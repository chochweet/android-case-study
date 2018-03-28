package com.target.dealbrowserpoc.dealbrowser;

import com.target.dealbrowserpoc.dealbrowser.deals.DealsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API class to GET/PUT/POST/DELETE server calls
 * Created by ramyav on 3/24/2018.
 */

public interface DealsAPI {

    @GET("/api/deals")
    Call<DealsList> getDeals();
}
