package com.decathlon.moviesAPI.service;

import com.decathlon.moviesAPI.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmdbServiceImpl implements OmdbService {

    @Value("${omdb.apikey}")
    public String apiKey;

    //movie api url
    public final String omdbUrl = "http://www.omdbapi.com/";

    @Autowired
    RestTemplate restTemplate;


    public Movie getMovies(String title) {
        //creating thr URL
        String url = omdbUrl+ "?apikey=" + apiKey +"&t=" + title;

        //fetch the movie
        Movie movie = restTemplate.getForObject(url, Movie.class);

        return movie;
    }
}
