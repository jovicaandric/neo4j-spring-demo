package com.jovicaandric.neo4j.adapter.neo4j;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Neo4JSession {

    private Neo4JConfiguration configuration;

    @Autowired
    public Neo4JSession(final Neo4JConfiguration configuration) {
        this.configuration = configuration;
    }

    @Bean
    Session session() {
        return GraphDatabase.driver(configuration.getUri(), AuthTokens.basic(configuration.getUsername(), configuration.getPassword()))
            .session();
    }
}
