package com.example.movies;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder

public class MovieModelFavorite {
    String Title, Year, Director, Poster, ImdbID;

    public MovieModelFavorite(String title, String year, String director, String imdbID, String poster ){
        this.Title = title;
        this.Year = year;
        this.Director = director;
        this.ImdbID = imdbID;
        this.Poster = poster;


    }


    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return ImdbID;
    }

    public String getDirector() {
        return Director;
    }

    public String getPoster() {
        return Poster;
    }
}
