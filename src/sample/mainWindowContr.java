package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class mainWindowContr {

    @FXML
    private Button btnAddDTP;

    @FXML
    private Button btnAddDrivers;

    @FXML
    private Button btnAddTransport;

    @FXML
    private Button btnAddUser;

    @FXML
    private Label labelNoPerms;

    @FXML
    private Label mainLabel;

    @FXML
    void initialize(){
        btnAddDTP.setOnAction(Event -> {
            btnAddDTP.getScene().getWindow().hide();
            setScene("/sample/addDTP.fxml");
        });
        btnAddUser.setOnAction(Event -> {
            btnAddUser.getScene().getWindow().hide();
            setScene("/sample/addUser.fxml");
        });
        btnAddDrivers.setOnAction(Event -> {
            btnAddUser.getScene().getWindow().hide();
            setScene("/sample/addDrivers.fxml");
        });
        btnAddTransport.setOnAction(Event -> {
            btnAddUser.getScene().getWindow().hide();
            setScene("/sample/addTransport.fxml");
        });
    }
    public void setScene(String window){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException exception) {
            System.out.println("Ошибка loader");
        }

        Parent Root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(Root));
        stage.show();
    }
}

