package com.example.pet_malo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class petcare_adapter extends RecyclerView.Adapter<petcare_adapter.petcareviewholder>
{
    model data[];


    public petcare_adapter(model[] data) {
        this.data = data;

    }

    @NonNull
    @Override
    public petcareviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.petcare_row,parent,false);
         return  new petcareviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  petcareviewholder holder,int position) {
        holder.name.setText(data[position].getTitle());
        holder.desc.setText(data[position].getDescription());
        Glide.with(holder.name.getContext()).load("https://pet-shop-management.000webhostapp.com/examples/upload/"+data[position].getImages()).into(holder.img);
         holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),petcare_detailed.class);
                intent.putExtra("title",data[position].getTitle());
                intent.putExtra("desc",data[position].getDescription());
                intent.putExtra("img","https://pet-shop-management.000webhostapp.com/examples/upload/"+data[position].getImages());
                view.getContext().startActivity(intent);




            }
        });


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class petcareviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView name, desc;

        public petcareviewholder(@NonNull @NotNull View itemView) {

            super(itemView);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            desc=itemView.findViewById(R.id.desc);

        }
    }
}
