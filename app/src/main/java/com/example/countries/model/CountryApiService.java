package com.example.countries.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryApiService {

    public static final String BASE_URL = "https://restcountries.eu/";

    private CountryApi countryApi;

    public CountryApiService(){
        countryApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CountryApi.class);
    }


    public Single<List<Country>> getCountry(String name){
        return countryApi.getCountry(BASE_URL + "/rest/v2/name/" + name);
    }
}
