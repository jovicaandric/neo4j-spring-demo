package com.jovicaandric.neo4j.domain.repository;

import java.util.List;
import java.util.Optional;
import com.jovicaandric.neo4j.domain.model.Movie;

public interface MovieRepository {

    List<Movie> findAll();
    List<Movie> findAllByActor(final String actor);
    Optional<Movie> findByTitle(final String title);
}
