package com.jovicaandric.neo4j.adapter.http;

import java.util.List;
import com.jovicaandric.neo4j.domain.model.Person;
import com.jovicaandric.neo4j.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(final PersonService service) {
        this.service = service;
    }

    @GetMapping("/actors/movie/{movie}")
    public List<Person> listAllActorsByMovie(@PathVariable String movie) {
        return service.getActorsByMovie(movie);
    }

    @GetMapping("/directors")
    public List<Person> listAllDirectors(){
        return service.getDirectors();
    }
}
