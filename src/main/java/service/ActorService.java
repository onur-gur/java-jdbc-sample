package service;

import model.Actor;
import model.Gender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ActorService extends DatabaseService<Actor> {
    private final CastService castService;
    private final GenreService genreService;
    private final MovieGenreService movieGenreService;

    public ActorService() throws SQLException {
        super();
        castService = new CastService();
        genreService = new GenreService();
        movieGenreService = new MovieGenreService();
    }

    @Override
    public void close() throws Exception {
        castService.close();
        genreService.close();
        movieGenreService.close();
        super.close();
    }

    @Override
    protected Actor convertToModel(ResultSet resultSet) throws SQLException {
        Actor actor = new Actor();
        actor.setId(resultSet.getInt("id"));
        actor.setName(resultSet.getString("name"));

        String gender = resultSet.getString("gender");
        actor.setGender(gender.equals("F") ? Gender.FEMALE : Gender.MALE);
        actor.setBirthYear(resultSet.getInt("birth_year"));

        int deathYear = resultSet.getInt("death_year");
        actor.setDeathYear(deathYear == 0 ? null : deathYear);
        actor.setNominations(resultSet.getInt("nominations"));
        actor.setOscarNominations(resultSet.getInt("oscar_nominations"));
        actor.setOscars(resultSet.getInt("oscars"));
        actor.setWins(resultSet.getInt("wins"));

        return actor;
    }

    public Actor findByName(String name) throws SQLException{
        Actor actor;
        try(PreparedStatement statement = preparedStatement("select * from actors a where a.name = ?")){
            statement.setString(1,name);
            try(ResultSet resultSet = statement.executeQuery()){
                if(!resultSet.next()){
                    return null;
                }
                actor = convertToModel(resultSet);
            }
        }
        return actor;
    }

    public List<Actor> findByGender(Gender gender) throws SQLException {
        List<Actor> actorList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from actors a where a.gender = ?")){
            statement.setString(1,gender.toString());
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    actorList.add(convertToModel(resultSet));
                }
            }
        }
        return actorList;
    }

    public List<Actor> findOscarAwardedActors() throws SQLException {
        List<Actor> actorList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from actors a where a.oscars>0")){
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    actorList.add(convertToModel(resultSet));
                }
            }
        }
        return actorList;
    }

    public List<Actor> findActorsWithMoreThanNominations(int n) throws SQLException {
        List<Actor> actorList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from actors a where a.oscars > ?")){
            statement.setInt(1,n);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    actorList.add(convertToModel(resultSet));
                }
            }
        }
        return actorList;
    }

    public List<Actor> findActorsYoungerThanAge(int age) throws SQLException {
        List<Actor> actorList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from actors a where YEAR(CURDATE())-a.birth_year < ?")){
            statement.setInt(1,age);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    actorList.add(convertToModel(resultSet));
                }
            }
        }
        return actorList;
    }

    public List<Actor> findDeadActors() throws SQLException {
        List<Actor> actorList = new LinkedList<>();
        try(PreparedStatement statement = preparedStatement("select * from actors a where a.death_year is not null")){
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    actorList.add(convertToModel(resultSet));
                }
            }
        }
        return actorList;
    }







}
