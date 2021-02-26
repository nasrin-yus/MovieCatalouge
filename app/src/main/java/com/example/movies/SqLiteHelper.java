package com.example.movies;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqLiteHelper extends SQLiteOpenHelper {

    String TABLE_NAME = "MovieTable";

    public SqLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
                "_id integer primary key autoincrement," +
                "Title text," +
                "Year text," +
                "Director text," +
                "imdbID text," +
                "Poster text," +
                "Language text" +
                ")";
        db.execSQL(CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//insert into data base
    public void insertIntoMovieTable(String Title,String Year,String Director,String Language,String IdOMDb,String Poster) {
        String INSERT_INTO_MOVIETABLE_QUERY = "INSERT INTO " + TABLE_NAME
                + "(Title,Released,Genre,Actors,imdbID,Poster,Plot) VALUES(" + "'" + Title + "','"
                + Year + "','"
                + Director + "','"
                + Language + "','"
                + IdOMDb + "','"
                + Poster + "','"
                + "')";
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(INSERT_INTO_MOVIETABLE_QUERY);
        } catch (Exception ex) {}
        db.close();
    }


//get from data base
   // public List<String> getFavoritMovieNames() {
     //   String SELECT_NAMES_QUERY = "SELECT title FROM " + TABLE_NAME;
       // ArrayList<String> title = new ArrayList<>();
        //SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.rawQuery(SELECT_NAMES_QUERY, null);
        //while (cursor.moveToNext()) {
          //  String name = cursor.getString(0);
            //title.add(name);

        //}
        //db.close();
        //return title;
    //}
}

