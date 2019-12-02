package com.example.countries.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.countries.R;
import com.example.countries.util.SearchManager;
import com.example.countries.viewmodel.ListViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements SearchManager.QueryListener {

    private ListViewModel viewModel;
    private CountryListAdapter countryListAdapter = new CountryListAdapter(new ArrayList<>());

    @BindView(R.id.fragment_list_countryList)
    RecyclerView countryList;

    @BindView(R.id.fragment_list_listError)
    TextView listError;

    @BindView(R.id.fragment_list_loadingView)
    ProgressBar loadingView;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;



    public ListFragment() {

        SearchManager.setQueryListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.fetchCountries(null);
        countryList.setLayoutManager(new LinearLayoutManager(getContext()));
        countryList.setAdapter(countryListAdapter);

        refreshLayout.setOnRefreshListener(() -> {
            countryList.setVisibility(View.GONE);
            listError.setVisibility(View.GONE);
            loadingView.setVisibility(View.VISIBLE);
            onQueryChanged(null);
            refreshLayout.setRefreshing(false);
        });

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.country.observe(getViewLifecycleOwner(), countries -> {
            if (countries != null) {
                countryList.setVisibility(View.VISIBLE);
                countryListAdapter.updateCountryList(countries);
            }
        });

        viewModel.countryLoadError.observe(getViewLifecycleOwner(), isError -> {
            if (isError != null) {
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        viewModel.loading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    listError.setVisibility(View.GONE);
                    countryList.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onQueryChanged(String newQuery) {
        viewModel.fetchCountries(newQuery);
    }
}
