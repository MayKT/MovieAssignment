package com.example.loadingimageretrofit.mvp.presenter;

import android.content.Context;

import com.example.loadingimageretrofit.Interactor.MovieDetailInteractor;
import com.example.loadingimageretrofit.R;
import com.example.loadingimageretrofit.mvp.model.MovieListInfo;
import com.example.loadingimageretrofit.mvp.model.MovieListModel;
import com.example.loadingimageretrofit.mvp.view.MovieDetailView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MovieDetaiPresenterImpl extends BasePresenter implements MovieDetailPresenter {

   private MovieDetailView movieDetailView=null;
   private MovieDetailInteractor movieDetailInteractor;

   public MovieDetaiPresenterImpl(MovieDetailInteractor movieDetailInteractor){
       this.movieDetailInteractor=movieDetailInteractor;
   }

    @Override
    public void onUIReady(int movieId) {

       showMovieDetailsById(movieId);
       showSimilarVideosById(movieId);
    }

    @Override
    public void onAttachView(MovieDetailView view) {
    this.movieDetailView=view;
    }

    @Override
    public void showMovieDetailsById(int movieId) {
       this.movieDetailInteractor.getMovieDetails(movieId).subscribe(new Observer<MovieListInfo>() {
           @Override
           public void onSubscribe(Disposable d) {
               addDisposableOberver(d);
           }

           @Override
           public void onNext(MovieListInfo movieListInfo) {
               movieDetailView.showMovieDetail(movieListInfo);
           }

           @Override
           public void onError(Throwable e) {
               movieDetailView.hideLoading();
               movieDetailView.showDialogMsg("Error in connecting movie details",e.getLocalizedMessage());
           }

           @Override
           public void onComplete() {
               movieDetailView.hideLoading();
           }
       });

    }

    @Override
    public void showSimilarVideosById(int movieId) {
       this.movieDetailInteractor.getSimilarMovies(movieId).subscribe(new Observer<MovieListModel>() {
           @Override
           public void onSubscribe(Disposable d) {
               addDisposableOberver(d);
           }

           @Override
           public void onNext(MovieListModel movieListModel) {
               if (movieListModel != null) {

                   if (movieListModel.getResults().isEmpty()) {
                      movieDetailView.showNoMovieInfo();
                   } else {

                       movieDetailView.showSimilarMovies(movieListModel.getResults());

                   }

               } else {
                   movieDetailView.showDialogMsg(movieDetailView.context().getResources().getString(R.string.error_connecting),
                           movieDetailView.context().getResources().getString(R.string.please_check_your_internet_connection));
               }

           }

           @Override
           public void onError(Throwable e) {
            movieDetailView.hideLoading();
            movieDetailView.showDialogMsg("Error in Connecting similar movies",e.getLocalizedMessage());
           }
           @Override
           public void onComplete() {
               movieDetailView.hideLoading();
           }
       });

    }
}
