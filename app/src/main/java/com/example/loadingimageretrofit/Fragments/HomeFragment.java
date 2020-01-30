package com.example.loadingimageretrofit.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loadingimageretrofit.Interactor.MovieInteractor;
import com.example.loadingimageretrofit.Movieadapter;
import com.example.loadingimageretrofit.R;
import com.example.loadingimageretrofit.ServiceHelper;

import com.example.loadingimageretrofit.mvp.model.MovieListInfo;
import com.example.loadingimageretrofit.mvp.presenter.MoviePresenterImpl;

import com.example.loadingimageretrofit.mvp.view.MovieView;

import java.util.List;


public class HomeFragment extends Fragment implements MovieView {
    public HomeFragment() {

    }

    Movieadapter adapter;
    private RecyclerView recyclerView;
    private MoviePresenterImpl MoviePresenter;
    RecyclerView secondrecyclerview, thirdrecyclerview, fourthrecyclerview;
    Movieadapter secondapdater, thirdadapter, fourthadapter;

    ServiceHelper.ApiService service;
    Context context;
    ProgressBar progressBar;
    Dialog mdialog;
    int page = 1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);
        context = view.getContext();

        adapter = new Movieadapter();

        service = ServiceHelper.getClient(context);

        MoviePresenter = new MoviePresenterImpl(new MovieInteractor(service));
        mdialog=new Dialog(context);

        secondapdater = new Movieadapter();
        thirdadapter = new Movieadapter();
        fourthadapter = new Movieadapter();

        recyclerView = view.findViewById(R.id.first_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        secondrecyclerview = view.findViewById(R.id.second_recycler_view);
        secondrecyclerview.setHasFixedSize(true);
        secondrecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        secondrecyclerview.setAdapter(secondapdater);
        thirdrecyclerview = view.findViewById(R.id.third_recycler_view);
        thirdrecyclerview.setHasFixedSize(true);
        thirdrecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        thirdrecyclerview.setAdapter(thirdadapter);
        fourthrecyclerview = view.findViewById(R.id.fourth_recycler_view);
        fourthrecyclerview.setHasFixedSize(true);
        fourthrecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        fourthrecyclerview.setAdapter(fourthadapter);


        MoviePresenter.onAttachView(this);
        MoviePresenter.onUIReady();
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        MoviePresenter.onTerminate();

    }

    @Override
    public void addMoreMoviesToTheList(List<MovieListInfo> movieInfoModelList) {
        for (MovieListInfo model : movieInfoModelList) {
            adapter.add(model);
        }
        for (MovieListInfo model : movieInfoModelList) {
            secondapdater.add(model);
        }

    }

    @Override
    public void showMovieList(List<MovieListInfo> movieInfoModelList) {
        page = 1;
        adapter.clear();
        for (MovieListInfo model : movieInfoModelList) {
            adapter.add(model);
        }


    }

    @Override
    public void showUpComingMovieList(List<MovieListInfo> movieInfoModelList) {
        thirdadapter.clear();
        for (MovieListInfo model : movieInfoModelList) {
            thirdadapter.add(model);
        }
    }

    @Override
    public void showTopRatedMovieList(List<MovieListInfo> movieInfoModelList) {
        fourthadapter.clear();
        for (MovieListInfo model : movieInfoModelList) {
            fourthadapter.add(model);
        }
    }

    @Override
    public void showNowShowingMovieList(List<MovieListInfo> movieInfoModelList) {
        secondapdater.clear();
        for (MovieListInfo model : movieInfoModelList) {
            secondapdater.add(model);
        }
    }


    @Override
    public void resetPageNumberToDefault() {
        page--;

    }

    @Override
    public void showNoMovieInfo() {

    }

    @Override
    public Context context() {
        //context= newInstance("","").getContext();
        context = getContext();
        return context;
    }

    @Override
    public void showLoading() {
        if (!isRemoving()) {
            mdialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (!isRemoving()) {
            mdialog.hide();
        }
    }

    @Override
    public void showToastMsg(String msg) {
        this.hideLoading();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogMsg(String title, String msg) {
        this.hideLoading();
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(getString(R.string.ok), null).show();
    }

}
