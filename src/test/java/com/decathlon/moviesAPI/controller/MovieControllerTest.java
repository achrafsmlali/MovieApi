package com.decathlon.moviesAPI.controller;

import com.decathlon.moviesAPI.TestTools;
import com.decathlon.moviesAPI.model.Movie;
import com.decathlon.moviesAPI.service.MovieService;
import com.decathlon.moviesAPI.service.OmdbService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MovieController.class)
public class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieService movieService;

    @MockBean
    OmdbService omdbService;


    @Test
    public void testPostMovie() throws Exception {
        //creat a movie
        Movie movie = TestTools.createMovie(1);

        //return the movie if omdbService.getMovies is invoked
        Mockito.when(omdbService.getMovies("Joker")).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\" : \"Joker\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Title").value("Joker"));
    }

    @Test
    public void testPostNonExistingMovie() throws Exception {

        //creat a movie with all attributes equal to null
        Movie movie = new Movie();

        //return the movie if omdbService.getMovies is invoked
        Mockito.when(omdbService.getMovies("x0fm")).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\" : \"x0fm\" }"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testGetMovies() throws Exception {
        //create a list of movies
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(TestTools.createMovie(1));
        movies.add(TestTools.createMovie(2));

        //return the movie list if omdbService.findAll is invoked
        Mockito.when(movieService.findAll()).thenReturn(movies);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[1].id").value(2));

    }

    @Test
    public void testDeleteMovie() throws Exception {

        //return true list if movieService.existsById(1) is invoked
        Mockito.when(movieService.existsById(1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/movies/1/"))
                .andExpect(status().isOk());

        //check id movieService.deleteById was invoked
        verify(movieService,times(1)).deleteById(1);

    }

    @Test
    public void testDeleteNonExistingMovie() throws Exception {
        //return false list if movieService.existsById(1) is invoked
        Mockito.when(movieService.existsById(1)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/movies/1/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateMovie() throws Exception {
        //create a movie
        Movie movie = TestTools.createMovie(1);

        //mapper is used to convert object to json
        ObjectMapper mapper = new ObjectMapper();

        //return false list if movieService.findById(1) is invoked
        Mockito.when(movieService.findById(1)).thenReturn(java.util.Optional.of(movie));

        mockMvc.perform(MockMvcRequestBuilders.put("/movies/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(movie)))
                .andExpect(status().isOk());

        //check id movieService.findById(1) was invoked
        verify(movieService,times(1)).findById(1);


    }

    @Test
    public void testUpdateNonExistingMovie() throws Exception {
        //creat a movie
        Movie movie = TestTools.createMovie(1);

        //mapper is used to convert object to json
        ObjectMapper mapper = new ObjectMapper();

        //return empty Optional to simulate non-existing movie if movieService.findById(1) is invoked
        Mockito.when(movieService.findById(1)).thenReturn(java.util.Optional.empty());


        mockMvc.perform(MockMvcRequestBuilders.put("/movies/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(movie)))
                .andExpect(status().isNotFound());

    }
}
