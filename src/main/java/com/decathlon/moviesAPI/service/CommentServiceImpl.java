package com.decathlon.moviesAPI.service;

import com.decathlon.moviesAPI.model.Comment;
import com.decathlon.moviesAPI.payload.MovieRankResponse;
import com.decathlon.moviesAPI.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllByMovie_Id(Integer movieId) {
        return commentRepository.findAllByMovie_Id(movieId);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<MovieRankResponse> getMovieRanking(LocalDate dateFrom, LocalDate dateTo) {
        return commentRepository.getMovieRanking(dateFrom,dateTo);
    }
}
