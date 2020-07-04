package com.decathlon.moviesAPI.repository;

import com.decathlon.moviesAPI.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
