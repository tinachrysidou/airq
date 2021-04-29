package com.example.airq.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> info;
    private MutableLiveData<String> nav;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        info = new MutableLiveData<>();
        nav = new MutableLiveData<>();

        mText.setValue("Thesis of Tina Chrysidou & George Syrrakos Undergrads @ INFORMATION AND COMMUNICATION SYSTEMS DEPARTMENT OF THE AEGEAN UNIVERSITY");
        info.setValue("This App is part of our Thesis based on Air quality monitoring using a Particulate Matter sensor" +
                "\n" +
                "All Data Are live recorded stored in InfluxDB and displayed using Grafana"+
                "\n" + "We push notifications and live data updates to keep the user posted on pollution predictions");
        nav.setValue("Navigate Using the menu on top left corner");


    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> getInfo() {
        return info;
    }
    public LiveData<String> getNav() {
        return nav;
    }
}