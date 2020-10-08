import model.Actor;
import model.Movie;
import service.ActorService;
import service.CastService;
import service.GenreService;
import service.MovieService;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        MovieService movieService = new MovieService();
        Movie movie = new Movie();
        movie.setName("Blade Runner 2049");
        System.out.println(movieService.findByName(movie.getName()).getYear());

        ActorService actorService = new ActorService();
        System.out.println("1'den fazla oscar adaylığı:");
        actorService.findActorsWithMoreThanNominations(1).forEach(actor -> System.out.println(actor.getName()));

        System.out.println("Ölmüş olan aktorler:");
        actorService.findDeadActors().forEach(actor -> System.out.println(actor.getName()));

        CastService castService = new CastService();
        castService.findByMovieId(1).forEach(cast -> System.out.println(cast.getActorId()));

        System.out.println("Film isim türüne göre listeleme");
        GenreService genreService = new GenreService();

        String genre = "Horror";
        System.out.println(genreService.findByName(genre) == null ? "Bu film türünde kayıt bulunmamaktadır" : genreService.findByName(genre).getName());

        String genre2 = "Drama";
        System.out.println(genreService.findByName(genre2) == null ? "Bu film türünde kayıt bulunmamaktadır" : genreService.findByName(genre2).getName());


    }
}
