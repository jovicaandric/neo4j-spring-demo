package com.jovicaandric.neo4j.domain.repository;

import java.util.List;
import com.jovicaandric.neo4j.domain.model.Person;

public interface PersonRepository {

    List<Person> findActorsByMovie(final String title);
    List<Person> finaAllDirectors();
}
