package com.example.countries.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.countries.R;
import com.example.countries.model.Country;

import butterknife.BindView;


public class DetailFragment extends Fragment {

    @BindView(R.id.fragment_detail_country_flag_image)
    ImageView imageView;

    @BindView(R.id.fragment_detail_country_name)
    TextView textView;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);

       /* if(getArguments() != null) {
            //country = DetailFragmentArgs.fromBundle(getArguments()).getCountryClassObject().;
            Country country = DetailFragmentArgs.fromBundle(getArguments()).getCountry();
        }*/
    }

}
