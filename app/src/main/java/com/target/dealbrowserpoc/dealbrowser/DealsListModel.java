package com.target.dealbrowserpoc.dealbrowser;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * DealsListModel is the object that contains all details about the list. Used to move it between activities/fragments
 * Created by ramyav on 3/24/2018.
 */

public class DealsListModel implements Parcelable, Comparable<DealsListModel> {
    public int index;

    public String _id;
    public String title;
    public String description;
    public String price;
    public String salePrice;
    public String image;
    public String aisle;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.index);
        dest.writeString(this._id);
        dest.writeString(this.aisle);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.image);
        dest.writeString(this.price);
        dest.writeString(this.salePrice);
    }

    public DealsListModel() {
    }

    protected DealsListModel(Parcel in) {
        this.index = in.readInt();
        this._id = in.readString();
        this.aisle = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        this.price = in.readString();
        this.salePrice = in.readString();
    }

    public static final Parcelable.Creator<DealsListModel> CREATOR = new Parcelable.Creator<DealsListModel>() {
        @Override
        public DealsListModel createFromParcel(Parcel source) {
            return new DealsListModel(source);
        }

        @Override
        public DealsListModel[] newArray(int size) {
            return new DealsListModel[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(@NonNull DealsListModel dealsListModel) {
        int compareQuantity = Integer.getInteger(((DealsListModel) dealsListModel).getPrice());

        //ascending order
        return Integer.getInteger(this.price) - compareQuantity;

    }

    public static Comparator<DealsListModel> FruitNameComparator
            = new Comparator<DealsListModel>() {

        public int compare(DealsListModel fruit1, DealsListModel fruit2) {

            String fruitName1 = fruit1.getPrice();
            String fruitName2 = fruit2.getPrice();

            //ascending order
            return fruitName1.compareTo(fruitName2);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }

    };

}
