package service;

import model.Gender;
import model.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreService extends DatabaseService<Genre> {
    public GenreService() throws SQLException {
        super();
    }

    @Override
    protected Genre convertToModel(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getInt("id"));
        genre.setName(resultSet.getString("name"));
        return genre;
    }

    public Genre findByName(String name) throws SQLException {
        Genre genre;
        try(PreparedStatement statement = preparedStatement("select * from genres g where g.name = ?")){
            statement.setString(1,name);
            try(ResultSet resultSet = statement.executeQuery()){
                if (!resultSet.next()){
                    return null;
                }
                genre = convertToModel(resultSet);
            }
        }
        return genre;
    }
}
