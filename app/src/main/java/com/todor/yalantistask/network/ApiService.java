package com.todor.yalantistask.network;

import retrofit.GsonConverterFactory;
import retrofit2.Retrofit;

public class ApiService {

    public static final String BASE_URL = "http://dev-contact.yalantis.com";

    public API getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(API.class);
    }

}
