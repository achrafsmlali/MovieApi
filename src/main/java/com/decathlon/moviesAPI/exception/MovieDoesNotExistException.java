package com.decathlon.moviesAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//this is exception is thrown when requesting a non-existing movie
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "the movie does not exist")
public class MovieDoesNotExistException extends RuntimeException {
    public MovieDoesNotExistException(String str) {
        super("the Movie " + str + "does not exist");
    }
}