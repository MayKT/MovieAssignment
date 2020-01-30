package com.example.loadingimageretrofit.Interactor;

import com.example.loadingimageretrofit.ServiceHelper;
import com.example.loadingimageretrofit.mvp.model.IMovieListInfo;
import com.example.loadingimageretrofit.mvp.model.MovieListInfoImpl;
import com.example.loadingimageretrofit.mvp.model.MovieListModel;

import io.reactivex.Observable;

public class MovieDetailInteractor {
    private ServiceHelper.ApiService service;
    private IMovieListInfo movieListInfo;
    public MovieDetailInteractor(ServiceHelper.ApiService service){
        this.service=service;
        movieListInfo=new MovieListInfoImpl();
    }
    public Observable<MovieListModel> getMovieDetails(int movieId) {
        return this.movieListInfo.getDetailsfromAPI(movieId,service);
    }
    public Observable<MovieListModel> getSimilarMovies(int movieId){
        return this.movieListInfo.getSimilarMovesfromAPI(movieId,service);
    }

}
