package com.decathlon.moviesAPI.controller;

import com.decathlon.moviesAPI.exception.MovieNotFoundException;
import com.decathlon.moviesAPI.model.Comment;
import com.decathlon.moviesAPI.model.Movie;
import com.decathlon.moviesAPI.payload.CommentRequest;
import com.decathlon.moviesAPI.payload.DateRangRequest;
import com.decathlon.moviesAPI.payload.MovieRankResponse;
import com.decathlon.moviesAPI.service.CommentService;
import com.decathlon.moviesAPI.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    MovieService movieService;

    @PostMapping("/comments")
    @ResponseBody
    public Comment postComment(@RequestBody CommentRequest commentRequest){
        //find the movie for which we'll associate the comment
        Optional<Movie> movieOptional = movieService.findById(commentRequest.getMovieId());

        //if the movie does'nt exist throw not found  exception
        if (!movieOptional.isPresent()){
            throw new MovieNotFoundException(commentRequest.getMovieId());
        }

        Movie movie = movieOptional.get();

        //create comment
        Comment comment = new Comment();
        comment.setContent(commentRequest.getCommentContent());
        comment.setMovie(movie);

        //insert the comment
        Comment insertedComment = commentService.save(comment);

        return  insertedComment ;
    }

    @GetMapping("/comments")
    @ResponseBody
    public List<Comment> getComments(@RequestParam(name = "id", required = false) Integer movieId){

        //check if the RequestParam movieId was passed
       if (movieId != null){

           //check if a movie with such id exist
           if (!movieService.existsById(movieId)){
               throw new MovieNotFoundException(movieId);
           }
           //return comments by movieId
           return commentService.findAllByMovie_Id(movieId);
       }
        //return all comments
        return commentService.findAll();
    }

    @GetMapping("/top")
    @ResponseBody
    public List<MovieRankResponse> getMovieRaking(@RequestBody(required = true) DateRangRequest dateRangRequest){
        return commentService.getMovieRanking(dateRangRequest.getDateFrom(),dateRangRequest.getDateTo());
    }

}
