package com.example.walpapergrab.Api;

import android.content.Context;
import android.widget.Toast;

import com.example.walpapergrab.Listeners.CuratedResponseListener;
import com.example.walpapergrab.Listeners.SearchResponseListener;
import com.example.walpapergrab.Models.CuratedApiResponse;
import com.example.walpapergrab.Models.SearchApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    Context context;

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://api.pexels.com/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManager(Context context) {

        this.context = context;
    }


    public void getCuratedWallpapers(CuratedResponseListener listener,String page){
        CallWallpaperList callWallpaperList=retrofit.create(CallWallpaperList.class);
        Call<CuratedApiResponse> call =callWallpaperList.getWallpapers(page,"20");


        call.enqueue(new Callback<CuratedApiResponse>() {
            @Override
            public void onResponse(Call<CuratedApiResponse> call, Response<CuratedApiResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Error occurred!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onFetch(response.body(),response.message());

            }

            @Override
            public void onFailure(Call<CuratedApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());

            }
        });


    }

    public void searchCuratedWallpapers(SearchResponseListener listener, String page,String query){
        CallWallpaperListSearch callWallpaperListSearch=retrofit.create(CallWallpaperListSearch.class);
        Call<SearchApiResponse> call =callWallpaperListSearch.searchWallpapers(query,page,"20");


        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Error occurred!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onFetch(response.body(),response.message());

            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());

            }
        });


    }




}
