package com.firas.project.network;

import com.firas.project.models.TvShowPageResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetTvShowDataService {
    @GET("tv/popular")
    Call<TvShowPageResult> getPopularTvShow (@Query("page") int page, @Query("api_key") String userkey);

}
