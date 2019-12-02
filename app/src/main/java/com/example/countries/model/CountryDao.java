package com.example.countries.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Country country);

    @Query("SELECT * FROM country WHERE name LIKE :query || '%'")
    List<Country> getCountryList(String query);

    @Query("DELETE FROM country WHERE name = :name")
    void deleteCountry(String name);

    @Query("SELECT * FROM country")
    List<Country> getAllCountries();

}
