package com.example.walpapergrab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.walpapergrab.Adapters.CuratedAdapter;
import com.example.walpapergrab.Api.RequestManager;
import com.example.walpapergrab.Listeners.CuratedResponseListener;
import com.example.walpapergrab.Listeners.OnRecyclerClickListener;
import com.example.walpapergrab.Models.CuratedApiResponse;
import com.example.walpapergrab.Models.Photo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class  MainActivity extends AppCompatActivity implements OnRecyclerClickListener {


    RecyclerView recyclerview_home;
    CuratedAdapter adapter;
    RequestManager manager;
    ProgressDialog  dialog;
    FloatingActionButton fab_next,fab_previous;
    int page;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_next=findViewById(R.id.fab_next);
        fab_previous=findViewById(R.id.fab_previous);
        recyclerview_home=findViewById(R.id.recyclerview_home);

        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading data...");
        manager=new RequestManager(this);
        manager.getCuratedWallpapers(listener,"1");


        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String next_page=String.valueOf(page+1);
                manager.getCuratedWallpapers(listener,next_page);
                dialog.show();

            }
        });

        fab_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page>1){
                    String previous_page=String.valueOf(page-1);
                    manager.getCuratedWallpapers(listener,previous_page);
                    dialog.show();

                }
            }
        });



//Above this is the main oncreate method
    }
    private final CuratedResponseListener listener=new CuratedResponseListener() {
        @Override
        public void onFetch(CuratedApiResponse response, String message) {
            dialog.dismiss();
            if(response.getPhotos().isEmpty()){
                Toast.makeText(MainActivity.this, "No photo !!", Toast.LENGTH_SHORT).show();
                return;
            }
            page=response.getPage();
            showData(response.getPhotos());

        }

        @Override
        public void onError(String message) {
             dialog.dismiss();
            Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();

        }

    };

    private void showData(ArrayList<Photo> photos) {

        recyclerview_home.setHasFixedSize(true);
        recyclerview_home.setLayoutManager(new GridLayoutManager(this,2));
        adapter=new CuratedAdapter(MainActivity.this,photos,this);
        recyclerview_home.setAdapter(adapter);



    }

    @Override
    public void onClick(Photo photo) {
        //display the name of the photographer
        Toast.makeText(MainActivity.this, photo.getPhotographer(), Toast.LENGTH_SHORT).show();

    }
}