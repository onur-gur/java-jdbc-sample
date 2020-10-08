package service;

import model.Model;

import java.sql.*;

public abstract class DatabaseService<M extends Model> implements AutoCloseable {

    private static final String DBMS = "mysql";
    private static final String DB = "movies";
    private static final String DB_USER = "root";
    private static final String DB_USER_PASSWORD = "12345";

    private final String connectionString;
    private final Connection connection;

    protected DatabaseService() throws SQLException{
        connectionString = String.format("jdbc:%s://localhost:3306/%s",DBMS,DB);
        connection = establishConnection();
    }

    private Connection establishConnection() throws SQLException{
        Connection connection;
        try {
            connection = DriverManager.getConnection(connectionString,DB_USER,DB_USER_PASSWORD);
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return connection;
    }

    protected final PreparedStatement preparedStatement(String query) throws SQLException{
        return  connection.prepareStatement(query);
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    protected abstract M convertToModel(ResultSet resultSet) throws SQLException;
}
