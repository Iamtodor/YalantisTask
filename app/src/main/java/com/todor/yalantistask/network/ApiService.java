package com.todor.yalantistask.network;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class ApiService {

    public static final String BASE_URL = "http://dev-contact.yalantis.com";
    private OkHttpClient httpClient;

    public API getApiService() {
        setHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(httpClient)
                .build();

        return retrofit.create(API.class);
    }

    public void setHttpClient() {
        httpClient = new OkHttpClient();
        httpClient.interceptors().add(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("User-Agent", "Your-App-Name")
                    .header("Accept", "application/vnd.http://dev-contact.yalantis.com.v1.full+json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });
        httpClient.interceptors().add(new LoggingInterceptor());
    }
}
