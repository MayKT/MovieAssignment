package com.example.loadingimageretrofit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.loadingimageretrofit.Interactor.MovieDetailInteractor;
import com.example.loadingimageretrofit.mvp.model.MovieListInfo;
import com.example.loadingimageretrofit.mvp.presenter.MovieDetaiPresenterImpl;
import com.example.loadingimageretrofit.mvp.view.MovieDetailView;

import java.util.List;

import butterknife.BindView;

import static com.example.loadingimageretrofit.Constants.BASE_IMG_URL;

public class MovieDetailActivity extends BaseActivity implements MovieDetailView {


    @BindView(R.id.movie_detail_layout)
    ConstraintLayout moviedetailLayout;
    @BindView(R.id.iv_poster)
    ImageView ivMoviePoster;
    @BindView(R.id.txt_releaseyear)
    TextView txtReleasedate;
    @BindView(R.id.txt_isAdult)
    TextView isAdult;
    @BindView(R.id.txt_duration)
    TextView txtDuration;
    @BindView(R.id.txt_movieOverview)
    TextView txtMovieOverview;
    @BindView(R.id.similar_movie_rv)
    RecyclerView rvSimilarMovie;
    @BindView(R.id.layout_play)
    LinearLayout layoutPlay;
    @BindView(R.id.btn_cancel)
    ImageButton btnCancel;
    @BindView(R.id.layout_rating)
    LinearLayout layoutRating;
    @BindView(R.id.layout_mylist)
    LinearLayout layoutMylist;

   public static int mmovieId;
    Movieadapter movieadapter;
    MovieDetaiPresenterImpl movieDetailPresenter;
    ServiceHelper.ApiService mservice;
    MovieListInfo movieListInfo;
    Dialog mdialog;
    public static Intent MovieDetailActivityIntent(Context context,int movieId){
        Intent intent=new Intent(context,MovieDetailActivity.class);
        mmovieId=movieId;
        return intent;

    }
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {
        init();
    }


    private void init() {

         movieadapter=new Movieadapter();
         mdialog=new Dialog(this);
         mservice=ServiceHelper.getClient(this);
         movieDetailPresenter=new MovieDetaiPresenterImpl(new MovieDetailInteractor(mservice));
         movieListInfo=new MovieListInfo();
         rvSimilarMovie.setHasFixedSize(true);
         rvSimilarMovie.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
         rvSimilarMovie.setAdapter(movieadapter);
        txtReleasedate.setText(movieListInfo.getReleasedate());

        txtMovieOverview.setText(movieListInfo.getOverview());
        if(movieListInfo.isAdult()){
            isAdult.setText("18+");
        }
        else
            isAdult.setText(" ");

        Glide.with(MovieDetailActivity.this).load(BASE_IMG_URL + movieListInfo.getPosterpath()).into(ivMoviePoster);


        movieDetailPresenter.onAttachView(this);

         movieDetailPresenter.onUIReady(mmovieId);

         btnCancel.setOnClickListener(v -> {
             finish();
         });
         layoutPlay.setOnClickListener(v -> {

         });
         layoutMylist.setOnClickListener(v -> {

         });
         layoutRating.setOnClickListener(v -> {

         });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieDetailPresenter.onTerminate();
    }

    @Override
    public void showMovieDetail(MovieListInfo movieInfoModel) {
        txtReleasedate.setText(movieListInfo.getReleasedate());
        txtMovieOverview.setText(movieListInfo.getOverview());
        if(movieListInfo.isAdult()){
            isAdult.setText("18+");
        }
        else
            isAdult.setText(" ");

        Glide.with(MovieDetailActivity.this).load(BASE_IMG_URL + movieListInfo.getPosterpath()).into(ivMoviePoster);


    }

    @Override
    public void showSimilarMovies(List<MovieListInfo> similarVideoListModel) {
        movieadapter.clear();
        for (MovieListInfo model : similarVideoListModel) {
            movieadapter.add(model);
        }


    }

    @Override
    public void showNoMovieInfo() {

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showLoading() {
        if(!isFinishing())
            mdialog.show();

    }

    @Override
    public void hideLoading() {
        if(!isFinishing())
            mdialog.show();

    }

    @Override
    public void showToastMsg(String msg) {
        this.hideLoading();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogMsg(String title, String msg) {
        this.hideLoading();
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(getString(R.string.ok), null).show();
    }
}
