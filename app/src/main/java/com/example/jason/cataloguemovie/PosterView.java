package com.example.jason.cataloguemovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jason.cataloguemovie.Adapter.MovieAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PosterView extends AppCompatActivity {

    @BindView(R.id.img_Poster_view) ImageView imgPosterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_view);

        ButterKnife.bind(this);
        getSupportActionBar().setTitle(getIntent().getStringExtra(Movie_detail.EXTRA_JUDUL).toString());
        Glide.with(this).load(MovieAdapter.BASE_URL+MovieAdapter.IMAGE_SIZE+getIntent().getStringExtra(Movie_detail.EXTRA_POSTER).toString()).into(imgPosterView);
    }
}
