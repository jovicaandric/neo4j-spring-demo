package com.jovicaandric.neo4j.adapter.http;

import java.util.List;
import com.jovicaandric.neo4j.domain.model.Movie;
import com.jovicaandric.neo4j.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private final MovieService service;

    @Autowired
    public MovieController(final MovieService service) {
        this.service = service;
    }

    @GetMapping("/movies/actor/{actor}")
    public List<Movie> listAllMoviesByActor(@PathVariable String actor) {
        return service.getMoviesByActor(actor);
    }

    @GetMapping("/movies/")
    public List<Movie> listAllMovies() {
        return service.getAllMovies();
    }

    @GetMapping("/movies/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable final String title) {
        return service.getMovieByTitle(title)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
