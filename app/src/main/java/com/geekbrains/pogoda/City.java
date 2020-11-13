package com.geekbrains.pogoda;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {

    private int imageMonuments;
    private String cityName;

    protected City(Parcel in) {
        imageMonuments = in.readInt();
        cityName = in.readString();
    }

    public City(int imageMonuments, String cityName){
        this.imageMonuments = imageMonuments;
        this.cityName = cityName;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageMonuments);
        dest.writeString(cityName);

    }
    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getImageMonuments() {
        return imageMonuments;
    }

    public String getCityName() {
        return cityName;
    }
}
