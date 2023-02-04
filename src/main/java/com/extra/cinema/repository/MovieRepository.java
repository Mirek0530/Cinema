package com.extra.cinema.repository;

import com.extra.cinema.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

    Movie save(Movie movie);

    List<Movie> findAll();
}
