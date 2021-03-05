package com.example.movies;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqLiteHelper extends SQLiteOpenHelper {

    String TABLE_NAME = "Movies";

    public SqLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MOVIES_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
                "_id integer primary key autoincrement," +
                "Title text," +
                "Year text," +
                "Director text," +
                "imdbID text," +
                "Poster text"+")";
        db.execSQL(CREATE_MOVIES_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//insert into data base
    public void insertIntoMoviesTable(String Title,
                                      String Year,
                                      String Director,
                                      String IdOMDb,
                                      String Poster) {

        String INSERT_INTO_MOVIES_TABLE_QUERY="INSERT INTO " + TABLE_NAME
                +"(Title,Year,Director,IdOMDb,Poster) VALUES" +
                "(" + "'" + Title + "'," + "'" + Year + "'," + Director+ "'," + "'" +IdOMDb+ "'," + "'" +Poster
                + ")";



                SQLiteDatabase db = this.getWritableDatabase();
       try {
            db.execSQL(INSERT_INTO_MOVIES_TABLE_QUERY);

        }catch (Exception ex) {}
        db.close();
    }


//get from data base


    public List<MovieModelFavorite> getAllSavedRecords(){
      // String SELECT_ALL_QUERY = "SELECT " +
        //      "_id integer, " +
          //  "Title, " +
            //    "Year, " +
              //  "Director, " +
               // "IdOMDb, " +
               // "Poster "+
               // "FROM " + TABLE_NAME;
       String SELECT_ALL_QUERY = "SELECT * FROM " + TABLE_NAME;

        ArrayList<MovieModelFavorite> SavedRecords = new ArrayList<MovieModelFavorite>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL_QUERY, null);

        while (cursor.moveToNext()){

            String Title = cursor.getString(1);
            String Year = cursor.getString(2);
            String Director = cursor.getString(3);
            String IdOMDb = cursor.getString(4);
            String Poster = cursor.getString(5);
            SavedRecords.add(new MovieModelFavorite(Title,Year,Director,IdOMDb,Poster));

        }
        //cursor.close();
        db.close();
        return SavedRecords;
    }
}

