package com.example.walpapergrab.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walpapergrab.Listeners.OnRecyclerClickListener;
import com.example.walpapergrab.Models.Photo;
import com.example.walpapergrab.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CuratedAdapter extends  RecyclerView.Adapter<curatedViewHolder> {
    Context context;
    List<Photo> list;
    OnRecyclerClickListener listener;

    public CuratedAdapter(Context context, List<Photo> list, OnRecyclerClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }


    public curatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new curatedViewHolder(LayoutInflater.from(context).inflate(R.layout.home_list,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull curatedViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getSrc().getMedium()).placeholder(R.drawable.defaultpic).into(holder.imageview_list);
        holder.home_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(holder.getAdapterPosition()));

            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}
class curatedViewHolder extends RecyclerView.ViewHolder {
    CardView  home_list_container;
    ImageView imageview_list;

    public curatedViewHolder(@NonNull View itemView) {
        super(itemView);
        home_list_container=itemView.findViewById(R.id.home_list_container);
        imageview_list=itemView.findViewById(R.id.imageview_list);
    }

}
