package com.example.loadingimageretrofit.Interactor;

import com.example.loadingimageretrofit.ServiceHelper;
import com.example.loadingimageretrofit.mvp.model.MovieListDelegate;
import com.example.loadingimageretrofit.mvp.model.MovieListModel;
import com.example.loadingimageretrofit.mvp.model.TrendingMovieListModelImpl;

import io.reactivex.Observable;

public class MovieInteractor {
    private ServiceHelper.ApiService mService;
    private MovieListDelegate movieListModel;

    public MovieInteractor(ServiceHelper.ApiService service) {
        this.mService = service;
        movieListModel = new TrendingMovieListModelImpl();
    }

    public Observable<MovieListModel> getTrendingMovieList(int page) {
        return this.movieListModel.getTrendingMoviesfromAPI(mService,page);

    }
    public Observable<MovieListModel> getNowPlayingMovieList(int page) {
        return this.movieListModel.getNowPlayingfromAPI(mService,page);

    }
    public Observable<MovieListModel> getTopRatedMovieList(int page) {
        return this.movieListModel.getTopRatedMoviesfromAPI(mService,page);

    }
    public Observable<MovieListModel> getUpcomingMovieList(int page) {
        return this.movieListModel.getUpcomingMoviesfromAPI(mService,page);

    }

}
