package com.decathlon.moviesAPI.service;

import com.decathlon.moviesAPI.model.Comment;
import com.decathlon.moviesAPI.payload.MovieRankResponse;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    List<Comment> findAllByMovie_Id(Integer movieId);

    List<Comment> findAll();

    List<MovieRankResponse> getMovieRanking(LocalDate dateFrom, LocalDate dateTo);
}
