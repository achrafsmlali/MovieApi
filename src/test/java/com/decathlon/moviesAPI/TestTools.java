package com.decathlon.moviesAPI;

import com.decathlon.moviesAPI.model.Comment;
import com.decathlon.moviesAPI.model.Movie;

import java.time.LocalDate;

public class TestTools {

    public static Comment createComment(int commentId, int movieId){
        Movie movie = createMovie(movieId);

        Comment comment = new Comment();
        comment.setMovie(movie);
        comment.setContent("this is comment content");
        comment.setId(commentId);

        return comment;
    }

    public static Movie createMovie(Integer movieID){
        Movie movie = new Movie();
        movie.setActors("Joaquin Phoenix, Robert De Niro, Zazie Beetz, Frances Conroy");
        movie.setDirector("Todd Phillips");
        movie.setId(movieID);
        movie.setLanguage("English");
        movie.setPlot("In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society");
        movie.setGenre("Dram");
        LocalDate date =  LocalDate.of(2019, 10, 03);
        movie.setReleaseDate(date);

        movie.setTitle("Joker");
        movie.setYear(date.getYear());
        return movie;
    }
}
