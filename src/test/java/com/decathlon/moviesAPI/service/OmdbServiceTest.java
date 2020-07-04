package com.decathlon.moviesAPI.service;

import com.decathlon.moviesAPI.TestTools;
import com.decathlon.moviesAPI.model.Movie;
import com.decathlon.moviesAPI.service.OmdbServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OmdbServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    OmdbServiceImpl omdbService;

    @Value("${omdb.apikey}")
    public String apiKey;

    @Test
    public void testGetMovies(){
        //creat a movie
        Movie movie = TestTools.createMovie(1);

        //return the movie if the method getForObject of restTemplate was invoked
        Mockito.when(restTemplate.getForObject("http://www.omdbapi.com/?apikey=" + apiKey +"&t=Joker",Movie.class))
                .thenReturn(movie);

        //fetch the movie
        Movie movieResp = omdbService.getMovies("Joker");

        //check if the method getForObject of restTemplate was invoked
        verify(restTemplate, times(1)).getForObject("http://www.omdbapi.com/?apikey=" + apiKey +"&t=Joker",Movie.class);

        //check if the movie response data are the one expected
        assertEquals("Joaquin Phoenix, Robert De Niro, Zazie Beetz, Frances Conroy",movieResp.getActors());
        assertEquals("English",movieResp.getLanguage());
        assertEquals("In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society",movieResp.getPlot());
        assertEquals("Todd Phillips",movieResp.getDirector());
        assertEquals(2019,movieResp.getYear());
        assertEquals(LocalDate.of(2019, 10, 03),movieResp.getReleaseDate());


    }

}
