package com.codembeded.ngo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codembeded.ngo.R;
import com.codembeded.ngo.models.CollectPhoneNumbers;
import com.codembeded.ngo.models.CollectReadNumbers;
import com.codembeded.ngo.models.ModelRead;

import java.util.ArrayList;

public class AdapterRead extends RecyclerView.Adapter<AdapterRead.ViewHolder> {
    ArrayList<ModelRead> data;
    ArrayList<String> get_numbers = new ArrayList<>();
    Context ctx;
    CollectReadNumbers GetNumbersListener ;

    public AdapterRead(ArrayList<ModelRead> data, Context ctx, CollectReadNumbers getNumbersListener) {
        this.data = data;
        this.ctx = ctx;
        GetNumbersListener = getNumbersListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.box,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.number_tv.setText(data.get(position).getNumber());
        holder.checkbox.setChecked(data.get(position).isCheck());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView number_tv;
        CheckBox checkbox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox_v);
            number_tv =itemView.findViewById(R.id.number_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkbox.setChecked(!checkbox.isChecked());
                    if (checkbox.isChecked()){

                        data.get(getAdapterPosition()).setCheck(true);
                        get_numbers.add(data.get(getAdapterPosition()).getNumber());
                    }else{

                            data.get(getAdapterPosition()).setCheck(false);
                            get_numbers.remove(data.get(getAdapterPosition()).getNumber());
                    }
                 //   listener.phoneNumbers(getAdapterPosition(),get_numbers);
                    GetNumbersListener.GetPhoneNumbers(get_numbers);


                }
            });
        }
    }
}
