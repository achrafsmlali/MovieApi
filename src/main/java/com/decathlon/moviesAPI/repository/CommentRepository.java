package com.decathlon.moviesAPI.repository;

import com.decathlon.moviesAPI.model.Comment;
import com.decathlon.moviesAPI.payload.MovieRankResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(name = "getMovieRanking", nativeQuery = true)
    public List<MovieRankResponse> getMovieRanking(LocalDate dateFrom, LocalDate dateTO);

    public List<Comment> findAllByMovie_Id(Integer movieId);
}