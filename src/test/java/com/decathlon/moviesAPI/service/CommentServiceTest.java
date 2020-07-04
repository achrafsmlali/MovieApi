package com.decathlon.moviesAPI.service;

import com.decathlon.moviesAPI.TestTools;
import com.decathlon.moviesAPI.model.Comment;
import com.decathlon.moviesAPI.model.Movie;
import com.decathlon.moviesAPI.payload.MovieRankResponse;
import com.decathlon.moviesAPI.repository.CommentRepository;
import com.decathlon.moviesAPI.service.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void testFindAllByMovie_Id(){
        //create a liste of comments
        Comment comment1 = TestTools.createComment(1,1);
        Comment comment2 = TestTools.createComment(2,1);
        List<Comment> comments = new ArrayList<Comment>();
        comments.add(comment1);
        comments.add(comment2);

        //return the list of comments if commentRepository.findAllByMovie_Id(1) is invoked
        Mockito.when(commentRepository.findAllByMovie_Id(1)).thenReturn(comments);

        //find the comments associated to the movie id=1
        List<Comment> commentList = commentService.findAllByMovie_Id(1);

        //check if the comments returned are the ones associated to the movie id=1
        assertEquals(2,commentList.size());
        assertEquals(1, commentList.get(0).getMovie().getId());
        assertEquals(1, commentList.get(1).getMovie().getId());
    }

    @Test
    public void testSave(){
        //create a comment
        Comment comment = TestTools.createComment(1, 1);
        //save the comment
        commentService.save(comment);
        //check if the method save of commentRepository was invoked
        verify(commentRepository,times(1)).save(comment);
    }

    @Test
    public void testFindAll(){
        //create a list of comments
        List<Comment> comments = new ArrayList<Comment>();
        comments.add(TestTools.createComment(1, 1));
        comments.add(TestTools.createComment(2, 1));

        //return the lists if commentService.findAll() is invoked
        Mockito.when(commentService.findAll()).thenReturn(comments);

        //find all movies
        List<Comment> commentList = commentService.findAll();


        assertEquals(2, commentList.size());
        //check if the method findAll of commentRepository was invoked
        verify(commentRepository, times(1)).findAll();

    }

    @Test
    public void testGetMovieRanking(){
        //create a list of movie ranking
        MovieRankResponse mRR2 = new MovieRankResponse(1,3,1);
        MovieRankResponse mRR1 = new MovieRankResponse(2,2,2);
        List<MovieRankResponse> movieRankResponses = new ArrayList<MovieRankResponse>();
        movieRankResponses.add(mRR1);
        movieRankResponses.add(mRR2);

        LocalDate dateFrom = LocalDate.of(1900,02,02);
        LocalDate dateTo = LocalDate.of(1900,02,02);

        //return the list of movie ranking if the method getMovieRanking of commentRepository is invoked
        Mockito.when(commentRepository.getMovieRanking(dateFrom,dateTo)).thenReturn(movieRankResponses);

        //find movie rankings in date range
        List<MovieRankResponse> movieRankResponseList = commentService.getMovieRanking(dateFrom, dateTo);

        //check the size of the response list is correct
        assertEquals(2, movieRankResponseList.size());
    }


}
