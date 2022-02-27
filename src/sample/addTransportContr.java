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

public class addTransportContr {

    @FXML
    private Button addButton;

    @FXML
    private Button goBackBtn;

    @FXML
    private Label labelCore;

    @FXML
    private TextField ownerName;

    @FXML
    private TextField ownerSurname;

    @FXML
    private TextField transportBrand;

    @FXML
    private TextField transportColor;

    @FXML
    private TextField transportModel;

    @FXML
    private TextField transportNum;

    @FXML
    void initialize(){

        goBackBtn.setOnAction(ActionEvent ->{
            goBackBtn.getScene().getWindow().hide();
            mainWindowContr.setScene("/sample/mainWindow.fxml");
        });

        addButton.setOnAction(ActionEvent ->{

            String ownerName1 = ownerName.getText().trim();
            String ownerSurname1 = ownerSurname.getText().trim();
            String brand = transportBrand.getText().trim();
            String color = transportColor.getText().trim();
            String model = transportModel.getText().trim();
            String number = transportNum.getText().trim();

            if (!(ownerName1.equals("") || ownerSurname1.equals("") || brand.equals("") ||
                    color.equals("") || model.equals("") || number.equals(""))){

                ConnHandler handler = new ConnHandler();

                Transport newtransport = new Transport(number, brand, ownerSurname1, ownerName1, color, model);
                try {
                    handler.addTransport(newtransport);
                } catch (SQLException | ClassNotFoundException throwables) {
                    labelCore.setText("Не удалось добавить данные");
                }
                labelCore.setText("Данные успешно добавлены!");
            } else labelCore.setText("Пожалуйста, заполните все поля");
        });
    }
}
