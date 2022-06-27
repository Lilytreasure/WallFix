package com.example.walpapergrab.Listeners;

import com.example.walpapergrab.Models.SearchApiResponse;

public interface SearchResponseListener {
    void onFetch(SearchApiResponse response,String message);
    void  onError(String message);



}
