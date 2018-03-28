package com.target.dealbrowserpoc.dealbrowser;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Controller class that takes a Base URL, uses Retrofit to connect to server, while prividing
 * the type of json converter and logging mechanism
 * Created by ramyav on 3/24/2018.
 */

public class GetDealsController {

    static final String BASE_URL = "http://target-deals.herokuapp.com";

    private static Retrofit retrofit = null;

    static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        // TODO: Change the level type to SOURCE for production times. USE BODY for development and testing
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();



        return retrofit;
    }
}
