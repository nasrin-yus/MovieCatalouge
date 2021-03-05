package com.example.movies;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<TestViewHolder> {
    ArrayList<String> MTitle,MYear,MDirector,MPoster,imdbID_Sh_Movie;
    MainActivity searchMovie = new MainActivity();
    public MovieAdapter(ArrayList<String> MTitle,
                        ArrayList<String> MYear,
                        ArrayList<String> MDirector,
                        ArrayList<String> MPoster,
                        ArrayList<String> imdbID_Sh_Movie) {


        this.MTitle = MTitle;
        this.MYear = MYear;
        this.MDirector = MDirector;
        this.MPoster = MPoster;
        this.imdbID_Sh_Movie=imdbID_Sh_Movie;
           }

    @NonNull

    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_recycler, parent, false);
        TestViewHolder holder = new TestViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        holder.txtTitle.setText("Movie Title:"+ MTitle.get(position));
        holder.txtYear.setText("Year:"+ MYear.get(position));
        holder.txtDirector.setText("Movie Director:"+ MDirector.get(position));
        Picasso.get().load(MPoster.get(position)).fit().into(holder.imgPoster);

        //setOnClickListener on recyclerview
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = position;

                String idName = imdbID_Sh_Movie.get(index);
                    searchMovie.ShowDetailsActivity(holder.txtTitle.getContext(),idName);

                            }
        });
    }

    @Override
    public int getItemCount() {
        int i = 0;

        if(MTitle != null)
            i = MTitle.size();


        return i;    }
}
