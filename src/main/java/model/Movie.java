package model;

import java.util.LinkedList;
import java.util.List;

public class Movie extends Model{

    private String name;
    private Integer duration;
    private Integer year;
    private String director;
    private Float imdbScore;

    private List<Genre> genres = new LinkedList<>();
    private List<Actor> actors = new LinkedList<>();


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Integer getDuration(){
        return this.duration;
    }
    public void setDuration(Integer duration){
        this.duration = duration;
    }
    public Integer getYear(){
        return this.year;
    }
    public void setYear(Integer year){
        this.year = year;
    }
    public String getDirector(){
        return this.director;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public Float getImdbScore(){
        return this.imdbScore;
    }
    public void setImdbScore(Float imdbScore){
        this.imdbScore = imdbScore;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public boolean containsGenre(String genre){
        return genres.stream().map(Genre::getName).allMatch(g->g.equals(genre));
    }

    public boolean containsActor(String actor){
        return  actors.stream().map(Actor::getName).allMatch(a->a.equals(actor));
    }
}
