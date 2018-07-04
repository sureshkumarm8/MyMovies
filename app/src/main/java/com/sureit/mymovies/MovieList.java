package com.sureit.mymovies;

public class MovieList {

    private String title;
    private String posterUrl;
    private String description;
    private String vote_average;
    private String releaseDate;

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getVote_average(){
        return vote_average;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public MovieList(String title, String description, String posterUrl, String vote_average, String releaseDate) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.description = description;
        this.vote_average=vote_average;
        this.releaseDate = releaseDate;
    }}
