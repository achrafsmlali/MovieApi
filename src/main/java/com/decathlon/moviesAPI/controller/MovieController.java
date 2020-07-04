package com.decathlon.moviesAPI.controller;

import com.decathlon.moviesAPI.exception.MovieDoesNotExistException;
import com.decathlon.moviesAPI.exception.MovieNotFoundException;
import com.decathlon.moviesAPI.model.Movie;
import com.decathlon.moviesAPI.payload.MovieRequest;
import com.decathlon.moviesAPI.service.MovieService;
import com.decathlon.moviesAPI.service.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    @Autowired
    OmdbService omdbService;

    @Autowired
    MovieService movieService;

    @PostMapping("/movies")
    @ResponseBody
    public Movie postMovie(@RequestBody (required = true) MovieRequest movieRequest) {
        Movie movie = omdbService.getMovies(movieRequest.getTitle());

        //check if the title is not null if it is the movie does not exist
        if (movie.getTitle() == null){
            throw new MovieDoesNotExistException(movieRequest.getTitle());
        }
        //create the movie
        movieService.save(movie);
        return movie;
    }

    @GetMapping("/movies")
    @ResponseBody
    public List<Movie> getMovies(){
        //find all movies
        List<Movie> movies  = movieService.findAll();
        return movies;
    }

    @DeleteMapping("/movies/{id}/")
    @ResponseBody
    public void deleteMovie(@PathVariable Integer id){
        //check if the movie with the id exist
        if (!movieService.existsById(id)){
            throw new MovieNotFoundException(id);
        }
        //delete the movie with
        movieService.deleteById(id);
    }

    @PutMapping("/movies/{id}/")
    @ResponseBody
    public void updateMovie(@RequestBody Movie movie, @PathVariable Integer id){
        //find the movie
        Optional<Movie> movieOptional = movieService.findById(id);

        //check if the movie with the id exist
        if (!movieOptional.isPresent()){
            throw new MovieNotFoundException(id) ;
        }

        movie.setId(id);

        //update the movies
        movieService.save(movie);
    }

}
