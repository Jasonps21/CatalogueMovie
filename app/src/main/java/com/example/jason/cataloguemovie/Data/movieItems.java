package com.example.jason.cataloguemovie.Data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class movieItems implements Parcelable {
    private int page;
    private int id;
    private String imageURL;
    private String posterPath;
    private String title;
    private String year;
    private double rating;
    private String synopsis;
    private boolean favorite;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public movieItems(){

    }

    public movieItems(JSONObject object, int page) {
        try {
                int id = object.getInt("id");
                String posterUrl = object.getString("poster_path");
                String title = object.getString("original_title");
                String year = object.getString("release_date");
                double rating = object.getDouble("vote_average");
                String synopsis = object.getString("overview");
                String imgUrl = object.getString("backdrop_path");
                this.id = id;
                this.page = page;
                this.imageURL = imgUrl;
                this.title = title;
                this.synopsis = synopsis;
                this.posterPath = posterUrl;
                this.year = year;
                this.rating = rating;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }


    public int getId() {
        return (id);
    }

    public String getImageURL() {
        return (imageURL);
    }

    public String getTitle() {
        return (title);
    }

    public String getYear() {
        return (year);
    }

    public double getRating() {
        return (rating);
    }

    public String getSynopsis() {
        return (synopsis);
    }

    public boolean isFavotite() {
        return (favorite);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
