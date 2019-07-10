package com.chennikawangmai.tkthree;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RetrofitJson {
    @GET("forces/{id}")
    Call<Force> getForces(@Path("id") String Id);
    @GET("forces")
    Call<List<Forcelistforce>>getForcesList();
    @GET("crimes-at-location?")
    Call<List<Crime>>getcrimes(@QueryMap Map<String,Object> parameters);
}
