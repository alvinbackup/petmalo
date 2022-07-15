package com.example.pet_malo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class prod_adapter extends RecyclerView.Adapter<prod_adapter.prodviewholder>
{
    prod_model data[];



    public prod_adapter(prod_model[] data) {
        this.data = data;

    }

    @NonNull
    @NotNull
    @Override
    public prodviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_product_row,parent,false);
        return new prodviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull prodviewholder holder, int position) {
        holder.prod_name.setText(data[position].getProduct_name());
        holder.prod_price.setText(data[position].getPrice());
        holder.prod_stock.setText(data[position].getStock());
        holder.prod_desc.setText(data[position].getDescription());
        holder.prod_id.setText(data[position].getProd_id());
        holder.prod_categ.setText(data[position].getProduct_categ());

        Glide.with(holder.prod_name.getContext()).load("https://petmalo.000webhostapp.com/examples/upload/"+data[position].getImage()).into(holder.prod_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),product_details.class);
                intent.putExtra("prod_name",data[position].getProduct_name());
                intent.putExtra("prod_price",data[position].getPrice());
                intent.putExtra("prod_stock",data[position].getStock());
                intent.putExtra("prod_desc",data[position].getDescription());
                intent.putExtra("prod_categ",data[position].getProduct_categ());
                intent.putExtra("prod_id",data[position].getProd_id());
                intent.putExtra("img",data[position].getImage());
                view.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    class prodviewholder extends RecyclerView.ViewHolder
    {

       ImageView prod_image;
       TextView prod_name, prod_price,prod_desc, prod_stock,prod_id,prod_categ;
        public prodviewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            prod_image=itemView.findViewById(R.id.prod_img);
            prod_name=itemView.findViewById(R.id.prod_name);
            prod_price=itemView.findViewById(R.id.prod_price);
            prod_desc=itemView.findViewById(R.id.prod_desc);
            prod_stock=itemView.findViewById(R.id.prod_stock);
            prod_id=itemView.findViewById(R.id.product_id);
            prod_categ=itemView.findViewById(R.id.prod_categ);



        }
    }
}
