package com.example.countries.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Country country);

    @Query("SELECT * FROM country WHERE name LIKE :query")
    Country getCountryList(String query);

    @Query("DELETE FROM country WHERE name = :name")
    Country deleteCountry(String name);
}
