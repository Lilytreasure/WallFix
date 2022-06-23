package com.example.walpapergrab.Api;

import com.example.walpapergrab.Models.CuratedApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CallWallpaperList {
    @Headers({
            "Accept:Application/json",
            "Authorization: 563492ad6f91700001000001c86f1910848f418ea34aa09efebd00c9"
    })
    @GET("curated/")
    Call<CuratedApiResponse> getWallpapers(
            @Query("page") String page,
            @Query( "per_page") String per_page

    );
}
