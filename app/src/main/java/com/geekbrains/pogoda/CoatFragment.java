package com.geekbrains.pogoda;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CoatFragment extends Fragment {

    private final static String CITY_KEY = "CurrentCity";

        public static CoatFragment create(City city){
        CoatFragment fragment = new CoatFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(CITY_KEY, city);
        fragment.setArguments(bundle);

        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_coat,container, false);

        ImageView coatCity = layout.findViewById(R.id.imageViewFragment);
        TextView cityNameView = layout.findViewById(R.id.textViewFragment);

        TypedArray imgs = getResources().obtainTypedArray(R.array.city_monuments);

         City city = getCurrentCity();

         if(city != null) {
            //coatCity.setImageResource(imgs.getResourceId(ity.getImageMonuments() -1)); // ??
             cityNameView.setText(city.getCityName());
         }
        return layout;
    }

    public City getCurrentCity(){
        Bundle args = getArguments();
        City city = null;
        if(args != null) {
            city = getArguments().getParcelable(CoatFragment.CITY_KEY); // ?

        }
            return city;
    }

}
