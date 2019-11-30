package com.example.countries.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.countries.model.Country;
import com.example.countries.model.CountryApiService;
import com.example.countries.view.MainActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends AndroidViewModel {

    public MutableLiveData<List<Country>> country = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    private CountryApiService countryApiService = new CountryApiService();
    private CompositeDisposable disposable = new CompositeDisposable();

    public ListViewModel(@NonNull Application application) {
        super(application);
    }


    public void refresh() {
        fetchCountries(null);
    }

    public void fetchCountries(String query) {

        if (query == null) {
            return;
        }

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
                                countryLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

}
