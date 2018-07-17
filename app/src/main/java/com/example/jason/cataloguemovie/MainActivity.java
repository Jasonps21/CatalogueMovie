package com.example.jason.cataloguemovie;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jason.cataloguemovie.Adapter.MovieAdapter;
import com.example.jason.cataloguemovie.Data.movieItems;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<movieItems>> {

    @BindView(R.id.btn_cari) Button btnCari;
    @BindView(R.id.listView_film) ListView listViewMovie;
    @BindView(R.id.et_judul) EditText etJudul;

    private MovieAdapter adapter;

    static final String JUDUL_FILM = "JUDUL_FILM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listViewMovie = findViewById(R.id.listView_film);
        listViewMovie.setAdapter(adapter);

        btnCari = findViewById(R.id.btn_cari);
        etJudul = findViewById(R.id.et_judul);
        btnCari.setOnClickListener(myListener);

        String judul = etJudul.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(JUDUL_FILM, judul);
        getLoaderManager().initLoader(0, bundle, this);
    }

    @Override
    public Loader<ArrayList<movieItems>> onCreateLoader(int i, final Bundle bundle) {
        String judul = "";
        if(bundle!=null){
            judul = bundle.getString(JUDUL_FILM);
        }
        return new MyAsyncTaskLoader(this, judul);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<movieItems>> loader, ArrayList<movieItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<movieItems>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String judul = etJudul.getText().toString();
            //if(TextUtils.isEmpty(judul))return;
            Bundle bundle = new Bundle();
            bundle.putString(JUDUL_FILM, judul);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}
