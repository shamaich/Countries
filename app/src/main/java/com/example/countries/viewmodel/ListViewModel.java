package com.example.countries.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.countries.model.Country;
import com.example.countries.model.CountryApiService;
import com.example.countries.model.CountryDao;
import com.example.countries.model.CountryDatabase;
import com.example.countries.util.ConnectionLiveData;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends AndroidViewModel {

    public MutableLiveData<List<Country>> country = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private CountryApiService countryApiService = new CountryApiService();
    private CompositeDisposable disposable = new CompositeDisposable();

    private AsyncTask<Country, Void, Void> insertTask;
    private AsyncTask<String, Void, List<Country>> retrieveTask;
    private AsyncTask<Void, Void, List<Country>> allRetrieveTask;


    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchCountries(String query) {

        ConnectivityManager cm =
                (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (query == null && isConnected) {
            countryLoadError.setValue(false);
            loading.setValue(false);
            return;
        }
        else if (!isConnected && query == null) {
            fetchCountriesFromDatabase();
        }
        else if(!isConnected){
            fetchCountriesFromDatabaseWithQuery(query);
        }
        else if(isConnected) {
            fetchCountriesFromEndpoint(query);
        }

    }

    private void fetchCountriesFromEndpoint(String query) {
        loading.setValue(true);
        disposable.add(
                countryApiService.getCountry(query)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Country>>() {
                            @Override
                            public void onSuccess(List<Country> value) {
                                country.setValue(value);
                                countryLoadError.setValue(false);
                                loading.setValue(false);
                                Toast.makeText(getApplication(), "Countries retrieved from endpoint", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                fetchCountriesFromDatabase();
                                countryLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );

    }


    public void fetchCountriesFromDatabase() {

        allRetrieveTask = new AllRetrieveCountryTask();
        allRetrieveTask.execute();
    }

    public void fetchCountriesFromDatabaseWithQuery(String query) {

        retrieveTask = new RetrieveCountryTask();
        retrieveTask.execute(query);

    }

    private void countryRetried(List<Country> countryList) {
        country.setValue(countryList);
        countryLoadError.setValue(false);
        loading.setValue(false);
    }

    @SuppressLint("StaticFieldLeak")
    private class RetrieveCountryTask extends AsyncTask<String, Void, List<Country>> {

        @Override
        protected List<Country> doInBackground(String... strings) {
            CountryDao countryDao = CountryDatabase.getInstance(getApplication()).countryDao();
            return countryDao.getCountryList(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Country> countryList) {
            countryRetried(countryList);
        }

    }


    public void insertCountry(Country country) {
        insertTask = new InsertCountryTask();
        insertTask.execute(country);
    }

    @SuppressLint("StaticFieldLeak")
    private class InsertCountryTask extends AsyncTask<Country, Void, Void> {

        @Override
        protected Void doInBackground(Country... countries) {
            Log.d("InsertCountryTask", "doInBackground: " + countries[0].getCountryName());
            CountryDao countryDao = CountryDatabase.getInstance(getApplication()).countryDao();
            countryDao.insert(countries[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class AllRetrieveCountryTask extends AsyncTask<Void, Void, List<Country>> {

        @Override
        protected List<Country> doInBackground(Void... voids) {
            return CountryDatabase.getInstance(getApplication()).countryDao().getAllCountries();
        }

        @Override
        protected void onPostExecute(List<Country> countryList) {
            allCountriesRetrieved(countryList);
            Toast.makeText(getApplication(), "Countries retrieved from database", Toast.LENGTH_SHORT).show();
        }
    }

    private void allCountriesRetrieved(List<Country> countryList) {
        country.setValue(countryList);
        countryLoadError.setValue(false);
        loading.setValue(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (insertTask != null) {
            insertTask.cancel(true);
            insertTask = null;
        }

        if (retrieveTask != null) {
            retrieveTask.cancel(true);
            retrieveTask = null;
        }

        if (allRetrieveTask != null) {
            allRetrieveTask.cancel(true);
            allRetrieveTask = null;
        }
    }

}
