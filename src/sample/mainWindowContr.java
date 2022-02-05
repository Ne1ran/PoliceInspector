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
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private Label d1;

    @FXML
    private Label dtp1;

    @FXML
    private Label t1;

    @FXML
    void initialize(){
        ConnHandler handler = new ConnHandler();

        mainLabel.setText("С возвращением, инспектор " + CurrentlyLoggedUser.getSurname() + " " + CurrentlyLoggedUser.getName());

        try {
            ResultSet driversSet = handler.getDrivers();
            ResultSet DTPSet = handler.getDTPs();
            ResultSet transportSet = handler.getTransport();
            d1.setText(changeLabels(driversSet, "Drivers"));
            dtp1.setText(changeLabels(DTPSet, "DTP"));
            t1.setText(changeLabels(transportSet, "Transport"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Шиза");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        btnAddDTP.setOnAction(Event -> {
            btnAddDTP.getScene().getWindow().hide();
            setScene("/sample/addDTP.fxml");
        });
        btnAddUser.setOnAction(Event -> {
            if (CurrentlyLoggedUser.getPerms().equals("Admin")){
            btnAddUser.getScene().getWindow().hide();
            setScene("/sample/addUser.fxml");
            } else labelNoPerms.setText("У вас недостаточно прав");
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

    public String changeLabels(ResultSet resultSet, String labelType) throws SQLException {
        String textForLabel = "";
        int count = 0;
        while (resultSet.next()){
            count++;
            if (count == 10){
                break;
            }
            if (labelType.equals("Drivers")) { // можно чутка оптимизировать
                textForLabel += count + ". " + resultSet.getNString(AllConstants.DriversConst.SURNAME) + " " +
                        resultSet.getNString(AllConstants.DriversConst.NAME) + "; " + resultSet.getNString(AllConstants.DriversConst.CARNUMBER) + "\n";
            } else if (labelType.equals("Transport")){
                textForLabel += count + ". " + resultSet.getNString(AllConstants.TransportConst.NUMBER) + "; " +
                        resultSet.getNString(AllConstants.TransportConst.BRAND) + "; " + resultSet.getNString(AllConstants.TransportConst.DRIVERSURNAME) + "\n";
            } else if (labelType.equals("DTP")){
                textForLabel += count + ". " + resultSet.getNString(AllConstants.DTPConst.WHERE) + "; " +
                        resultSet.getNString(AllConstants.DTPConst.TIME) + "; " + resultSet.getNString(AllConstants.DTPConst.INSPECTOR) + "\n";
            }
    }
        return textForLabel;
    }
}


