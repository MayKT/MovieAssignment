package com.example.loadingimageretrofit;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loadingimageretrofit.Interactor.MovieInteractor;
import com.example.loadingimageretrofit.mvp.model.MovieListInfo;
import com.example.loadingimageretrofit.mvp.model.MovieListModel;
import com.example.loadingimageretrofit.mvp.view.MovieView;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends BaseAdapter{
    Dialog mdialog;

    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new HomeAdapter.ViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((HomeAdapter.ViewHolder)holder).bindPost((MovieListModel)getItemsList().get(position));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindCustomHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_trending)
        TextView trending;

        @BindView(R.id.trending_rv)
        RecyclerView trending_recyclerView;

        private Context context;
        private Movieadapter movieadapter;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            ButterKnife.bind(this, view);

            mdialog=new Dialog(context);
            movieadapter = new Movieadapter();

            trending_recyclerView.setHasFixedSize(true);
            trending_recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
            trending_recyclerView.setAdapter(movieadapter);

        }

        private void bindPost(MovieListModel model) {

            for (MovieListInfo movieListInfo: model.getResults()) {
                movieadapter.add(movieListInfo);
            }

        }

    }
}
