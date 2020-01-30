package com.example.loadingimageretrofit.mvp.model;

import com.example.loadingimageretrofit.Pageable;

import java.io.Serializable;
import java.util.List;

public class MovieListModel extends MovieListInfo implements Serializable, Pageable {
    int page;
    int total_results;
    int total_pages;
    List<MovieListInfo> results;

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieListInfo> getResults() {
        return results;
    }

    public void setResults(List<MovieListInfo> results) {
        this.results = results;
    }
}
