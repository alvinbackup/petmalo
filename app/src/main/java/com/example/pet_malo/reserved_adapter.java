package com.example.pet_malo;

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

public class reserved_adapter extends RecyclerView.Adapter<reserved_adapter.reserve_holder>
{
    reservedlist_model data[];

    public reserved_adapter(reservedlist_model[] data) {
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public reserve_holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reservedlist_row,parent,false);
       return new reserve_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull reserve_holder holder, int position) {

        holder.res_name.setText(data[position].getProd_name());
        holder.res_desc.setText(data[position].getRev_desc());
        holder.res_quan.setText(data[position].getRev_quan());
        holder.res_categ.setText(data[position].getRev_categ());
        holder.res_total.setText(data[position].getProd_total());
        holder.res_stat.setText(data[position].getStatus());
        holder.cust_name.setText(data[position].getCust_name());
        holder.cust_contact.setText(data[position].getCust_contact());
        holder.cust_add.setText(data[position].getCust_add());
        Glide.with(holder.res_name.getContext()).load("https://pet-shop-management.000webhostapp.com/examples/upload/"+data[position].getProd_image()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), order_details.class);
                intent.putExtra("order_status",data[position].getStatus());
                intent.putExtra("prod_name",data[position].getProd_name());
                intent.putExtra("order_quantity",data[position].getRev_quan());
                intent.putExtra("order_custname",data[position].getCust_name());
                intent.putExtra("order_custcon",data[position].getCust_contact());
                intent.putExtra("order_custadd",data[position].getCust_add());
                intent.putExtra("orderdd",data[position].getRev_desc());
                intent.putExtra("ordertt",data[position].getProd_total());

                intent.putExtra("img","https://pet-shop-management.000webhostapp.com/examples/upload/"+data[position].getProd_image());

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class reserve_holder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView res_name, res_desc,res_quan,res_total,res_categ, res_stat,cust_name,cust_contact,cust_add;
        public reserve_holder(@NonNull @NotNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.res_img);
            cust_name=itemView.findViewById(R.id.name);
            cust_contact=itemView.findViewById(R.id.contact);
            cust_add=itemView.findViewById(R.id.address);

            res_name=itemView.findViewById(R.id.resprod_name);
            res_quan=itemView.findViewById(R.id.resprod_qaun);
            res_desc=itemView.findViewById(R.id.resprod_desc);
            res_categ=itemView.findViewById(R.id.resprod_categ);
            res_total=itemView.findViewById(R.id.resprod_total);
            res_stat=itemView.findViewById(R.id.resprod_status);


        }
    }
}
