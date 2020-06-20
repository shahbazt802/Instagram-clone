package com.floydd.instagram_clone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("gy8QdqdL0yfsoohkL02WhzNpXMpVj5K4b3IPg9kI")
                // if defined
                .clientKey("SqM6sTfwGZJTb8OdBhKtJEcwlSnQoKiL5SCMQJfm")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
