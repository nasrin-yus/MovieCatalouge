package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Activity_deatails extends AppCompatActivity {
    ImageView imgPoster,imgthumbnail;
    TextView txtTitle,txtYear,txtruntime,txtplot,txtgenre,txtDirector,txtactors,txtwriters,txtlanguage;
    Button btnAddFavourit;
    String IdOMDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatails);

        Intent intent = getIntent();
        IdOMDb = intent.getStringExtra("idOMDb");

        //findviewbyid
        imgPoster=findViewById(R.id.imgPoster);
        imgthumbnail=findViewById(R.id.imgthumbnail);
        txtTitle=findViewById(R.id.txtTitle);
        txtYear=findViewById(R.id.txtYear);
        txtruntime=findViewById(R.id.txtruntime);
        txtplot=findViewById(R.id.txtplot);
        txtgenre=findViewById(R.id.txtgenre);
        txtDirector=findViewById(R.id.txtDirector);
        txtactors=findViewById(R.id.txtactors);
        txtwriters=findViewById(R.id.txtwriters);
        txtlanguage=findViewById(R.id.txtlanguage);
        btnAddFavourit=findViewById(R.id.btnAddFavourit);
//insert into table
        SqLiteHelper helper = new SqLiteHelper(Activity_deatails.this, "MovieCatalouge", null, 1);

        btnAddFavourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = txtTitle.getText().toString();
                String Year = txtYear.getText().toString();
                String Director = txtDirector.getText().toString();
                String Language = txtlanguage.getText().toString();
                String Poster = imgPoster.getTag().toString();
                helper.insertIntoMovieTable(Title,Year,Director,Language,IdOMDb,Poster);
                Toast.makeText(Activity_deatails.this, " Movie Saved", Toast.LENGTH_SHORT).show();
            }
        });


        //Movie Detail

        String urlDetail = "http://omdbapi.com/?i="+IdOMDb+"&apikey=1095eb61&r=json";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlDetail,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                DetailsMovie detailsModel = gson.fromJson(response.toString(),DetailsMovie.class);

                Picasso.get().load(detailsModel.getPoster()).fit().into(imgthumbnail);
                txtTitle.setText(detailsModel.getTitle());
                txtgenre.setText(detailsModel.getGenre());
                txtruntime.setText(detailsModel.getReleased());
                txtactors.setText("Actors :"+detailsModel.getActors());
                txtwriters.setText("Writers :"+detailsModel.getWriter());
                txtlanguage.setText(detailsModel.getLanguage());
                txtDirector.setText("Director :"+detailsModel.getDirector());
                txtYear.setText(detailsModel.getYear());
                txtplot.setText(detailsModel.getPlot());
                Picasso.get().load(detailsModel.getPoster()).fit().into(imgPoster);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(Activity_deatails.this, "error" , Toast.LENGTH_SHORT).show();
            }
        });

    }

    }

