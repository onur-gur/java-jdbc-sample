package service;

import model.MovieGenre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MovieGenreService extends DatabaseService<MovieGenre> {
    public MovieGenreService() throws SQLException {
        super();
    }

    @Override
    protected MovieGenre convertToModel(ResultSet resultSet) throws SQLException {
        MovieGenre movieGenre = new MovieGenre();
        movieGenre.setId(resultSet.getInt("id"));
        movieGenre.setMovieId(resultSet.getInt("movie_id"));
        movieGenre.setGenreId(resultSet.getInt("genre_id"));
        return movieGenre;
    }

    public List<MovieGenre> findByMovieId(Integer movieId) throws  SQLException {
        List<MovieGenre> movieGenreList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from movie_genres mg where mg.movie_id = ?")){
            statement.setInt(1,movieId);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    movieGenreList.add(convertToModel(resultSet));
                }
            }
        }
        return movieGenreList;
    }

    public List<MovieGenre> findByMGenreId(Integer genreId) throws  SQLException {
        List<MovieGenre> movieGenreList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from movie_genres mg where mg.genre_id = ?")){
            statement.setInt(1,genreId);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    movieGenreList.add(convertToModel(resultSet));
                }
            }
        }
        return movieGenreList;
    }

    public List<MovieGenre> findByMGenreIdAndMovieId(Integer movieId,Integer genreId) throws  SQLException {
        List<MovieGenre> movieGenreList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from movie_genres mg where mg.genre_id = ? and mg.movie_id = ?")){
            statement.setInt(1,genreId);
            statement.setInt(1,movieId);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    movieGenreList.add(convertToModel(resultSet));
                }
            }
        }
        return movieGenreList;
    }
}
