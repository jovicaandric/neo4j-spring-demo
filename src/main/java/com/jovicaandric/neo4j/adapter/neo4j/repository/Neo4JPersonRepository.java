package com.jovicaandric.neo4j.adapter.neo4j.repository;

import java.util.LinkedList;
import java.util.List;
import com.jovicaandric.neo4j.domain.model.Person;
import com.jovicaandric.neo4j.domain.repository.PersonRepository;
import org.neo4j.driver.Query;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.neo4j.driver.Values.parameters;

@Repository
public class Neo4JPersonRepository implements PersonRepository {

    private final Session session;
    private Query findActorsByMovie = new Query(
        "MATCH (actor:Person)-[:ACTED_IN]->(movie:Movie {title: $title}) RETURN actor.name as name, actor.born as born");
    private Query findAllDirectors = new Query(
        "MATCH (director:Person)-[:DIRECTED]->(movie:Movie) RETURN DISTINCT director.name as name, director.born as born");

    @Autowired
    public Neo4JPersonRepository(final Session session) {
        this.session = session;
    }


    @Override
    public List<Person> findActorsByMovie(final String title) {
        final Result result = session.run(findActorsByMovie.withParameters(parameters("title",title)));
        final List<Person> actors = new LinkedList<>();
        while (result.hasNext()) {
            final Record actorRecord = result.next();
            actors.add(map(actorRecord));
        }
        return actors;
    }

    @Override
    public List<Person> finaAllDirectors() {
        final Result result = session.run(findAllDirectors);
        final List<Person> directors = new LinkedList<>();
        while (result.hasNext()) {
            final Record directorRecord = result.next();
            directors.add(map(directorRecord));
        }
        return directors;
    }

    /**
     * Maps Neo4J Person record to domain Person model
     * @param record neo4j query result
     * @return mapped person
     */
    private Person map(final Record record) {
        return new Person(
            record.get("name").asString(),
            record.get("born").asInt()
        );
    }
}
