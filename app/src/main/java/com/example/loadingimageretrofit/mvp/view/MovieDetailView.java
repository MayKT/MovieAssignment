package com.example.loadingimageretrofit.mvp.view;

import com.example.loadingimageretrofit.mvp.model.MovieListInfo;

import java.util.List;

public interface MovieDetailView extends BaseView {

    void showMovieDetail(MovieListInfo movieListInfo);
    void showSimilarMovies(List<MovieListInfo> similarVideoListModel);
    void showNoMovieInfo();

}
