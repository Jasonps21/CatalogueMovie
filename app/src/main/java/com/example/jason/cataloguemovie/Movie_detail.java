package com.example.jason.cataloguemovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jason.cataloguemovie.Adapter.MovieAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Movie_detail extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_judul_film_detail)
    TextView tvMovieName;
    @BindView(R.id.img_Poster)
    ImageView imgPoster;
    @BindView(R.id.img_backdrop)
    ImageView imgBackdrop;
    @BindView(R.id.tv_tanggal_rilis_detail)
    TextView tvYear;
    @BindView(R.id.tv_sinopsi_detail)
    TextView tvSynopsis;

    private int movieid;
    String posterPath, nama;

    public static String EXTRA_JUDUL = "extra_judul";
    public static String EXTRA_RELEASE = "extra_release";
    public static String EXTRA_BACKDROP = "extra_bacdrop";
    public static String EXTRA_POSTER = "extra_poster";
    public static String EXTRA_SINOPSI = "extra_sinopsi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);
        nama = getIntent().getStringExtra(EXTRA_JUDUL).toString();
        posterPath = getIntent().getStringExtra(EXTRA_POSTER).toString();
        getSupportActionBar().setTitle(nama.toString());
        initialize();
        tvMovieName.setText(nama.toString());
        tvYear.setText(getIntent().getStringExtra(EXTRA_RELEASE).toString());
        tvSynopsis.setText(getIntent().getStringExtra(EXTRA_SINOPSI).toString());
        Glide.with(this).load(MovieAdapter.BASE_URL + MovieAdapter.IMAGE_SIZE + getIntent().getStringExtra(EXTRA_BACKDROP).toString()).into(imgBackdrop);
        Glide.with(this).load(MovieAdapter.BASE_URL + MovieAdapter.IMAGE_SIZE + posterPath).into(imgPoster);
        imgPoster.setOnClickListener(this);
    }

    private void initialize() {
        tvMovieName = findViewById(R.id.tv_judul_film_detail);
        imgPoster = findViewById(R.id.img_Poster);
        imgBackdrop = findViewById(R.id.img_backdrop);
        tvYear = findViewById(R.id.tv_tanggal_rilis_detail);
        tvSynopsis = findViewById(R.id.tv_sinopsi_detail);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_Poster) {
            Intent intent = new Intent(this, PosterView.class);
            intent.putExtra(EXTRA_POSTER, posterPath);
            intent.putExtra(EXTRA_JUDUL, nama);
            startActivity(intent);
        }
    }
}
