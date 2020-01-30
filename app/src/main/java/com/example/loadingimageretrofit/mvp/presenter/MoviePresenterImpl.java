package com.example.loadingimageretrofit.mvp.presenter;

import android.util.Log;

import com.example.loadingimageretrofit.Interactor.MovieInteractor;
import com.example.loadingimageretrofit.R;
import com.example.loadingimageretrofit.ServiceHelper;
import com.example.loadingimageretrofit.mvp.model.MovieListModel;
import com.example.loadingimageretrofit.mvp.view.MovieView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MoviePresenterImpl extends BasePresenter implements MoviePresenter {

    private MovieView movieView=null;
    private MovieInteractor movieInteractor;

    public MoviePresenterImpl(MovieInteractor movieInteractor){
        this.movieInteractor=movieInteractor;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        this.movieView=null;
    }
    @Override
    public void onAttachView(MovieView view) {
        this.movieView=view;

    }
    @Override
    public void onUIReady() {
        getTrendingMovies();
        getNowPlayingMovies();
        getUpComingMovies();
        getTopRatedMovies();
    }
    private ServiceHelper.ApiService mService;
    @Override
    public void getTrendingMovies() {
        //movieListModel=new TrendingMovieListModelImpl();
        movieView.showLoading();
        this.movieInteractor.getTrendingMovieList(1).subscribe(new Observer<MovieListModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposableOberver(d);

            }

            @Override
            public void onNext(MovieListModel movieListModel) {

                if (movieListModel != null) {

                    if (movieListModel.getResults().isEmpty()) {
                        movieView.showNoMovieInfo();
                    } else {
                        movieView.showMovieList(movieListModel.getResults());

                    }

                } else {
                    movieView.showDialogMsg(movieView.context().getResources().getString(R.string.error_connecting),
                            movieView.context().getResources().getString(R.string.please_check_your_internet_connection));
                }
            }

            @Override
            public void onError(Throwable e) {

                movieView.hideLoading();
                movieView.showDialogMsg(movieView.context().getResources().getString(R.string.error_connecting),
                        e.getLocalizedMessage());

            }

            @Override
            public void onComplete() {
                movieView.hideLoading();

            }
        });

    }

    @Override
    public void getNowPlayingMovies() {
        this.movieInteractor.getNowPlayingMovieList(1)
                .subscribe(new Observer<MovieListModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposableOberver(d);
                    }

                    @Override
                    public void onNext(MovieListModel movieListModel) {
                        if (movieListModel != null) {

                            if (movieListModel.getResults().isEmpty()) {
                                movieView.showNoMovieInfo();
                            } else {
                                movieView.showNowShowingMovieList(movieListModel.getResults());

                            }

                        } else {
                            movieView.showDialogMsg(movieView.context().getResources().getString(R.string.error_connecting),
                                    movieView.context().getResources().getString(R.string.please_check_your_internet_connection));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        movieView.hideLoading();
                        movieView.showDialogMsg(movieView.context().getResources().getString(R.string.error_connecting),
                                e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        movieView.hideLoading();
                    }
                });
    }

    @Override
    public void getTopRatedMovies() {
        this.movieInteractor.getTopRatedMovieList(1)
                .subscribe(new Observer<MovieListModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposableOberver(d);
                    }

                    @Override
                    public void onNext(MovieListModel movieListModel) {
                        if (movieListModel != null) {

                            if (movieListModel.getResults().isEmpty()) {
                                movieView.showNoMovieInfo();
                            } else {
                                movieView.showTopRatedMovieList(movieListModel.getResults());

                            }

                        } else {
                            movieView.showDialogMsg(movieView.context().getResources().getString(R.string.error_connecting),
                                    movieView.context().getResources().getString(R.string.please_check_your_internet_connection));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        movieView.hideLoading();
                        movieView.showDialogMsg(movieView.context().getResources().getString(R.string.error_connecting),
                                e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        movieView.hideLoading();
                    }
                });

    }

    @Override
    public void getUpComingMovies() {
        this.movieInteractor.getUpcomingMovieList(1)
                .subscribe(new Observer<MovieListModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposableOberver(d);
                    }

                    @Override
                    public void onNext(MovieListModel movieListModel) {
                        if (movieListModel != null) {

                            if (movieListModel.getResults().isEmpty()) {
                                movieView.showNoMovieInfo();
                            } else {
                                movieView.showUpComingMovieList(movieListModel.getResults());

                            }

                        } else {
                            movieView.showDialogMsg(movieView.context().getResources().getString(R.string.error_connecting),
                                    movieView.context().getResources().getString(R.string.please_check_your_internet_connection));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        movieView.hideLoading();
                        movieView.showDialogMsg(movieView.context().getResources().getString(R.string.error_connecting),
                                e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        movieView.hideLoading();
                    }
                });


    }


}
