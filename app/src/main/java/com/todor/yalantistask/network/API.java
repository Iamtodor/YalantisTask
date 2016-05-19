package com.todor.yalantistask.network;

import com.todor.yalantistask.model.Example;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import retrofit2.http.Headers;
import rx.Observable;

public interface API {

    @Headers("Accept: application/json")
    @GET("/rest/v1/tickets")
    Observable<List<Example>> getTickets(@Query("state") String state);

}
