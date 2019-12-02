package Interceptor;

import Maze.TrappedPlus;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;

public class LoginScreen extends Stage{
    private Scene scene;
    private final static String sceneID="LoginScreen";
    public LoginScreen(){

        Group root=new Group();
        Label title=new Label("T R A P P E D II");
        title.setStyle("-fx-font-size:60px;-fx-text-fill:	#5a5860");
        title.setAlignment(Pos.CENTER);

        Button loginButton=new Button("Login");
        Button registerButton=new Button("Register");
        Button playButton=new Button("Play as Guest");
        playButton.setOnAction(e->{
            new TrappedPlus().show();
        });
        loginButton.setAlignment(Pos.CENTER);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        registerButton.setOnAction(e-> {
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    boolean allowLogin = false;
                    allowLogin = new usernameFilter().validInput(username, "[A-Za-z0-9]+");
                    allowLogin = new usernameFilter().validInput(password, "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}");
                    if (allowLogin) {
                        FileManagement.saveAToFile(new User(username,password));
                            File f = new File(getClass().getResource("/text/currentuser.txt").getFile());
                            FileWriter fr = null;
                            try {
                                fr = new FileWriter(f);
                                fr.write(username);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }finally{
                                try {
                                    fr.close();
                                } catch (IOException exx) {
                                    exx.printStackTrace();
                                }
                                close();
                                new TrappedPlus().show();
                            }

                            System.out.println("Object has been serialized");

                        }


                    else{
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Cannot Register");
                        errorAlert.setContentText("Password must have at least 1 number, 1 uppercase and 1 lowercase character");
                        errorAlert.showAndWait();

                    }
                });
        loginButton.setOnAction(e->{
            boolean allowLogin = false;
            String username = usernameField.getText();
            String password = passwordField.getText();
            usernameFilter filter = new usernameFilter();
            try {
                allowLogin = filter.checkInput(username, password);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
                if (allowLogin) {
                    File file = new File(getClass().getResource("/text/currentuser.txt").getFile());
                    FileWriter fr = null;
                    try {
                        fr = new FileWriter(file);
                        fr.write(username);
                    } catch (IOException f) {
                        f.printStackTrace();
                    }finally{
                        try {
                            fr.close();
                        } catch (IOException f) {
                            f.printStackTrace();
                        }
                        close();
                        new TrappedPlus().show();
                    }
                }else{
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Cannot Log In");
                    errorAlert.setContentText("Username or Password is Incorrect");
                    errorAlert.showAndWait();
               // }
            }
        });
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.add(title,0,0);
        grid.add(usernameField, 0, 1);
        grid.add(passwordField, 0, 2);
        grid.add(loginButton, 0, 3);
        grid.add(registerButton, 0, 4);
        grid.add(playButton, 0, 5);
        Scene scene = new Scene(grid, 300, 275);
        setScene(scene);

    }
}

