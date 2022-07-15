package com.example.pet_malo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class petlist_adapter extends RecyclerView.Adapter<petlist_adapter.myviewholder>
{
    petlist_model data[];

    public petlist_adapter(petlist_model[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petlist_row,parent,false);
    return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
     holder.petlist_name.setText(data[position].getName());
        holder.petlist_email.setText(data[position].getCust_email());
        holder.petlist_birthday.setText(data[position].getBirthday());
        holder.petlist_gender.setText(data[position].getGender());
        holder.petlist_breed.setText(data[position].getBreed());
        holder.petlist_species.setText(data[position].getSpecies());
        holder.petlist_date.setText(data[position].getDate());
        holder.petlist_againts.setText(data[position].getAgaints());
        holder.petlist_manufacturer.setText(data[position].getManufacturer());
        holder.petlist_veterinarian.setText(data[position].getVeterinarian());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),pet_info.class);
                intent.putExtra("pet_name",data[position].getName());
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView petlist_email, petlist_name, petlist_birthday,petlist_gender, petlist_breed,petlist_species,petlist_date,petlist_againts,petlist_manufacturer, petlist_veterinarian;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            petlist_email =itemView.findViewById(R.id.petlist_email);
            petlist_name =itemView.findViewById(R.id.petlist_name);
            petlist_birthday =itemView.findViewById(R.id.petlist_birthday);
            petlist_gender =itemView.findViewById(R.id.petlist_gender);
            petlist_breed =itemView.findViewById(R.id.petlist_breed);
            petlist_species =itemView.findViewById(R.id.petlist_species);
            petlist_date =itemView.findViewById(R.id.petlist_date);
            petlist_againts =itemView.findViewById(R.id.petlist_againts);
            petlist_manufacturer =itemView.findViewById(R.id.petlist_manufacturer);
            petlist_veterinarian =itemView.findViewById(R.id.petlist_veterinarian);



        }
    }
}
