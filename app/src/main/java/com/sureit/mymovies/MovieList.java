package com.sureit.mymovies;

public class MovieList {

    private String title;
    private String poster_url;
    private String description;
    private String vote_average;
    private String release_date;

    public String getTitle() {
        return title;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public String getDescription() {
        return description;
    }

    public String getVote_average(){
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public MovieList(String title, String description, String poster_url, String vote_average, String release_date) {
        this.title = title;
        this.poster_url = poster_url;
        this.description = description;
        this.vote_average=vote_average;
        this.release_date=release_date;
    }}
