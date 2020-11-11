package com.geekbrains.pogoda;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {

    private final int image_monuments;
    private final String cityName;

    protected City(Parcel in) {
        image_monuments = in.readInt();
        cityName = in.readString();
    }

    public City(int image_monuments, String cityName){
        this.image_monuments = image_monuments;
        this.cityName = cityName;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image_monuments);
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

     public int getImage_monuments() {
        return image_monuments;
    }

    public String getCityName() {
        return cityName;
    }
}
