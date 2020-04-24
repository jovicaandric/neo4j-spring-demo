package com.jovicaandric.neo4j.domain.service;

import java.util.List;
import com.jovicaandric.neo4j.domain.model.Person;
import com.jovicaandric.neo4j.domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonService(final PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> getActorsByMovie(final String movieTitle) {
        return repository.findActorsByMovie(movieTitle);
    }

    public List<Person> getDirectors() {
        return repository.finaAllDirectors();
    }
}
