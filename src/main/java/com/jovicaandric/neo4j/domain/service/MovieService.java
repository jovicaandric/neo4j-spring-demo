package com.jovicaandric.neo4j.domain.service;

import java.util.List;
import java.util.Optional;
import com.jovicaandric.neo4j.domain.model.Movie;
import com.jovicaandric.neo4j.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository repository;

    @Autowired
    public MovieService(final MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getMoviesByActor(final String actor) {
        return repository.findAllByActor(actor);
    }

    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    public Optional<Movie> getMovieByTitle(final String title) {
        return repository.findByTitle(title);
    }
}
