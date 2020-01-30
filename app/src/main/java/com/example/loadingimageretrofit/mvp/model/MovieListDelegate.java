package com.example.loadingimageretrofit.mvp.model;

import com.example.loadingimageretrofit.ServiceHelper;


import io.reactivex.Observable;

public interface MovieListDelegate {

    Observable<MovieListModel> getTrendingMoviesfromAPI(ServiceHelper.ApiService service, int page);
    Observable<MovieListModel> getNowPlayingfromAPI(ServiceHelper.ApiService service, int page);
    Observable<MovieListModel> getUpcomingMoviesfromAPI(ServiceHelper.ApiService service, int page);
    Observable<MovieListModel> getTopRatedMoviesfromAPI(ServiceHelper.ApiService service, int page);





}
