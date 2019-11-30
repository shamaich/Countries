package com.example.countries.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CountryApi {

    @GET()
    //Single<List<Country>> getCountry();
    Single<List<Country>> getCountry(@Url String url);

}
