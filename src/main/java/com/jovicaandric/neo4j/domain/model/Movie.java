package com.jovicaandric.neo4j.domain.model;

public class Movie {

    private String title;
    private String tagline;
    private Integer released;

    public Movie() {
    }

    public Movie(final String title, final String tagline, final Integer released) {
        this.title = title;
        this.tagline = tagline;
        this.released = released;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(final String tagline) {
        this.tagline = tagline;
    }

    public Integer getReleased() {
        return released;
    }

    public void setReleased(final Integer released) {
        this.released = released;
    }
}
