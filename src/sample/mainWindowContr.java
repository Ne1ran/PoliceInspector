package sample;

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
    private Button btnSearchDTPs;

    @FXML
    private Button btnSearchDrivers;

    @FXML
    private Button btnSearchTransport;

    @FXML
    private Button btnWatchDTPs;

    @FXML
    private Button btnWatchDrivers;

    @FXML
    private Button btnWatchTransports;

    @FXML
    private Label labelNoPerms;

    @FXML
    private Label mainLabel;

    @FXML
    void initialize(){
        ConnHandler handler = new ConnHandler();

        if (CurrentlyLoggedUser.getPerms().equals("Admin")){
            mainLabel.setText("С возвращением, администратор " + CurrentlyLoggedUser.getSurname() + " " + CurrentlyLoggedUser.getName());
        } else {
            mainLabel.setText("С возвращением, инспектор " + CurrentlyLoggedUser.getSurname() + " " + CurrentlyLoggedUser.getName());
        }
        /*try {
            ResultSet driversSet = handler.getDrivers();
            ResultSet DTPSet = handler.getDTPs();
            ResultSet transportSet = handler.getTransport();
            d1.setText(changeLabels(driversSet, "Drivers"));
            dtp1.setText(changeLabels(DTPSet, "DTP"));
            t1.setText(changeLabels(transportSet, "Transport"));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } */
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
            btnAddDrivers.getScene().getWindow().hide();
            setScene("/sample/addDriver.fxml");
        });
        btnAddTransport.setOnAction(Event -> {
            btnAddTransport.getScene().getWindow().hide();
            setScene("/sample/addTransport.fxml");
        });
        btnWatchDrivers.setOnAction(Event -> {
            btnWatchDrivers.getScene().getWindow().hide();
            setScene("/sample/watchDrivers.fxml");
        });
        btnWatchTransports.setOnAction(Event -> {
            btnWatchTransports.getScene().getWindow().hide();
            setScene("/sample/watchTransports.fxml");
        });
        btnWatchDTPs.setOnAction(Event -> {
            btnWatchDTPs.getScene().getWindow().hide();
            setScene("/sample/watchDTPs.fxml");
        });
        btnSearchDrivers.setOnAction(Event -> {
            btnSearchDrivers.getScene().getWindow().hide();
            setScene("/sample/searchDrivers.fxml");
        });
        btnSearchDTPs.setOnAction(Event -> {
            btnSearchDTPs.getScene().getWindow().hide();
            setScene("/sample/searchDTPs.fxml");
        });
        btnSearchTransport.setOnAction(Event -> {
            btnSearchTransport.getScene().getWindow().hide();
            setScene("/sample/searchTransport.fxml");
        });
    }
    public static void setScene(String window){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainWindowContr.class.getResource(window));

        try {
            loader.load();
        } catch (IOException exception) {
            System.out.println("Ошибка loader!!!");
        }

        Parent Root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(Root));
        stage.show();
    }

    /*public String changeLabels(ResultSet resultSet, String labelType) throws SQLException {
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
                textForLabel += count + ". " + resultSet.getNString(AllConstants.DTPConst.PLACE) + "; " +
                        resultSet.getNString(AllConstants.DTPConst.TIME) + "; " + resultSet.getNString(AllConstants.DTPConst.INSPECTOR) + "\n";
            }
    }
        return textForLabel;
    } */
}


