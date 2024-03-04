package com.example.movie_management.Repository;

import com.example.movie_management.Model.Category;
import java.sql.*;

public class CategoryRepository {

    private final String URL = "jdbc:mysql://localhost:3306/movie";
    private final String USER = "root";
    private final String PASSWORD = "193725486";
    private Connection conn = null;
    private Statement stm = null;
    private ResultSet rs = null;

    public Category getCategoryById(int cid) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT * FROM categories WHERE category_id =" + cid +";";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        Category c = new Category();
        while(rs.next()){
            c.setId(rs.getInt(1));
            c.setName(rs.getString(2));
        }
        conn.close();
        return c;
    }


    public void saveCategory(Category c) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stm = conn.createStatement();
        // String query = "INSERT INTO customer VALUES (3,'Davut','Sivas');";
        String query = "INSERT INTO categories VALUES (" + c.getId() +",'" + c.getName() +"');";
        stm.executeUpdate(query);
    }

    public void updateCategory(Category c) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stm = conn.createStatement();
        // String query = "INSERT INTO customer VALUES (3,'Davut','Sivas');";
        String query = String.format("UPDATE categories SET name='%s' WHERE category_id=%d",
                c.getName(),c.getId());

        stm.executeUpdate(query);
    }

    public void deleteCategory(int categoryId)throws Exception{
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stm = conn.createStatement();
            String query = "DELETE FROM categories WHERE category_id =" + categoryId + ";";
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

