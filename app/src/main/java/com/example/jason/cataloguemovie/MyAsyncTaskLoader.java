package com.example.jason.cataloguemovie;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;

import com.example.jason.cataloguemovie.Data.movieItems;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<movieItems>> {

    private ArrayList<movieItems> mData;
    private boolean mHasResult = false;
    private String mJudulFilm;

    public MyAsyncTaskLoader(final Context context, String mJudulFilm) {
        super(context);
        onContentChanged();
        this.mJudulFilm = mJudulFilm;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()) forceLoad();
        else if (mHasResult) deliverResult(mData);
    }

    @Override
    public void deliverResult(ArrayList<movieItems> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResource(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = "7c445a6784e1994f25403363c0474971";

    @Override
    public ArrayList<movieItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<movieItems> moviedata = new ArrayList<>();
        String url = "";
        if (TextUtils.isEmpty(mJudulFilm)) {
            url = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY;
        } else if (!TextUtils.isEmpty(mJudulFilm)) {
            url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language=en-US&query=" + mJudulFilm;
        }
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responObject = new JSONObject(result);
                    int page = responObject.getInt("page");
                    JSONArray movieList = responObject.getJSONArray("results");

                    for (int i = 0; i < movieList.length(); i++) {
                        JSONObject data = movieList.getJSONObject(i);
                        movieItems movieItems1 = new movieItems(data, page);
                        moviedata.add(movieItems1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return moviedata;
    }

    protected void onReleaseResource(ArrayList<movieItems> mData) {

    }
}