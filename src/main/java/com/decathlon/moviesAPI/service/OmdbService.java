package com.decathlon.moviesAPI.service;

import com.decathlon.moviesAPI.model.Movie;

public interface OmdbService {

    public Movie getMovies(String title);
    }
