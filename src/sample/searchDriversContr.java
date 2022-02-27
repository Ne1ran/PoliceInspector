package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class searchDriversContr {

    @FXML
    private TableView<Driver> DriversTable;

    @FXML
    private TextField carnumberField;

    @FXML
    private Button goBackBtn;

    @FXML
    private TextField licencenumberField;

    @FXML
    private TextField nameField;

    @FXML
    private Label nothingFound;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField surnameField;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        loadTable();

        goBackBtn.setOnAction(Event -> {
            goBackBtn.getScene().getWindow().hide();
            mainWindowContr.setScene("/sample/mainWindow.fxml");
        });

        searchBtn.setOnAction(Event -> {
            try {
                searchDrivers();
            } catch (SQLException | ClassNotFoundException throwables) {
                System.out.println("Ошибка!!11!");
            }
        });
    }

    private ObservableList<Driver> getDriversList() throws SQLException, ClassNotFoundException {

        String surname = surnameField.getText().trim();
        String name = nameField.getText().trim();
        String licence = licencenumberField.getText().trim();
        String carnumber = carnumberField.getText().trim();

        if (!(surname.equals("") && name.equals("") && licence.equals("") && carnumber.equals(""))) {

            nothingFound.setText("");

            String query = "";
            if (!surname.equals("")){
                query += AllConstants.DriversConst.SURNAME + " = '" + surname + "' AND ";
            }
            if (!name.equals("")){
                query += AllConstants.DriversConst.NAME + " = '" + name + "' AND ";
            }
            if (!licence.equals("")){
                query += AllConstants.DriversConst.LICENCEID + " = '" + licence + "' AND ";
            }
            if (!carnumber.equals("")){
                query += AllConstants.DriversConst.CARNUMBER + " = '" + carnumber + "'";
            }
            if (query.substring(query.length() - 4).contains("AND")){
                query = query.substring(0, query.length()-4);
            }
            ConnHandler handler = new ConnHandler();
            ObservableList<Driver> list = FXCollections.observableArrayList();
            ResultSet driversSet = handler.searchDrivers(query.trim());
            int count = 0;
            while (driversSet.next()) {
                count++;
                if (count > 6) {
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
        } else {
            nothingFound.setText("Данные отсутствуют.");
            return null;
        }
    }
    private void searchDrivers() throws SQLException, ClassNotFoundException {
        ObservableList<Driver> drivers = getDriversList();
        DriversTable.setItems(drivers);
    }
    private void loadTable(){
        TableColumn<Driver, String> name = new TableColumn<>("Имя");
        TableColumn<Driver, String> surname = new TableColumn<>("Фамилия");
        TableColumn<Driver, String> dateofbirth = new TableColumn<>("Дата рождения");
        TableColumn<Driver, String> licenceid = new TableColumn<>("Номер лицензии");
        TableColumn<Driver, String> car = new TableColumn<>("Марка машины");
        TableColumn<Driver, String> carnumber = new TableColumn<>("Номер машины");
        TableColumn<Driver, String> phonenumber = new TableColumn<>("Номер телефона");
        TableColumn<Driver, String> numofviolations = new TableColumn<>("Количество нарушений");

        DriversTable.getColumns().addAll(name, surname, dateofbirth, licenceid, car, carnumber, phonenumber, numofviolations);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        dateofbirth.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
        licenceid.setCellValueFactory(new PropertyValueFactory<>("licenceid"));
        car.setCellValueFactory(new PropertyValueFactory<>("car"));
        carnumber.setCellValueFactory(new PropertyValueFactory<>("carnumber"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        numofviolations.setCellValueFactory(new PropertyValueFactory<>("numofviolations"));
    }
}

