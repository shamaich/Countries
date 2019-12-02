package com.example.countries.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.countries.R;
import com.example.countries.databinding.FragmentDetailBinding;
import com.example.countries.model.Country;
import com.example.countries.viewmodel.ListViewModel;


public class DetailFragment extends Fragment {

    private static Country country;
    private FragmentDetailBinding binding;
    private ListViewModel listViewModel;
    private Button saveCountryButton;

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        this.binding = binding;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        country = DetailFragmentArgs.fromBundle(getArguments()).getCountry();
        binding.setCountry(country);
        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        saveCountryButton = view.findViewById(R.id.fragment_detail_save_button);
        saveCountryButton.setOnClickListener(view1 -> listViewModel.insertCountry(country));

        observeViewModel();

    }

    private void observeViewModel() {
        listViewModel.connectionLiveData.observe(getViewLifecycleOwner(), connection -> {
            if (connection.getIsConnected()) {
                saveCountryButton.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), String.format("Wireless connection"), Toast.LENGTH_SHORT).show();
            } else {
                saveCountryButton.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), String.format("Connection turned OFF"), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
