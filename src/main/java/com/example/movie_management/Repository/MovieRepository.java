package com.example.movie_management.Repository;

import com.example.movie_management.Model.Movie;
import java.sql.*;

public class MovieRepository {

    private final String URL = "jdbc:mysql://localhost:3306/movie";
    private final String USER = "root";
    private final String PASSWORD = "193725486";
    private Connection conn = null;
    private Statement stm = null;
    private ResultSet rs = null;

    public Movie getMovieById(int cid) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT * FROM movies WHERE movie_id =" + cid +";";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        Movie c = new Movie();
        while(rs.next()){
            c.setId(rs.getInt(1));
            c.setTitle(rs.getString(2));
            c.setDirector(rs.getString(3));
            c.setYear(rs.getInt(4));
        }
        conn.close();
        return c;
    }


    public void saveMovie(Movie c) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stm = conn.createStatement();
        // String query = "INSERT INTO customer VALUES (3,'Davut','Sivas');";
        String query = "INSERT INTO movies VALUES (" + c.getId() +",'" + c.getTitle() + "','" + c.getDirector() +"','"+ c.getYear()+"');";
        stm.executeUpdate(query);
    }

    public void updateMovie(Movie c) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stm = conn.createStatement();
        // String query = "INSERT INTO customer VALUES (3,'Davut','Sivas');";
        String query = String.format("UPDATE movies SET title='%s', director='%s', year=%d WHERE movie_id=%d",
                c.getTitle(), c.getDirector(), c.getYear(), c.getId());

        stm.executeUpdate(query);
    }

    public void deleteMovie(int movieId)throws Exception{
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stm = conn.createStatement();
            String query = "DELETE FROM movies WHERE movie_id =" + movieId + ";";
            stm.executeUpdate(query);
            stm.close();
            conn.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            System.err.println("Error closing statement: " + exception.getMessage());
        }


    }

}
