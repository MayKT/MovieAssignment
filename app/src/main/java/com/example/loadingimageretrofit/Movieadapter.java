package com.example.loadingimageretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loadingimageretrofit.mvp.model.MovieListInfo;
import com.example.loadingimageretrofit.mvp.model.MovieListModel;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.loadingimageretrofit.Constants.BASE_IMG_URL;


public class Movieadapter extends BaseAdapter {
//    List<MovieListModel> movieListModels;
//    public Movieadapter(List<MovieListModel> movieListModels,Context context){
//
//this.movieListModels=movieListModels;
//    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizonal_movie_item, parent, false);

        return new Movieadapter.ViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Movieadapter.ViewHolder) holder).bind((MovieListInfo) getItemsList().get(position), position);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindCustomHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Context context;
        @BindView(R.id.card_view_home)
        CardView cardView;
        @BindView(R.id.image_view_movie)
        ImageView imageView;
        @BindView(R.id.txt_moviename)
        TextView txt_moviename;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bind(MovieListInfo model, int position) {
            Glide.with(context).load(BASE_IMG_URL + model.getPosterpath()).into(imageView);
            txt_moviename.setText(model.getTitle());

            itemView.setOnClickListener( v -> {
                context.startActivity(MovieDetailActivity.MovieDetailActivityIntent(context,model.getId()));
            });
        }
    }


}
