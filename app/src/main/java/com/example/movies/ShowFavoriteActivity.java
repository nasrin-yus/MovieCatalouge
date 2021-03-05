package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ShowFavoriteActivity extends AppCompatActivity {
    ArrayList<String> MTitle,MYear,MDirector,MPoster,imdbID_Sh_Movie;
    RecyclerView rv_ShowFavorite;
   // ArrayList<MovieModelFavorite> savedRecords = new ArrayList<MovieModelFavorite>();
   String idOMDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_favorite);

       // Intent intent = getIntent();
        //idOMDb = intent.getStringExtra("idOMDb");

        MTitle = new ArrayList<String>();
        MYear = new ArrayList<String>();
        MDirector = new ArrayList<String>();
        MPoster = new ArrayList<String>();
        imdbID_Sh_Movie=new ArrayList<String>();
        rv_ShowFavorite=findViewById(R.id.rv_ShowFavorite);

        SqLiteHelper helper = new SqLiteHelper(ShowFavoriteActivity.this, "Movies", null, 1);
        List<MovieModelFavorite> savedRecords = helper.getAllSavedRecords();

        for (int i = 0; i< savedRecords.size(); i++){

            MTitle.add(savedRecords.get(i).getTitle());
            MYear.add(savedRecords.get(i).getYear());
            MDirector.add(savedRecords.get(i).getDirector());
           imdbID_Sh_Movie.add(savedRecords.get(i).getImdbID());
            MPoster.add(savedRecords.get(i).getPoster());

        }


        LinearLayoutManager myManager = new LinearLayoutManager(ShowFavoriteActivity.this, RecyclerView.VERTICAL, false);
        rv_ShowFavorite.setLayoutManager(myManager);
        FavoriteAdapter adapter = new FavoriteAdapter(MTitle,
                                                        MYear,
                                                        MDirector,
                                                       imdbID_Sh_Movie,
                                                        MPoster);
        rv_ShowFavorite.setAdapter(adapter);

    }
}