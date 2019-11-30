package com.example.countries.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
public class Country implements Serializable {
    @ColumnInfo(name = "name")
    @SerializedName("name")
    String countryName;

    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    String capital;

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    String flag;

    @ColumnInfo(name = "callingCodes")
    @SerializedName("callingCodes")
    ArrayList<String> callingCodes;

    @ColumnInfo(name = "region")
    @SerializedName("region")
    String region;

    @ColumnInfo(name = "subregion")
    @SerializedName("subregion")
    String subregion;

    @ColumnInfo(name = "timezones")
    @SerializedName("timezones")
    ArrayList<String> timezones;

    @ColumnInfo(name = "currencies")
    @SerializedName("currencies")
    ArrayList<HashMap<String, String>> currencies;

    @ColumnInfo(name = "languages")
    @SerializedName("languages")
    ArrayList<HashMap<String, String>> languages;

    @PrimaryKey(autoGenerate = true)
    public int uuid;

    public Country() {
    }

    public Country(String countryName, String capital, String flag, ArrayList<String> callingCodes, String region, String subregion, ArrayList<String> timezones, ArrayList<HashMap<String, String>> currencies, ArrayList<HashMap<String, String>> languages) {
        this.countryName = countryName;
        this.capital = capital;
        this.flag = flag;
        this.callingCodes = callingCodes;
        this.region = region;
        this.subregion = subregion;
        this.timezones = timezones;
        this.currencies = currencies;
        this.languages = languages;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ArrayList<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(ArrayList<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public ArrayList<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(ArrayList<String> timezones) {
        this.timezones = timezones;
    }

    public ArrayList<HashMap<String, String>> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<HashMap<String, String>> currencies) {
        this.currencies = currencies;
    }

    public ArrayList<HashMap<String, String>> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<HashMap<String, String>> languages) {
        this.languages = languages;
    }
}
