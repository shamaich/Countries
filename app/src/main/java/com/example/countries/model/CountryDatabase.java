package com.example.countries.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Country.class}, version = 1, exportSchema = false)
public abstract class CountryDatabase extends RoomDatabase {

    private static CountryDatabase instance;

    public static CountryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    CountryDatabase.class,
                    "countrydatabase")
                    .build();
        }
        return instance;
    }

    public abstract CountryDao countryDao();
}
