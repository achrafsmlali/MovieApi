package com.decathlon.moviesAPI.service;


import com.decathlon.moviesAPI.TestTools;
import com.decathlon.moviesAPI.model.Movie;
import com.decathlon.moviesAPI.repository.MovieRepository;
import com.decathlon.moviesAPI.service.MovieService;
import com.decathlon.moviesAPI.service.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {


    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService ;

    @Test
    public void testFindByID(){
        //return a movie object if tje method findById(1) of movieRepository was invoked
        Mockito.when(movieRepository.findById(1))
                .thenReturn(Optional.of(TestTools.createMovie(1)));
        //find the movie by id
        Optional<Movie> movieOp = movieService.findById(1);

        //check if the movie exist
        assertEquals(true,movieOp.isPresent());
        Movie movie = movieOp.get();

        //check if the movie data is correct
        assertEquals("Joaquin Phoenix, Robert De Niro, Zazie Beetz, Frances Conroy",movie.getActors());
        assertEquals("English",movie.getLanguage());
        assertEquals("In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society",movie.getPlot());
        assertEquals("Todd Phillips",movie.getDirector());
        assertEquals(2019,movie.getYear());
        assertEquals(LocalDate.of(2019, 10, 03),movie.getReleaseDate());

    }

    @Test
    public void  testExistsById(){
        //return true if the method existsById(1) of movieRepository is invoked
        Mockito.when(movieRepository.existsById(1))
                .thenReturn(true);

        assertEquals(true, movieService.existsById(1));
    }

    @Test
    public void testSave(){
        //create a movie
        Movie movie = TestTools.createMovie(1);
        // save the movie
        movieService.save(movie);

        //check if the method save of movieRepository was invoked
        verify(movieRepository,times(1)).save(movie);
    }

    @Test
    public void testFindAll(){
        //create a list of movies
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(TestTools.createMovie(1));
        movies.add(TestTools.createMovie(2));

        //return the list if the method findAll of movieRepository was invoked
        Mockito.when(movieRepository.findAll()).thenReturn(movies);

        //find the movies
        List<Movie> movieList = movieService.findAll();

        //check if the nbr of movies returned is correct
        assertEquals(2, movieList.size());

        //check if rhe method findAll of movieRepository was invoked
        verify(movieRepository, times(1)).findAll();

    }

    @Test
    public void testDeleteById(){
        //delete a movie
        movieService.deleteById(1);

        //check if the method delete of movieRepository was invoked
        verify(movieRepository, times(1)).deleteById(1);
    }
}
