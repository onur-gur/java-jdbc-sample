package service;

import model.Cast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CastService extends DatabaseService<Cast> {

    public  CastService() throws SQLException {
        super();
    }

    @Override
    protected Cast convertToModel(ResultSet resultSet) throws SQLException {
        Cast cast = new Cast();
        cast.setId(resultSet.getInt("id"));
        cast.setActorId(resultSet.getInt("actor_id"));
        cast.setMovieId(resultSet.getInt("movie_id"));
        return cast;
    }

    public List<Cast> findByMovieId(Integer movideId) throws SQLException {
        List<Cast> castByFilm = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from casts c where c.movie_id = ?")){
            statement.setInt(1,movideId);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    castByFilm.add(convertToModel(resultSet));
                }
            }
        }
        return castByFilm;
    }

    public List<Cast> findByActorId(Integer actorID) throws SQLException {
        List<Cast> castByFilm = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from casts c where c.actor_id = ?")){
            statement.setInt(1,actorID);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    castByFilm.add(convertToModel(resultSet));
                }
            }
        }
        return castByFilm;
    }

    public Cast findByMovieIdAndActorId(Integer movieId,Integer actorId) throws SQLException {
        Cast cast;
        try(PreparedStatement statement = preparedStatement("select * from casts c where c.movie_id = ? and c.actor_id = ?")){
            statement.setInt(1,movieId);
            statement.setInt(2,actorId);
            try(ResultSet resultSet = statement.executeQuery()){
                if (!resultSet.next()){
                    return null;
                }
                cast = convertToModel(resultSet);
            }
        }
        return cast;
    }





}
