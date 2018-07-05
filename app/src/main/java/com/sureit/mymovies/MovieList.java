package com.sureit.mymovies;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieList implements Parcelable {

    private String title;
    private String posterUrl;
    private String description;
    private String vote_average;
    private String releaseDate;

    protected MovieList(Parcel in) {
        title = in.readString();
        posterUrl = in.readString();
        description = in.readString();
        vote_average = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<MovieList> CREATOR = new Creator<MovieList>() {
        @Override
        public MovieList createFromParcel(Parcel in) {
            return new MovieList(in);
        }

        @Override
        public MovieList[] newArray(int size) {
            return new MovieList[size];
        }
    };

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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterUrl);
        dest.writeString(description);
        dest.writeString(vote_average);
        dest.writeString(releaseDate);
    }
}
