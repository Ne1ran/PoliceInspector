package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
            String login = loginField.getText().trim();
            String pass = passwordField.getText().trim();

            if (!login.equals("") && !pass.equals("")){
                try {
                    CurrentlyLoggedUser userNow = new CurrentlyLoggedUser();
                    if (loginUser(login, pass, userNow)){
                        authButton.getScene().getWindow().hide();
                        mainWindowContr.setScene("/sample/mainWindow.fxml");
                    } else System.out.println("Такого пользователя не существует");
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }


            } else {
                System.out.println("Логин или пароль пусты");
            }
        });
    }

    private boolean loginUser(String login, String pass, CurrentlyLoggedUser user) throws SQLException, ClassNotFoundException {
        ConnHandler handler = new ConnHandler();
        User signInUser = new User();
        signInUser.setlogin(login);
        signInUser.setUserpassword(pass);
        ResultSet rset = handler.getUser(signInUser);

        int count = 0;

        while (rset.next()){
            count++;
            CurrentlyLoggedUser.setName(rset.getNString(AllConstants.UsersConst.USER_NAME));
            CurrentlyLoggedUser.setPerms(rset.getNString(AllConstants.UsersConst.USER_TYPE));
            CurrentlyLoggedUser.setSurname(rset.getNString(AllConstants.UsersConst.USER_SURNAME));
        }
        return count == 1;
    }
}