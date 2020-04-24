package com.jovicaandric.neo4j.adapter.neo4j.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import com.jovicaandric.neo4j.domain.model.Movie;
import com.jovicaandric.neo4j.domain.model.Review;
import com.jovicaandric.neo4j.domain.repository.MovieRepository;
import org.neo4j.driver.Query;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.neo4j.driver.Values.parameters;

@Repository
public class Neo4JMovieRepository implements MovieRepository {

    private final Session session;
    private Query findAllByActorQuery = new Query(
        "MATCH (actor:Person {name: $actor})-[:ACTED_IN]->(movie) RETURN movie.title as title, movie.tagline as tagline, movie.released as released");
    private Query findAllQuery = new Query(
        "MATCH (movie:Movie) RETURN movie.title as title, movie.tagline as tagline, movie.released as released");
    private Query findByTitleQuery = new Query(
        "MATCH (movie:Movie{title: $title}) RETURN movie.title as title, movie.tagline as tagline, movie.released as released");
    private Query findReviewsByTitleQuery = new Query(
        "MATCH (:Person)-[review:REVIEWED]->(movie:Movie{title: $title}) RETURN review.summary as summary, review.rating as rating");

    @Autowired
    public Neo4JMovieRepository(final Session session) {
        this.session = session;
    }

    @Override
    public List<Movie> findAll() {
        final Result result = session.run(findAllQuery);
        final List<Movie> movies = new LinkedList<>();
        while (result.hasNext()) {
            final Record movieRecord = result.next();
            movies.add(mapMovie(movieRecord));
        }
        return movies;
    }

    @Override
    public List<Movie> findAllByActor(final String actor) {
        final Result result = session.run(findAllByActorQuery.withParameters(parameters("actor", actor)));
        final List<Movie> movies = new LinkedList<>();
        while (result.hasNext()) {
            final Record movieRecord = result.next();
            movies.add(mapMovie(movieRecord));
        }
        return movies;
    }

    @Override
    public Optional<Movie> findByTitle(final String title) {
        final Result result = session.run(findByTitleQuery.withParameters(parameters("title", title)));
        if (result.hasNext()) {
            final Record record = result.next();
            return Optional.of(mapMovie(record));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Review> findReviewsByTitle(final String title) {
        final Result result = session.run(findReviewsByTitleQuery.withParameters(parameters("title", title)));
        final List<Review> reviews = new LinkedList<>();
        while (result.hasNext()) {
            final Record reviewRecord = result.next();
            reviews.add(mapReview(reviewRecord));
        }
        return reviews;
    }

    /**
     * Maps Neo4J Movie record to domain Movie model
     * @param movieRecord neo4j query result
     * @return mapped movie
     */
    private Movie mapMovie(final Record movieRecord) {
        return new Movie(
            movieRecord.get("title").asString(),
            movieRecord.get("tagline").asString(),
            movieRecord.get("released").asInt()
        );
    }

    /**
     * Maps Neo4J Review record to domain Review model
     * @param reviewRecord neo4j query result
     * @return mapped review
     */
    private Review mapReview(final Record reviewRecord) {
        return new Review(
            reviewRecord.get("summary").asString(),
            reviewRecord.get("rating").asDouble()
        );
    }
}
