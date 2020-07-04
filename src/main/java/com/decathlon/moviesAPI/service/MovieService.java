package com.decathlon.moviesAPI.service;

import com.decathlon.moviesAPI.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    boolean existsById(Integer id);

    void deleteById(Integer id);

    Optional<Movie> findById(Integer id);

    List<Movie> findAll();

    void save(Movie movie);
}
