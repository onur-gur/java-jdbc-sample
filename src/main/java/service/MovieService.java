package service;

import model.Model;
import model.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MovieService  extends DatabaseService<Movie>{

    private final ActorService actorService;
    private final CastService castService;
    private final GenreService genreService;
    private final MovieGenreService movieGenreService;

    public MovieService() throws SQLException {
        super();
        actorService = new ActorService();
        castService = new CastService();
        genreService = new GenreService();
        movieGenreService = new MovieGenreService();
    }

    @Override
    public void close() throws Exception {
        actorService.close();
        castService.close();
        genreService.close();
        movieGenreService.close();
        super.close();
    }

    @Override
    protected Movie convertToModel(ResultSet resultSet) throws SQLException {
        Movie movie = new Movie();

        movie.setId(resultSet.getInt("id"));
        movie.setDirector(resultSet.getString("director"));
        movie.setDuration(resultSet.getInt("duration"));
        movie.setImdbScore(resultSet.getFloat("imdb_score"));
        movie.setName(resultSet.getString("name"));
        movie.setYear(resultSet.getInt("year"));

        return movie;
    }

    public Movie setActors(Movie movie) throws SQLException{
        Movie _movie;
        try(PreparedStatement statement = preparedStatement("select * from casts c join actors a on c.actor_id = a.actor_id where c.movie_id = ?")){
            statement.setInt(1,movie.getId());
            try (ResultSet resultSet = statement.executeQuery()){
                if(!resultSet.next()){
                    return null;
                }
                _movie = convertToModel(resultSet);
            }
        }
        return  _movie;
    }

    public Movie setGenres(Movie movie) throws SQLException{
        Movie _movie;
        try(PreparedStatement statement = preparedStatement("select * from movie_genres mg join genres g on mg.genre_id = g.id where mg.movie_id = ?")){
            statement.setInt(1,movie.getId());
            try(ResultSet resultSet = statement.executeQuery()){
                if(!resultSet.next()){
                    return null;
                }
                _movie = convertToModel(resultSet);
            }
        }
        return _movie;
    }

    public Movie findByName(String name) throws SQLException {
        Movie movie;
        try(PreparedStatement statement = preparedStatement("select * from movies m where m.name = ?")){
            statement.setString(1,name);
            try(ResultSet resultSet = statement.executeQuery()){
                if(!resultSet.next()){
                    return null;
                }
                movie = convertToModel(resultSet);
            }
        }
        return movie;
    }

    public List<Movie> findMoviesLongerThan(int hours) throws SQLException{
        List<Movie> movieList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from movies m where m.duration > ?")){
            statement.setInt(1,hours);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    movieList.add(convertToModel(resultSet));
                }
            }
        }
        return movieList;
    }

    public List<Movie> findMoviesLongerThan(int hours,int minutes) throws SQLException{
        List<Movie> movieList = new LinkedList<>();
        int totalMinutes = hours*0+minutes;
        try(PreparedStatement statement = preparedStatement("select * from movies m where m.duration > ?")){
            statement.setInt(1,totalMinutes);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    movieList.add(convertToModel(resultSet));
                }
            }
        }
        return movieList;
    }

    public List<Movie> findByDirector(String director) throws SQLException {
        List<Movie> movieList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from movies m where m.director = ?")){
            statement.setString(1,director);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    movieList.add(convertToModel(resultSet));
                }
            }
        }
        return movieList;
    }

    public List<Movie> findByYear(int year) throws SQLException {
        List<Movie> movieList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from movies m where m.year = ?")){
            statement.setInt(1,year);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    movieList.add(convertToModel(resultSet));
                }
            }
        }
        return movieList;
    }

    public List<Movie> findByImdbScoreGreaterThan(float imdbScore) throws SQLException{
        List<Movie> movieList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from movies m where m.imdb_score > ?")){
            statement.setFloat(1,imdbScore);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    movieList.add(convertToModel(resultSet));
                }
            }
        }
        return movieList;
    }


}
