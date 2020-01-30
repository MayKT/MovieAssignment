package com.example.loadingimageretrofit.mvp.presenter;

import com.example.loadingimageretrofit.mvp.view.BaseView;
import com.example.loadingimageretrofit.mvp.view.MovieDetailView;

public interface MovieDetailPresenter {

    void onUIReady(int movieId);
    void onAttachView(MovieDetailView view);
    void showMovieDetailsById(int movieId);
    void showSimilarVideosById(int movieId);
}
