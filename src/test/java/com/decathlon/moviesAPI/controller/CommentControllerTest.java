package com.decathlon.moviesAPI.controller;

import com.decathlon.moviesAPI.TestTools;
import com.decathlon.moviesAPI.model.Comment;
import com.decathlon.moviesAPI.model.Movie;
import com.decathlon.moviesAPI.payload.MovieRankResponse;
import com.decathlon.moviesAPI.service.CommentService;
import com.decathlon.moviesAPI.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentController.class)
public class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommentService commentService;

    @MockBean
    MovieService movieService;


    @Test
    public  void testPostComment() throws Exception {
        //create a movie
        Movie movie = TestTools.createMovie(1);

        //return the movie created if movieService.findById is invoked
        Mockito.when(movieService.findById(1)).thenReturn(java.util.Optional.of(movie));

        //create a comment
        Comment comment = new Comment();
        comment.setId(1);
        comment.setContent("this is a comment");
        comment.setMovie(movie);

        //return the comment if the commentService.save is invoked
        Mockito.when(commentService.save(Mockito.any(Comment.class))).thenReturn(comment);


        mockMvc.perform(MockMvcRequestBuilders.post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"movieId\" : 1 , \"commentContent\" : \"this is a comment\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.movie.id").value(1))
                .andExpect(jsonPath("$.content").value("this is a comment"));
    }

    @Test
    public  void testPostCommentNonExistingMovie() throws Exception {

        Movie movie = new Movie();

        //return null to simulate a non-existing movie if movieService.findById is invoked
        Mockito.when(movieService.findById(1)).thenReturn(java.util.Optional.ofNullable(null));

        //create a comment
        Comment comment = new Comment();
        comment.setId(1);
        comment.setContent("this is a comment");
        comment.setMovie(movie);

        //return the comment if the commentService.save is invoked
        Mockito.when(commentService.save(Mockito.any(Comment.class))).thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders.post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"movieId\": 1 ,\"commentContent\": \"this is a comment\" }"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCommentsWithMovieIdPram() throws Exception {
        Movie movie = TestTools.createMovie(1);

        //create a list of comments
        Comment comment1 = TestTools.createComment(1,1);
        Comment comment2 = TestTools.createComment(2,1);
        Comment comment3 = TestTools.createComment(3, 2);
        List<Comment> comments = new ArrayList<Comment>();
        comments.add(comment1);
        comments.add(comment2);

        //return true if the movieService.existsById(1) is invoked
        Mockito.when(movieService.existsById(1)).thenReturn(true);

        //return only comments associated with the movie(id=1) if  commentService.findAllByMovie_Id(1) is invoked
        Mockito.when(commentService.findAllByMovie_Id(1)).thenReturn(comments.subList(0,2));

        mockMvc.perform(MockMvcRequestBuilders.get("/comments?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].movie.id").value(1))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].movie.id").value(1));

        //check if commentService.findAllByMovie_Id(1) was invoked
        verify(commentService,times(1)).findAllByMovie_Id(1);
    }

    @Test
    public void testGetCommentsWithNoMovieIdPram() throws Exception {
        Movie movie = TestTools.createMovie(1);

        //create a list of comments
        Comment comment1 = TestTools.createComment(1,1);
        Comment comment2 = TestTools.createComment(2,1);
        List<Comment> comments = new ArrayList<Comment>();
        comments.add(comment1);
        comments.add(comment2);

        //return true if the movieService.existsById(1) is invoked
        Mockito.when(movieService.existsById(1)).thenReturn(true);

        //return all comments if commentService.findAll() is invoked
        Mockito.when(commentService.findAll()).thenReturn(comments);

        mockMvc.perform(MockMvcRequestBuilders.get("/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].movie.id").value(1))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].movie.id").value(1));

        //check if commentService.findAll was invoked
        verify(commentService,times(1)).findAll();

    }

    @Test
    public void testGetCommentsNonExistingMovie() throws Exception {

        //return false if the movieService.existsById(1) is invoked
        Mockito.when(movieService.existsById(1)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get("/comments?id=1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetMovieRanking() throws Exception {
        //create a list of movieRank
        List<MovieRankResponse> movieRankResponses = new ArrayList<MovieRankResponse>();
        movieRankResponses.add(new MovieRankResponse(1,2,1));
        movieRankResponses.add(new MovieRankResponse(2,1,2));

        //return the movie rank list if commentService.getMovieRanking is invoked
        Mockito.when(commentService.getMovieRanking(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class)))
                .thenReturn(movieRankResponses);

        mockMvc.perform(MockMvcRequestBuilders.get("/top")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dateFrom\":\"02-10-1980\",\"dateTo\":\"04-10-2020\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].movieId").value(1))
                .andExpect(jsonPath("$.[0].totalComments").value(2))
                .andExpect(jsonPath("$.[0].rank").value(1))
                .andExpect(jsonPath("$.[1].movieId").value(2))
                .andExpect(jsonPath("$.[1].totalComments").value(1))
                .andExpect(jsonPath("$.[1].rank").value(2));

        //check if commentService.getMovieRanking was invoked
        verify(commentService,times(1)).getMovieRanking(Mockito.any(LocalDate.class),Mockito.any(LocalDate.class));

    }

    @Test
    public void testGetMovieRankingNoRequestBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/top")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
