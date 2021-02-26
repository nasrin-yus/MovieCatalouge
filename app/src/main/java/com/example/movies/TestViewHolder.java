package com.example.movies;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle;
    TextView txtYear;
    TextView txtDirector;
    ImageView imgPoster;
    public TestViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtYear = itemView.findViewById(R.id.txtYear);
        txtDirector = itemView.findViewById(R.id.txtDirector);
        imgPoster=itemView.findViewById(R.id.imgPoster);

    }
}
