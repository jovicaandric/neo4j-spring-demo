package com.jovicaandric.neo4j.domain.repository;

import java.util.List;
import java.util.Optional;
import com.jovicaandric.neo4j.domain.model.Movie;
import com.jovicaandric.neo4j.domain.model.Review;

public interface MovieRepository {

    List<Movie> findAll();

    List<Movie> findAllByActor(final String actor);

    Optional<Movie> findByTitle(final String title);

    List<Review> findReviewsByTitle(String title);
}
