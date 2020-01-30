package com.example.loadingimageretrofit.mvp.model;

import com.example.loadingimageretrofit.ServiceHelper;

import io.reactivex.Observable;

public interface IMovieListInfo {

    Observable<MovieListModel> getDetailsfromAPI( int movieId,ServiceHelper.ApiService service);

    Observable<MovieListModel> getSimilarMovesfromAPI(int movieId,ServiceHelper.ApiService service);
}
