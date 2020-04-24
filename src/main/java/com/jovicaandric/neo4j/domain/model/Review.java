package com.jovicaandric.neo4j.domain.model;

public class Review {

    private String summary;
    private Double rating;

    public Review(final String summary, final Double rating) {
        this.summary = summary;
        this.rating = rating;
    }

    public Review() {
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(final Double rating) {
        this.rating = rating;
    }
}
