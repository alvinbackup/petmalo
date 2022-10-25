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

import kotlin.jvm.internal.Lambda;

public class myapp_adapter extends RecyclerView.Adapter<myapp_adapter.myviewholder>
{
    appointment_list_model data[];

    public myapp_adapter(appointment_list_model[] data) {
        this.data = data;
    }





    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_row,parent,false);
        return new myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.service.setText(data[position].getService_text());
        holder.store.setText(data[position].getStore_name());
        holder.date.setText(data[position].getEvt_start());
        holder.time.setText(data[position].getEvt_time());
        holder.status.setText(data[position].getStatus());
        holder.store_id.setText(data[position].getP_id());
        holder.email.setText(data[position].getEmail());
        holder.appointment_id.setText(data[position].getAppoint_id());
        holder.fname.setText(data[position].getLname_text());
        holder.lname.setText(data[position].getLname_text());
        holder.address.setText(data[position].getAdd_text());
        holder.landmark.setText(data[position].getEvt_text());
        holder.contact.setText(data[position].getContact_text());
        Glide.with(holder.service.getContext()).load("https://pet-shop-management.000webhostapp.com/examples/upload/"+data[position].getService_img()).into(holder.app_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), appointment_details.class);
                    intent.putExtra("app_id",data[position].getAppoint_id());
                    intent.putExtra("store",data[position].getStore_name());
                    intent.putExtra("date",data[position].getEvt_start());
                    intent.putExtra("time",data[position].getEvt_time());
                    intent.putExtra("status",data[position].getStatus());
                intent.putExtra("fname",data[position].getFname_text());
                intent.putExtra("lname",data[position].getLname_text());
                intent.putExtra("address",data[position].getAdd_text());
                intent.putExtra("landmark",data[position].getEvt_text());
                intent.putExtra("contact",data[position].getContact_text());
                intent.putExtra("service",data[position].getService_text());
                intent.putExtra("image",data[position].getService_img());
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
        ImageView app_image;
        TextView service,store,date,time,status,store_id,email,appointment_id,fname,lname,address,landmark,contact;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            app_image=itemView.findViewById(R.id.app_img);
            service=itemView.findViewById(R.id.app_service);
            store=itemView.findViewById(R.id.app_store);
            date=itemView.findViewById(R.id.app_date);
            time=itemView.findViewById(R.id.app_time);
            status=itemView.findViewById(R.id.app_status);
            store_id=itemView.findViewById(R.id.app_storeid);
            email=itemView.findViewById(R.id.app_email);
            appointment_id=itemView.findViewById(R.id.app_appid);
            fname=itemView.findViewById(R.id.app_fname);
            lname=itemView.findViewById(R.id.app_lname);
            address=itemView.findViewById(R.id.app_address);
            landmark=itemView.findViewById(R.id.app_landmark);
            contact=itemView.findViewById(R.id.app_contact);


        }
    }
}
