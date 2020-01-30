package com.example.loadingimageretrofit.mvp.view;

import com.example.loadingimageretrofit.mvp.model.MovieListInfo;


import java.util.List;

public interface MovieView extends BaseView {

    void addMoreMoviesToTheList(List<MovieListInfo> movieInfoModelList);
    void showMovieList(List<MovieListInfo> movieInfoModelList);

    void showUpComingMovieList(List<MovieListInfo> movieInfoModelList);
    void showTopRatedMovieList(List<MovieListInfo> movieInfoModelList);
    void showNowShowingMovieList(List<MovieListInfo> movieInfoModelList);
    void resetPageNumberToDefault();
    void showNoMovieInfo();
}
