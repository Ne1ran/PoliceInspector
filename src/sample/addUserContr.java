package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class addUserContr {

    @FXML
    private TextField DoB_Box;

    @FXML
    private Button addUserButton;

    @FXML
    private CheckBox giveAdmPems;

    @FXML
    private Label labelCore;

    @FXML
    private TextField loginBox;

    @FXML
    private TextField nameBox;

    @FXML
    private TextField passBox;

    @FXML
    private TextField surnameBox;

    @FXML
    private TextField workplaceBox;

    @FXML
    private Button goBackBtn;

    @FXML
    void initialize(){
        ConnHandler handler = new ConnHandler();

        goBackBtn.setOnAction(actionEvent -> {
            goBackBtn.getScene().getWindow().hide();
            mainWindowContr.setScene("/sample/mainWindow.fxml");
        });

        addUserButton.setOnAction(actionEvent -> {
            String login = loginBox.getText().trim();
            String password = passBox.getText().trim();
            String surname = surnameBox.getText().trim();
            String name = nameBox.getText().trim();
            String dateOfBirth = DoB_Box.getText().trim();
            String userType;
            String workplace = workplaceBox.getText().trim();

            if (giveAdmPems.isSelected()) {
                userType = "Admin";
            } else userType = "Inspector";

            if(!(login.equals("") || password.equals("") || surname.equals("") || name.equals("") || dateOfBirth.equals("") || workplace.equals(""))) {

                User newUser = new User(login, password, surname, name, dateOfBirth, userType, workplace);

                try {
                    handler.signUpUser(newUser);
                } catch (SQLException | ClassNotFoundException throwables) {
                    labelCore.setText("Произошла ошибка добавления данных");
                }
                labelCore.setText("Данные успешно добавлены");
            } else labelCore.setText("Пожалуйста, заполните все поля");
        });
    }
}
