package com.decathlon.moviesAPI.payload;

public class MovieRankResponse {
    private Integer movieId;
    private Integer totalComments;
    private Integer rank;

    public MovieRankResponse(Integer movieId, Integer totalComments, Integer rank) {
        this.movieId = movieId;
        this.totalComments = totalComments;
        this.rank = rank;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
