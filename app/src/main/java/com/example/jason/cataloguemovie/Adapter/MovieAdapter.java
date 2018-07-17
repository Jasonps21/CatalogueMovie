package com.example.jason.cataloguemovie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jason.cataloguemovie.Movie_detail;
import com.example.jason.cataloguemovie.R;
import com.example.jason.cataloguemovie.Data.movieItems;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<movieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;

    public static final String BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String IMAGE_SIZE = "w500";

    public MovieAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<movieItems> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void addItem(final movieItems item){
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public movieItems getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view==null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_movie, null);
            holder.textViewJudul = view.findViewById(R.id.tv_judul_film);
            holder.img_poster_film = view.findViewById(R.id.img_movie);
            holder.textViewDateRelase = view.findViewById(R.id.tv_tglrilis_film);
            holder.textViewDescription = view.findViewById(R.id.tv_sinopsi_film);
            holder.linearLayoutMovie = view.findViewById(R.id.linearLayoutMovie);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textViewJudul.setText(mData.get(i).getTitle());
        holder.textViewDescription.setText(mData.get(i).getSynopsis());
        holder.textViewDateRelase.setText(mData.get(i).getYear());
        Glide.with(context).load(BASE_URL+IMAGE_SIZE+mData.get(i).getPosterPath()).into(holder.img_poster_film);
//        Picasso.with(context).load(BASE_URL + IMAGE_SIZE + mData.get(i).getPosterPath())
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .into(holder.img_poster_film, new Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//                });
        holder.linearLayoutMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Movie_detail.class);
                intent.putExtra(Movie_detail.EXTRA_JUDUL, mData.get(i).getTitle());
                intent.putExtra(Movie_detail.EXTRA_RELEASE, mData.get(i).getYear());
                intent.putExtra(Movie_detail.EXTRA_SINOPSI, mData.get(i).getSynopsis());
                intent.putExtra(Movie_detail.EXTRA_BACKDROP, mData.get(i).getImageURL());
                intent.putExtra(Movie_detail.EXTRA_POSTER, mData.get(i).getPosterPath());
                context.startActivity(intent);
            }
        });
        return view;
    }

    private static class ViewHolder{
        TextView textViewJudul;
        TextView textViewDateRelase;
        TextView textViewDescription;
        ImageView img_poster_film;
        LinearLayout linearLayoutMovie;
    }
}
