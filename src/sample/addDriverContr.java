package sample;

import javafx.event.ActionEvent;
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

public class addDriverContr {

    @FXML
    private Button addButton;

    @FXML
    private TextField driverCarBrand;

    @FXML
    private TextField driverCarNumber;

    @FXML
    private TextField driverDoB;

    @FXML
    private TextField driverLicenceID;

    @FXML
    private TextField driverName;

    @FXML
    private TextField driverPhoneNum;

    @FXML
    private TextField driverSurname;

    @FXML
    private TextField driversViolations;

    @FXML
    private Button goBackBtn;

    @FXML
    private Label labelCore;

    @FXML
    void initialize(){

        goBackBtn.setOnAction(ActionEvent ->{
            goBackBtn.getScene().getWindow().hide();
            setScene("/sample/mainWindow.fxml");
        });

        addButton.setOnAction(ActionEvent ->{

            String carBrand = driverCarBrand.getText().trim();
            String carNumber = driverCarNumber.getText().trim();
            String doB = driverDoB.getText().trim();
            String licenceID = driverLicenceID.getText().trim();
            String name = driverName.getText().trim();
            String phoneNum = driverPhoneNum.getText().trim();
            String surname = driverSurname.getText().trim();
            String violations = driversViolations.getText().trim();

            if (!(carBrand.equals("") || carNumber.equals("") || doB.equals("") || licenceID.equals("") ||
                    name.equals("") || phoneNum.equals("") || surname.equals("") || violations.equals(""))){

                ConnHandler handler = new ConnHandler();

            Driver newDriver = new Driver(name, surname, doB, licenceID, carBrand, carNumber, phoneNum, violations);
                try {
                    handler.addDriver(newDriver);
                } catch (SQLException | ClassNotFoundException throwables) {
                    labelCore.setText("Не удалось добавить данные");
                }
                labelCore.setText("Данные успешно добавлены!");
            } else labelCore.setText("Пожалуйста, заполните все поля");
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
