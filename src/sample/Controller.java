package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML
    private Button authButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        authButton.setOnAction(Event -> {
            String login = loginField.getText();
            String pass = passwordField.getText();

            if (!login.equals("") && !pass.equals("")){
                try {
                    if (loginUser(login, pass)){
                        authButton.getScene().getWindow().hide();

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/sample/mainWindow.fxml"));
                        try {
                            loader.load();
                        } catch (IOException exception) {
                            System.out.println("Ошибка loader");
                        }

                        Parent mainWindowRoot = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(mainWindowRoot));
                        stage.showAndWait();
                    } else System.out.println("Такого юзера нема");
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }


            } else {
                System.out.println("Логин или пароль пусты");
            }
        });
    }

    private boolean loginUser(String login, String pass) throws SQLException, ClassNotFoundException {
        ConnHandler handler = new ConnHandler();
        User signInUser = new User();
        signInUser.setlogin(login);
        signInUser.setUserpassword(pass);
        ResultSet rset = handler.getUser(signInUser);

        int count = 0;

        while (rset.next()){
            count++;
        }
        return count > 0;
    }
}