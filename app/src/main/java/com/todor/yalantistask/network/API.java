package com.todor.yalantistask.network;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("/rest/v1/tickets")
    void getTickets(@Query("state") String state);

}
