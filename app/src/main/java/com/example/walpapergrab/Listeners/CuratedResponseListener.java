package com.example.walpapergrab.Listeners;

import com.example.walpapergrab.Models.CuratedApiResponse;

public interface CuratedResponseListener {
    void onFetch(CuratedApiResponse response,String message);
    void onError(String message);




}
