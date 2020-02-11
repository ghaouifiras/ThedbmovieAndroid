package com.firas.project.network;

import com.firas.project.models.TvDescription;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetTvDescriptionDataService {
    @GET("tv/{tv_id}")
    Call<TvDescription> getTvDescription(@Path("tv_id") int TvshowId, @Query("api_key") String userkey);
}