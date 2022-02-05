package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class addDTPContr {

    @FXML
    private Button addDTPButton;

    @FXML
    private TextField approxTimeTB;

    @FXML
    private TextField carNumberTB;

    @FXML
    private TextField typesofcarsTB;

    @FXML
    private TextField casualtiesTB;

    @FXML
    private TextField driversInvTB;

    @FXML
    private Button goBackBtn;

    @FXML
    private TextField inspectorTB;

    @FXML
    private Label labelCore;

    @FXML
    private TextField reasonTB;

    @FXML
    private TextField whereTB;

    @FXML
    void initialize(){
        goBackBtn.setOnAction(Event ->{
            goBackBtn.getScene().getWindow().hide();
            setScene("/sample/mainWindow.fxml");
        });

        addDTPButton.setOnAction(Event ->{
            if (!approxTimeTB.getText().trim().equals("") && !carNumberTB.getText().trim().equals("") && typesofcarsTB.getText().trim().equals("") &&
            casualtiesTB.getText().trim().equals("") && driversInvTB.getText().trim().equals("") && inspectorTB.getText().trim().equals("") &&
            reasonTB.getText().trim().equals("") && whereTB.getText().trim().equals("")){
                ConnHandler handler = new ConnHandler();
                System.out.println("bbob");
                DTP newDTP = new DTP(whereTB.getText(), approxTimeTB.getText(), casualtiesTB.getText(), driversInvTB.getText(),
                        typesofcarsTB.getText(), carNumberTB.getText(), inspectorTB.getText(), reasonTB.getText());
                try {
                    handler.addDTP(newDTP);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println("bibib");
                setScene("/sample/mainWindow.fxml");
            }
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
