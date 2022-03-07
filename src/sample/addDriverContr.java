package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
            mainWindowContr.setScene("/sample/mainWindow.fxml");
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
}
