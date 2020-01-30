package com.example.loadingimageretrofit.mvp.presenter;


import com.example.loadingimageretrofit.mvp.view.MovieView;

public interface MoviePresenter {

    void onUIReady();
    void onAttachView(MovieView view);
    void getTrendingMovies();
    void getNowPlayingMovies();
    void getTopRatedMovies();
    void getUpComingMovies();


}
