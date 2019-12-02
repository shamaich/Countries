package com.example.countries.util.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class StringMapConverter {
    @TypeConverter
    public static ArrayList<HashMap<String, String>> fromString(String value) {
        Type mapType = new TypeToken<ArrayList<HashMap<String, String>>>() {
        }.getType();
        return new Gson().fromJson(value, mapType);
    }

    //ArrayList<HashMap<String, String>>

    @TypeConverter
    public static String fromStringMap(ArrayList<HashMap<String, String>> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}