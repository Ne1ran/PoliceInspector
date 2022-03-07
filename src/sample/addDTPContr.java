package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
            mainWindowContr.setScene("/sample/mainWindow.fxml");
        });

        addDTPButton.setOnAction(Event ->{
            if (!(approxTimeTB.getText().trim().equals("") || carNumberTB.getText().trim().equals("") || typesofcarsTB.getText().trim().equals("") ||
            casualtiesTB.getText().trim().equals("") || driversInvTB.getText().trim().equals("") || inspectorTB.getText().trim().equals("") ||
            reasonTB.getText().trim().equals("") || whereTB.getText().trim().equals(""))){
                ConnHandler handler = new ConnHandler();
                DTP newDTP = new DTP(whereTB.getText(), approxTimeTB.getText(), casualtiesTB.getText(), driversInvTB.getText(),
                        typesofcarsTB.getText(), carNumberTB.getText(), inspectorTB.getText(), reasonTB.getText());
                try {
                    handler.addDTP(newDTP);
                } catch (SQLException | ClassNotFoundException throwables) {
                    labelCore.setText("Не удалось добавить данные");
                }
                labelCore.setText("Данные успешно добавлены!");
            } else labelCore.setText("Пожалуйста, заполните все поля");
        });
    }
}
