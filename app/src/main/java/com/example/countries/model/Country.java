package com.example.countries.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Country implements Serializable {
    @SerializedName("name")
    String countryName;
    @SerializedName("capital")
    String capital;
    @SerializedName("flag")
    String flag;

    @SerializedName("callingCodes")
    ArrayList<String> callingCodes;
    @SerializedName("region")
    String region;
    @SerializedName("subregion")
    String subregion;

    @SerializedName("timezones")
    ArrayList<String> timezones;
    @SerializedName("currencies")
    ArrayList<HashMap<String, String>> currencies;

    @SerializedName("languages")
    ArrayList<HashMap<String, String>> languages;

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
