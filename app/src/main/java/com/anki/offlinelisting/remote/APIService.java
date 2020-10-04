package com.anki.offlinelisting.remote;

import com.anki.offlinelisting.remote.pojo.ListingModule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Api class
 */
public interface APIService {
    @GET("api/")
    Call<ListingModule> getListData(@Query("results") String value);
}
