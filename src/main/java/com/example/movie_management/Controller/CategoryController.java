package com.example.movie_management.Controller;

import com.example.movie_management.Model.Category;
import com.example.movie_management.Model.Movie;
import com.example.movie_management.Repository.CategoryRepository;
import com.example.movie_management.Repository.MovieRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CategoryController {

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button b4;

    @FXML
    private Button b5;

    @FXML
    private Button b6;

    @FXML
    private Label l1;

    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    void Clean(ActionEvent event) {

        t1.setText("");
        t2.setText("");
        l1.setText("");

    }

    @FXML
    void close(ActionEvent event) {

        Stage stage = (Stage) b3.getScene().getWindow();
        stage.close();

    }

    @FXML
    void deleteCategory(ActionEvent event) throws Exception {
        try{
            CategoryRepository categoryRepository = new CategoryRepository();
            categoryRepository.deleteCategory(Integer.parseInt(t1.getText()));
            l1.setText("Deleted successfully!");
        }
        catch (Exception exception){

            exception.printStackTrace();
            System.err.println("Error: " + exception.getMessage());
            l1.setText("An error occurred");
        }
    }

    @FXML
    void fetch(ActionEvent event) throws Exception {
        CategoryRepository categoryRepository = new CategoryRepository();
        Category c = categoryRepository.getCategoryById(Integer.parseInt(t1.getText()));
        // System.out.println(c.toString());
        if(c.getId() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No such record!", ButtonType.CLOSE);
            alert.showAndWait();
            t1.setText("");
            t1.requestFocus();
        }
        t2.setText(c.getName());
    }

    @FXML
    void save(ActionEvent event) throws Exception {
        CategoryRepository categoryRepository = new CategoryRepository();
        Category c = new Category();
        c.setId(Integer.parseInt(t1.getText()));
        c.setName(t2.getText());
        categoryRepository.saveCategory(c);
        l1.setText("Recorded successfully!");
    }

    @FXML
    void updateCategory(ActionEvent event) throws Exception {

        CategoryRepository categoryRepos = new CategoryRepository();
        Category c = categoryRepos.getCategoryById(Integer.parseInt(t1.getText()));

        if(c.getId() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No such record!", ButtonType.CLOSE);
            alert.showAndWait();
            t1.setText("");
            t1.requestFocus();
        }
        if (c.getId()==Integer.parseInt(t1.getText())){

            c.setName(t2.getText());
        }
        else{
            c.setId(Integer.parseInt(t1.getText()));
            c.setName(t2.getText());
        }

        categoryRepos.updateCategory(c);
        l1.setText("Recorded successfully!");

    }

}
