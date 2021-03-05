package com.example.movies;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<ViewHolderFavorite> {

    ArrayList<String> MTitle, MYear,MDirector,MPoster,imdbID_Sh_Movie;
    MainActivity FavoriteMovie = new MainActivity();

    public FavoriteAdapter(ArrayList<String> MTitle,
                               ArrayList<String> MYear,
                               ArrayList<String> MDirector,
                               ArrayList<String> imdbID_Sh_Movie,
                               ArrayList<String> MPoster){
        this.MTitle = MTitle;
        this.MYear = MYear;
        this.MDirector = MDirector;
        this.MPoster = MPoster;
        this.imdbID_Sh_Movie = imdbID_Sh_Movie;

    }


    @NonNull
    @Override
    public ViewHolderFavorite onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_recycler, parent, false);
        ViewHolderFavorite holder= new ViewHolderFavorite(v);
        return holder;    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFavorite holder, int position) {

        holder.txtTitle.setText("Title: " + MTitle.get(position));
        holder.txtYear.setText("Year: " + MYear.get(position));
        holder.txtDirector.setText("Director: " + MDirector.get(position));
        Picasso.get().load(MPoster.get(position)).fit().into(holder.imgPoster);

        //setOnClickListener on recyclerview
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = position;

                String idName = imdbID_Sh_Movie.get(index);
                FavoriteMovie.ShowDetailsActivity(holder.txtTitle.getContext(),idName);

            }
        });

    }

    @Override
    public int getItemCount() {
        int i = 0;
        if (MTitle != null)
            i = MTitle.size();
        return i;
    }
}
