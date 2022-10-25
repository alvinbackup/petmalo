package com.example.pet_malo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class cart_adapder extends RecyclerView.Adapter<cart_adapder.ViewHolder> {
    Context context;
    List<cart_model> cartModelList;

    public cart_adapder(Context context, List<cart_model> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.p_name.setText(cartModelList.get(position).getProduct_name());
        holder.p_categ.setText(cartModelList.get(position).getProduct_categ());
        holder.p_desc.setText(cartModelList.get(position).getProduct_desc());
        holder.p_id.setText(cartModelList.get(position).getProduct_id());
        holder.p_quan.setText(cartModelList.get(position).getProduct_quan());
        holder.p_price.setText(cartModelList.get(position).getProduct_price());
        holder.p_store.setText(cartModelList.get(position).getStore());

        Glide.with(holder.p_name.getContext()).load("https://pet-shop-management.000webhostapp.com/examples/upload/"+cartModelList.get(position).getProduct_img()).into(holder.image);



    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView p_name,p_categ,p_desc,p_id,p_quan,p_price,p_store;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.cart_img);
            p_name=itemView.findViewById(R.id.cart_product);
            p_categ=itemView.findViewById(R.id.cart_category);
            p_desc=itemView.findViewById(R.id.cart_desc);
            p_id=itemView.findViewById(R.id.cart_storeid);
            p_quan=itemView.findViewById(R.id.cart_quantity);
            p_price=itemView.findViewById(R.id.cart_price);
            p_store=itemView.findViewById(R.id.cart_store);

        }
    }
}
