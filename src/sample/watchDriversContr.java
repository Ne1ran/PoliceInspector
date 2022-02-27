package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;


public class watchDriversContr {

    @FXML
    private Button goBackBtn;

    @FXML
    private Button btnSearchDrivers;

    @FXML
    private TableView<Driver> DriversTable = new TableView<Driver>();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        GetDataDrivers();

        goBackBtn.setOnAction(Event ->{
            goBackBtn.getScene().getWindow().hide();
           mainWindowContr.setScene("/sample/mainWindow.fxml");
        });

        btnSearchDrivers.setOnAction(Event ->{
            goBackBtn.getScene().getWindow().hide();
            mainWindowContr.setScene("/sample/mainWindow.fxml");
        });

    }
    private ObservableList<Driver> getDriversList() throws SQLException, ClassNotFoundException {
        ConnHandler handler = new ConnHandler();
        ObservableList<Driver> list = FXCollections.observableArrayList();
        ResultSet driversSet = handler.getDrivers();
        int count = 0;
        while(driversSet.next()){
            count++;
            if(count > 11){
                break;
            }
            Driver driver = new Driver(driversSet.getNString(AllConstants.DriversConst.NAME),
                            driversSet.getNString(AllConstants.DriversConst.SURNAME),
                            driversSet.getNString(AllConstants.DriversConst.DoB),
                            driversSet.getNString(AllConstants.DriversConst.LICENCEID),
                            driversSet.getNString(AllConstants.DriversConst.CARTYPE),
                            driversSet.getNString(AllConstants.DriversConst.CARNUMBER),
                            driversSet.getNString(AllConstants.DriversConst.PHONENUM),
                            driversSet.getNString(AllConstants.DriversConst.VIOLATIONS));
            list.add(driver);
        }

        return list;
    }
    private void GetDataDrivers() throws SQLException, ClassNotFoundException {
        TableColumn<Driver, String> name = new TableColumn<>("Имя");
        TableColumn<Driver, String> surname = new TableColumn<>("Фамилия");
        TableColumn<Driver, String> dateofbirth = new TableColumn<>("Дата рождения");
        TableColumn<Driver, String> licenceid = new TableColumn<>("Номер лицензии");
        TableColumn<Driver, String> car = new TableColumn<>("Марка машины");
        TableColumn<Driver, String> carnumber = new TableColumn<>("Номер машины");
        TableColumn<Driver, String> phonenumber = new TableColumn<>("Номер телефона");
        TableColumn<Driver, String> numofviolations = new TableColumn<>("Количество нарушений");

        DriversTable.getColumns().addAll(name, surname, dateofbirth, licenceid, car, carnumber, phonenumber, numofviolations);

        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        dateofbirth.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
        licenceid.setCellValueFactory(new PropertyValueFactory<>("licenceid"));
        car.setCellValueFactory(new PropertyValueFactory<>("car"));
        carnumber.setCellValueFactory(new PropertyValueFactory<>("carnumber"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        numofviolations.setCellValueFactory(new PropertyValueFactory<>("numofviolations"));

        ObservableList<Driver> drivers = getDriversList();
        DriversTable.setItems(drivers);
    }
}
