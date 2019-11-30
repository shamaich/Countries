package com.example.countries.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countries.R;
import com.example.countries.databinding.ItemCountryBinding;
import com.example.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> implements CountryClickListener {

    private ArrayList<Country> countryList;
  /*  private CountryClickListener countryClickListener;

    public CountryListAdapter(CountryClickListener countryClickListener) {
        this.countryClickListener = countryClickListener;
    }*/

    public CountryListAdapter(ArrayList<Country> countryList) {
        this.countryList = countryList;
    }

    public void updateCountryList(List<Country> newCountryList) {
        countryList.clear();
        countryList.addAll(newCountryList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryListAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCountryBinding view = DataBindingUtil.inflate(inflater, R.layout.item_country, parent, false);

        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListAdapter.CountryViewHolder holder, int position) {

        holder.itemView.setCountry(countryList.get(position));
        holder.itemView.setListener(this);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    @Override
    public void onCountryClicked(View v) {
        Log.d("onCountryClicked", "onCountryClicked: ");
        String uuidString = ((TextView)v.findViewById(R.id.countryName)).getText().toString();
        Country country = new Country();
        for (Country c: countryList){
            if(c.getCountryName().equals(uuidString)){
                country = c;
                break;
            }
        }
        ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail(country);
        action.setCountry(country);
        Navigation.findNavController(v).navigate(action);
    }


    public class CountryViewHolder extends RecyclerView.ViewHolder {

        public ItemCountryBinding itemView;

        public CountryViewHolder(@NonNull ItemCountryBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
            //Country country = itemView.getCountry();


        }
    }
}
