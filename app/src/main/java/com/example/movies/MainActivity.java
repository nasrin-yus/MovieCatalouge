package com.example.movies;


//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvMovies,rvFavorits;
    EditText edtSearch;
    Button btnSearch,btnShowMyFavourit;
    ArrayList<String> MTitle,MYear,MDirector,MPoster,imdbID_Sh_Movie;
    String idOMDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //My Array List
        MTitle = new ArrayList<>();
        MYear = new ArrayList<>();
        MDirector = new ArrayList<>();
        MPoster = new ArrayList<>();
        imdbID_Sh_Movie=new ArrayList<>();



        setContentView(R.layout.activity_main);
       //findviewbyid
       edtSearch=findViewById(R.id.edtSearch);
       btnSearch=findViewById(R.id.btnSearch);
       rvMovies=findViewById(R.id.rvMovies);
       rvFavorits=findViewById(R.id.rvFavorits);
       btnShowMyFavourit=findViewById(R.id.btnShowMyFavourit);


       //show favorite
        btnShowMyFavourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this, ShowFavoriteActivity.class);
                //intent.putExtra("idOMDb",idOMDb);
               startActivity(intent);

            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String moviename = edtSearch.getText().toString();
                if (moviename.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please ProvideMovie Name", Toast.LENGTH_SHORT).show();
                     return;
               }
                AsyncHttpClient client=new AsyncHttpClient();
                String url="http://omdbapi.com/?s="+moviename+"&apikey=1095eb61&r=json";
               client.get(url, new JsonHttpResponseHandler() {
                   @Override
                   public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                       super.onSuccess(statusCode, headers, response);
                       Gson gson = new Gson();
                       MovieModel model = gson.fromJson(response.toString(),MovieModel.class);
                       try {
                           for(int i =0;i < model.getSearch().size();i++) {
                               MTitle.add(model.getSearch().get(i).getTitle());
                               MYear.add(model.getSearch().get(i).getYear());
                               MDirector.add(model.getSearch().get(i).getImdbID());
                               MPoster.add(model.getSearch().get(i).getPoster());
                               imdbID_Sh_Movie.add(model.getSearch().get(i).getImdbID());

                           }
                       }catch (Exception ex){}
                       LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
                       rvMovies.setLayoutManager(manager);
                       MovieAdapter adapter = new MovieAdapter(MTitle, MYear, MDirector, MPoster,imdbID_Sh_Movie);
                       rvMovies.setAdapter(adapter);


                }

                 @Override
                 public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                     super.onFailure(statusCode, headers, throwable, errorResponse);
                   Toast.makeText(MainActivity.this, "error" , Toast.LENGTH_SHORT).show();

               }
                });            }


        });            }


    public void ShowDetailsActivity(Context context, String idOMDb)
    {
        Intent intent = new Intent(context, Activity_deatails.class);
        intent.putExtra("idOMDb", idOMDb);
        context.startActivity(intent);
    }


}
