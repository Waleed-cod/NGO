package com.codembeded.ngo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codembeded.ngo.R;
import com.codembeded.ngo.models.ContactModel;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    ArrayList<ContactModel> data;
    Context ctx;


    public ContactAdapter(@NonNull ArrayList<ContactModel> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }
    @NonNull
    @Override

    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_box,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.contact_name.setText(data.get(position).getContact_name());
        holder.contact_phone_number.setText(data.get(position).getContact_phone());
        holder.char_text_box.setText(data.get(position).getChar_text_box());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView char_text_box;
        TextView contact_name,contact_phone_number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            char_text_box = itemView.findViewById(R.id.char_text_box);
            contact_name = itemView.findViewById(R.id.contact_name_tv);
            contact_phone_number = itemView.findViewById(R.id.contact_phone_number);
        }
    }
}
