package com.example.loadingimageretrofit.mvp.model;

import com.example.loadingimageretrofit.Pageable;

import java.io.Serializable;

public class MovieListInfo implements Serializable, Pageable {
        String poster_path;
        boolean adult;
        String overview;
        String release_date;
        int[] genre_ids;
        int id;
        String original_title;
        String original_language;
        String title;
        Number popularity;
        String backdrop_path;
        int vote_count;
        boolean video;
        Number video_average;
        public int[] getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(int[] genre_ids) {
            this.genre_ids = genre_ids;
        }
        public String getPosterpath() {
            return poster_path;
        }

        public void setPosterpath(String posterpath) {
            this.poster_path = posterpath;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getReleasedate() {
            return release_date;
        }

        public void setReleasedate(String releasedate) {
            this.release_date = releasedate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Number getPopularity() {
            return popularity;
        }

        public void setPopularity(Number popularity) {
            this.popularity = popularity;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getVote_count() {
            return vote_count;
        }

        public boolean isVideo() {
            return video;
        }

        public Number getVideo_average() {
            return video_average;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public void setVideo_average(Number video_average) {
            this.video_average = video_average;
        }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}


