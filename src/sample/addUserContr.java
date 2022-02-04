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
    void initialize(){
        ConnHandler handler = new ConnHandler();

        addUserButton.setOnAction(actionEvent -> {
            String login = loginBox.getText();
            String password = passBox.getText();
            String surname = surnameBox.getText();
            String name = nameBox.getText();
            String dateOfBirth = DoB_Box.getText();
            String userType;
            String workplace = workplaceBox.getText();

            if (giveAdmPems.isSelected()) {
                userType = "Admin";
            } else userType = "Inspector";

            User newUser = new User(login, password, surname, name, dateOfBirth, userType, workplace);

            try {
                handler.signUpUser(newUser);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
