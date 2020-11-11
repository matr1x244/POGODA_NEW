package com.geekbrains.pogoda;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.common.internal.Constants;

public class CityFragment extends Fragment {

    private boolean isOrintation;

    private City currentCity;

    private final static String CITY_KEY = "CurrentCity";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        citylist(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isOrintation = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            currentCity = savedInstanceState.getParcelable(CITY_KEY);
        } else {
            currentCity = new City(0, getResources().getStringArray(R.array.city)[0]);
        }
        if (isOrintation) {
            showIsOrintation(currentCity);
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(CITY_KEY, currentCity);
    }

    private void citylist(View view) {
        LinearLayout layoutView = (LinearLayout) view;

        String[] city = getResources().getStringArray(R.array.city);
        for (int i = 0; i < city.length; i++) {
            String city_search = city[i];

            TextView textView = new TextView(getContext());

            textView.setText(city_search);
            textView.setTextSize(10);

            layoutView.addView(textView);

            final int finalindex = i;

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentCity = new City(finalindex, getResources().getStringArray(R.array.city)[finalindex]);
                    showIsOrintation(currentCity);
                }
            });
        }

    }

    private void showIsOrintation(City currentCity) {
        if (isOrintation) {
            CoatFragment detailsFragment = (CoatFragment) getFragmentManager().findFragmentById(R.id.city_info);
            if (detailsFragment == null || currentCity.getCityName() != detailsFragment.getCurrentCity().getImage_monuments()) {
                CoatFragment details = CoatFragment.create(currentCity);
            }

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.city_info, detailsFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        } else {
            Intent intentOrintation = new Intent();
            intentOrintation.setClass(getActivity(), CoatActivityFragment.class);

            intentOrintation.putExtra(CityFragment.CITY_KEY, currentCity);
            startActivity(intentOrintation);
        }
    }
}

