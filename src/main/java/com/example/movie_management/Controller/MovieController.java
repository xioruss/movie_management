package com.example.movie_management.Controller;

import com.example.movie_management.HelloApplication;
import com.example.movie_management.Model.Movie;
import com.example.movie_management.Repository.MovieRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieController {

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
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private TextField t3;

    @FXML
    private TextField t4;

    @FXML
    private Label l1;

    @FXML
    private Button b7;

    @FXML
    void Clean(ActionEvent event) {

        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        l1.setText("");

    }

    @FXML
    void close(ActionEvent event) {

        Stage stage = (Stage) b3.getScene().getWindow();
        stage.close();

    }

    @FXML
    void fetch(ActionEvent event) throws Exception {
        MovieRepository movieRepository = new MovieRepository();
        Movie c = movieRepository.getMovieById(Integer.parseInt(t1.getText()));
        // System.out.println(c.toString());
        if(c.getId() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No such record!", ButtonType.CLOSE);
            alert.showAndWait();
            t1.setText("");
            t1.requestFocus();
        }
        t2.setText(c.getTitle());
        t3.setText(c.getDirector());
        t4.setText(Integer.toString(c.getYear()));
    }


    @FXML
    void save(ActionEvent event) throws Exception {
        MovieRepository movieRepository = new MovieRepository();
            Movie c = new Movie();
        c.setId(Integer.parseInt(t1.getText()));
        c.setTitle(t2.getText());
        c.setDirector(t3.getText());
        c.setYear(Integer.parseInt(t4.getText()));
        movieRepository.saveMovie(c);
        l1.setText("Recorded successfully!");
    }


    @FXML
    void deleteMovies(ActionEvent event) throws Exception {
        try{
        MovieRepository movieRepository = new MovieRepository();
        movieRepository.deleteMovie(Integer.parseInt(t1.getText()));
        l1.setText("Deleted successfully!");
        }
        catch (Exception exception){

            exception.printStackTrace();
            System.err.println("Error: " + exception.getMessage());
            l1.setText("An error occurred");
        }
    }
    @FXML
    void getCategory(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categories.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setTitle("Category");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void updateMovies(ActionEvent event) throws Exception {

        MovieRepository movieRepos = new MovieRepository();
        Movie c = movieRepos.getMovieById(Integer.parseInt(t1.getText()));

        if(c.getId() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No such record!", ButtonType.CLOSE);
            alert.showAndWait();
            t1.setText("");
            t1.requestFocus();
        }
        if (c.getId()==Integer.parseInt(t1.getText())){

            c.setTitle(t2.getText());
            c.setDirector(t3.getText());
            c.setYear(Integer.parseInt(t4.getText()));
        }
        else{
            c.setId(Integer.parseInt(t1.getText()));
            c.setTitle(t2.getText());
            c.setDirector(t3.getText());
            c.setYear(Integer.parseInt(t4.getText()));
        }

        movieRepos.updateMovie(c);
        l1.setText("Recorded successfully!");

    }

}
