package com.example.loadingimageretrofit.mvp.model;

import com.example.loadingimageretrofit.ServiceHelper;

import io.reactivex.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.loadingimageretrofit.Constants.DEVELOPER_KEY;

public class MovieListInfoImpl implements IMovieListInfo{
    @Override
    public Observable<MovieListModel> getDetailsfromAPI( int movieId,ServiceHelper.ApiService service) {
        return service.getMovieDetails(movieId,DEVELOPER_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MovieListModel> getSimilarMovesfromAPI(int movieId,ServiceHelper.ApiService service) {
        return service.getSimilarMoives(movieId,DEVELOPER_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
