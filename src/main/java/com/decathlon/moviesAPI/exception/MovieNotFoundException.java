package com.decathlon.moviesAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//this is exception is thrown when a movie is not found in the DB
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The movie was not found")
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Integer id) {
        super("Movie with id " + id + "was not found");
    }
}
