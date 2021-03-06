package com.codembeded.ngo.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codembeded.ngo.R;
import com.codembeded.ngo.models.CollectPhoneNumbers;
import com.codembeded.ngo.models.ContactModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    Context mContext;
    ArrayList<ContactModel> list;
    boolean isSelectedMode = false;
    ArrayList<ContactModel> selectedItems = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    CollectPhoneNumbers listener;
    int selected_position = 0;


    public ContactAdapter(Context mContext, ArrayList<ContactModel> list, CollectPhoneNumbers listener) {
        this.mContext = mContext;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override

    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_box, parent, false);

        return new ViewHolder(view);


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String c = list.get(position).getContact_name();
        String character = String.valueOf(c.charAt(0));
        holder.char_text_box.setText(character.toUpperCase());

        holder.contact_name.setText(list.get(position).getContact_name());
        holder.contact_phone_number.setText(list.get(position).getContact_phone());
        holder.checkbox.setChecked(list.get(position).isCheck());
//        holder.checkbox_iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (holder.checkbox_iv.isChecked()) {
//                    data.add(list.get(position).getContact_phone());
//                    Toast.makeText(mContext, list.get(position).getContact_phone() + "/" + list.get(position).getContact_name() +"/"+ String.valueOf(list.get(position)), Toast.LENGTH_SHORT).show();
//                } else {
//                    data.remove(list.get(position).getContact_phone());
//                }
////                listener.phoneNumbers(position, data);
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkbox;
        TextView char_text_box, contact_name, contact_phone_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkbox = itemView.findViewById(R.id.checkbox_iv);
            char_text_box = itemView.findViewById(R.id.char_text_box);
            contact_name = itemView.findViewById(R.id.contact_name_tv);
            contact_phone_number = itemView.findViewById(R.id.contact_phone_number);

//            checkbox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                }
//            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkbox.setChecked(!checkbox.isChecked());

                    if (checkbox.isChecked()) {
                        list.get(getAdapterPosition()).setCheck(true);
                        data.add(list.get(getAdapterPosition()).getContact_phone());
//                        Toast.makeText(mContext, String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    } else {
                        list.get(getAdapterPosition()).setCheck(false);
                        data.remove(list.get(getAdapterPosition()).getContact_phone());
                    }
                    listener.phoneNumbers(getAdapterPosition(),data);

                }
            });
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @SuppressLint("ResourceAsColor")
//                @Override
//                public boolean onLongClick(View v) {
//                    isSelectedMode = true;
//                    if (selectedItems.contains(list.get(getAdapterPosition()))) {
//                        itemView.setBackgroundColor(Color.TRANSPARENT);
//                        selectedItems.remove(list.get(getAdapterPosition()));
//                        data.remove(list.get(getAdapterPosition()).getContact_phone());
//                        listener.phoneNumbers(getAdapterPosition(),data);
//
//
//                    } else {
//                        itemView.setBackgroundColor(R.color.blue_grey_600);
//                        selectedItems.add(list.get(getAdapterPosition()));
//                        data.add(list.get(getAdapterPosition()).getContact_phone());
//                        Toast.makeText(mContext, "nuMBRs: " + data, Toast.LENGTH_SHORT).show();
//                    }
//
//                    if (selectedItems.size() == 0)
//                        isSelectedMode = false;
//                    return true;
//                }
//            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @SuppressLint("ResourceAsColor")
//                @Override
//                public void onClick(View v) {
//                    if (isSelectedMode) {
//                        if (selectedItems.contains(list.get(getAdapterPosition()))) {
//                            itemView.setBackgroundColor(Color.TRANSPARENT);
//                            selectedItems.remove(list.get(getAdapterPosition()));
//                            data.remove(list.get(getAdapterPosition()).getContact_phone());
//
//                        } else {
//                            itemView.setBackgroundColor(R.color.blue_grey_600);
//                            selectedItems.add(list.get(getAdapterPosition()));
//                            data.add(list.get(getAdapterPosition()).getContact_phone());
//                            listener.phoneNumbers(getAdapterPosition(),data);
//
//                            Toast.makeText(mContext, "nuMBRs: " + data, Toast.LENGTH_SHORT).show();
//                        }
//                        if (selectedItems.size() == 0)
//                            isSelectedMode = false;
//                    } else {
//
//                    }
//                }
//            });


        }
    }

}
