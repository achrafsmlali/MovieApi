package com.decathlon.moviesAPI.repository;

import com.decathlon.moviesAPI.TestTools;
import com.decathlon.moviesAPI.model.Comment;
import com.decathlon.moviesAPI.model.Movie;
import com.decathlon.moviesAPI.payload.MovieRankResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void testFindAllByMovie_Id(){

        //create movies
        movieRepository.save(TestTools.createMovie(1));
        movieRepository.save(TestTools.createMovie(2));

        //create comments
        commentRepository.save(TestTools.createComment(1, 1));
        commentRepository.save(TestTools.createComment(2, 1));
        commentRepository.save(TestTools.createComment(3, 2));

        //find comments associated with movie id=1
        List<Comment> commentsMovie1 = commentRepository.findAllByMovie_Id(1);

        //check if the list contains the right comments
        assertEquals(2, commentsMovie1.size());
        assertEquals(1,commentsMovie1.get(0).getMovie().getId());
        assertEquals(1,commentsMovie1.get(1).getMovie().getId());

        //find comments associated with movie id=1
        List<Comment> commentsMovie2 = commentRepository.findAllByMovie_Id(2);

        //check if the list contains the right comments
        assertEquals(2,commentsMovie2.get(0).getMovie().getId());
        assertEquals(1, commentsMovie2.size());



    }

//    @Test
//    public void testGetMovieRanking(){
//        LocalDate date =LocalDate.of(2019, 10, 03);
//        //create movies
//        movieRepository.save(TestTools.createMovie(1));
//        movieRepository.save(TestTools.createMovie(2));
//        movieRepository.save(TestTools.createMovie(3));
//        movieRepository.save(TestTools.createMovie(4));
//
//
//        //create one movie with the date out of the defined date rang
//        Movie movieOutOfDateRange = new Movie();
//        movieOutOfDateRange.setReleaseDate(LocalDate.of(1900, 10, 03));
//        movieRepository.save(movieOutOfDateRange);
//
//        //create comments
//        commentRepository.save(TestTools.createComment(1, 3));
//        commentRepository.save(TestTools.createComment(2, 3));
//        commentRepository.save(TestTools.createComment(3, 2));
//        commentRepository.save(TestTools.createComment(4, 4));
//
//        //get Movies ranking for which the release date is in the rang
//        List<MovieRankResponse> movieRankResponses = commentRepository
//                .getMovieRanking(LocalDate.of(2018, 10, 03),
//                        LocalDate.of(2020, 10, 03));
//        //check if the nbr of movie ranks is 4
//        assertEquals(4, movieRankResponses.size());
//
//        //check if the movie with id 3 is the first ranking and with 2 comments
//        assertEquals(3, movieRankResponses.get(0).getMovieId());
//        assertEquals(2, movieRankResponses.get(0).getTotalComments());
//        assertEquals(1, movieRankResponses.get(0).getRank());
//
//        //check if the movie with id 2 has 1 comments in the ranking pos 2
//        assertEquals(2, movieRankResponses.get(1).getMovieId());
//        assertEquals(1, movieRankResponses.get(1).getTotalComments());
//        assertEquals(2, movieRankResponses.get(1).getRank());
//
//        //check if the movie with id 4 has 1 comments and also in the ranking pos 2
//        assertEquals(4, movieRankResponses.get(2).getMovieId());
//        assertEquals(1, movieRankResponses.get(2).getTotalComments());
//        assertEquals(2, movieRankResponses.get(2).getRank());
//
//        //check if the movie with id 1 has 0 comments in the ranking pos 3
//        assertEquals(1, movieRankResponses.get(3).getMovieId());
//        assertEquals(0, movieRankResponses.get(3).getTotalComments());
//        assertEquals(3, movieRankResponses.get(3).getRank());
//
//    }



}
