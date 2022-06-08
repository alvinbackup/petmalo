package com.example.pet_malo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Storesmarker {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("lat")
    @Expose
    public String lat;
    @SerializedName("lng")
    @Expose
    public String lng;

}