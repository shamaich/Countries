package com.example.countries.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.countries.util.converters.ArrayConverter;
import com.example.countries.util.converters.StringMapConverter;

@Database(entities = {Country.class}, version = 1, exportSchema = false)
@TypeConverters({ArrayConverter.class,StringMapConverter.class})
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
