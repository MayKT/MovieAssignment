package com.example.loadingimageretrofit.mvp.model;



import com.example.loadingimageretrofit.ServiceHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.loadingimageretrofit.Constants.DEVELOPER_KEY;

public class TrendingMovieListModelImpl implements MovieListDelegate {

    @Override
    public Observable<MovieListModel> getTrendingMoviesfromAPI(ServiceHelper.ApiService service, int page) {
        return service.getTrendingMovies(DEVELOPER_KEY, "en-US", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    @Override
    public Observable<MovieListModel> getNowPlayingfromAPI(ServiceHelper.ApiService service, int page) {
//
        return service.getNowPlayingMovies("9a23905c3e7e031bc4cf6786557d362b", "en-US", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    @Override
    public Observable<MovieListModel> getUpcomingMoviesfromAPI(ServiceHelper.ApiService service, int page) {
        return service.getUpComingMovies("9a23905c3e7e031bc4cf6786557d362b", "en-US", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MovieListModel> getTopRatedMoviesfromAPI(ServiceHelper.ApiService service, int page) {
//
        return service.getTopRatedMovies("9a23905c3e7e031bc4cf6786557d362b", "en-US", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
