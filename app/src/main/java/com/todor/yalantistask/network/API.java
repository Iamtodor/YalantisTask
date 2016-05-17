package com.todor.yalantistask.network;

import com.todor.yalantistask.model.Example;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit2.http.Headers;

public interface API {

    @Headers("Accept: application/json")
    @GET("/rest/v1/tickets")
    Call<List<Example>> getTickets(@Query("state") String state);

}
