package com.example.countries.view;


import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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

import android.widget.LinearLayout.LayoutParams;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment {

    private static Country country;
    private FragmentDetailBinding binding;
    private ListViewModel listViewModel;
    @BindView(R.id.fragment_detail_save_button)
    Button saveCountryButton;

    public DetailFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
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
        ButterKnife.bind(this, view);
        country = DetailFragmentArgs.fromBundle(getArguments()).getCountry();
        binding.setCountry(country);
        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        saveCountryButton.setOnClickListener(view1 -> {
            listViewModel.insertCountry(country);
            saveCountryButton.setEnabled(false);
        });

        initializeLayouts();
        observeViewModel();

    }

    private void observeViewModel() {
        listViewModel.connectionLiveData.observe(getViewLifecycleOwner(), connection -> {
            if (connection.getIsConnected()) {
                saveCountryButton.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Wireless connection", Toast.LENGTH_SHORT).show();
            } else {
                saveCountryButton.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Connection turned OFF", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeLayouts() {

        LinearLayout linearLayout = Objects.requireNonNull(getView()).findViewById(R.id.fragment_detail_layout_callingCodes);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        StringBuilder getCountryTimezone = new StringBuilder();
        StringBuilder getCountryCurrency = new StringBuilder();
        StringBuilder getCountryLanguages = new StringBuilder();

        for (String x : country.getTimezones()) {
            getCountryTimezone.append("\n").append(x);//.append(" ");
        }

        TextView textViewTimeZone = new TextView(getContext());
        textViewTimeZone.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        textViewTimeZone.setPadding(10, 10, 10, 10);
        textViewTimeZone.setLayoutParams(layoutParams);
        textViewTimeZone.setText(String.format("Timezones: %s", getCountryTimezone));
        linearLayout.addView(textViewTimeZone);

        for (HashMap<String, String> x : country.getCurrencies()) {
            getCountryCurrency.append("\n").append("Name: ").append(x.get("name"));
        }

        TextView textViewCurrency = new TextView(getContext());
        textViewCurrency.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        textViewCurrency.setPadding(10, 10, 10, 10);
        textViewCurrency.setLayoutParams(layoutParams);
        textViewCurrency.setText(String.format("Currency:%s", getCountryCurrency));
        linearLayout.addView(textViewCurrency);


        for (HashMap<String, String> x : country.getLanguages()) {
            getCountryLanguages.append("\n").append("Name: ").append(x.get("name"));
        }

        TextView textViewLanguages = new TextView(getContext());
        textViewLanguages.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        textViewLanguages.setPadding(10, 10, 10, 10);
        textViewLanguages.setLayoutParams(layoutParams);
        textViewLanguages.setText(String.format("Languages:%s", getCountryLanguages));
        linearLayout.addView(textViewLanguages);


    }


}
