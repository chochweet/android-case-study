package com.target.dealbrowserpoc.dealbrowser.deals;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.target.dealbrowserpoc.dealbrowser.DealsListModel;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Object class used to retrieve data from GSon. SerializeName should be the key respective to the one in the json file.
 * Created by ramyav on 3/24/2018.
 */

public class DealsList {
    @SerializedName("id")
    public Integer id;
    @SerializedName("data")
    public List<DealDetail> data = new ArrayList<>();

    public class DealDetail {
        @SerializedName("index")
        public int index;
        @SerializedName("_id")
        public String _id;
        @SerializedName("title")
        public String title;
        @SerializedName("description")
        public String description;
        @SerializedName("price")
        public String price;
        @SerializedName("salePrice")
        public String salePrice;
        @SerializedName("image")
        public String image;
        @SerializedName("aisle")
        public String aisle;
    }
}
