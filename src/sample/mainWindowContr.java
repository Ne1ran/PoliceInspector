package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
    private TableView<Driver> driversTable;

    @FXML
    private TableView<DTP> dtpTable;

    @FXML
    private Label labelNoPerms;

    @FXML
    private Label mainLabel;

    @FXML
    private TableView<Transport> transportTable;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        if (CurrentlyLoggedUser.getPerms().equals("Admin")){
            mainLabel.setText("С возвращением, администратор " + CurrentlyLoggedUser.getSurname() + " " + CurrentlyLoggedUser.getName());
        } else {
            mainLabel.setText("С возвращением, инспектор " + CurrentlyLoggedUser.getSurname() + " " + CurrentlyLoggedUser.getName());
        }

        loadTables();
        loadDataInTables();

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

    private void loadTables(){
        loadDriversTable();
        loadDTPTable();
        loadTransportTable();
    }

    private void loadTransportTable(){
        TableColumn<Transport, String> number = new TableColumn<>("Номер");
        TableColumn<Transport, String> brand = new TableColumn<>("Название бренда");
        TableColumn<Transport, String> driversurname = new TableColumn<>("Фамилия водителя");
        TableColumn<Transport, String> drivername = new TableColumn<>("Имя водителя");
        TableColumn<Transport, String> color = new TableColumn<>("Цвет машины");
        TableColumn<Transport, String> model = new TableColumn<>("Модель машины");

        transportTable.getColumns().addAll(number, brand, driversurname, drivername, color, model);

        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        driversurname.setCellValueFactory(new PropertyValueFactory<>("driversurname"));
        drivername.setCellValueFactory(new PropertyValueFactory<>("drivername"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
    }

    private void loadDTPTable(){
        TableColumn<DTP, String> place = new TableColumn<>("Место");
        TableColumn<DTP, String> approxtime = new TableColumn<>("Время");
        TableColumn<DTP, String> casualties = new TableColumn<>("Количество жертв");
        TableColumn<DTP, String> driversinvolved = new TableColumn<>("Водители");
        TableColumn<DTP, String> typeofcar = new TableColumn<>("Марки машин");
        TableColumn<DTP, String> numberofcar = new TableColumn<>("Номера машин");
        TableColumn<DTP, String> inspector = new TableColumn<>("Инспектор");
        TableColumn<DTP, String> reason = new TableColumn<>("Причина");

        dtpTable.getColumns().addAll(place, approxtime, casualties, driversinvolved, typeofcar, numberofcar, inspector, reason);

        place.setCellValueFactory(new PropertyValueFactory<>("place"));
        approxtime.setCellValueFactory(new PropertyValueFactory<>("approxtime"));
        casualties.setCellValueFactory(new PropertyValueFactory<>("casualties"));
        driversinvolved.setCellValueFactory(new PropertyValueFactory<>("driversinvolved"));
        typeofcar.setCellValueFactory(new PropertyValueFactory<>("typeofcar"));
        numberofcar.setCellValueFactory(new PropertyValueFactory<>("numberofcar"));
        inspector.setCellValueFactory(new PropertyValueFactory<>("inspector"));
        reason.setCellValueFactory(new PropertyValueFactory<>("reason"));
    }

    private void loadDriversTable(){
        TableColumn<Driver, String> name = new TableColumn<>("Имя");
        TableColumn<Driver, String> surname = new TableColumn<>("Фамилия");
        TableColumn<Driver, String> dateofbirth = new TableColumn<>("Дата рождения");
        TableColumn<Driver, String> licenceid = new TableColumn<>("Номер лицензии");
        TableColumn<Driver, String> car = new TableColumn<>("Марка машины");
        TableColumn<Driver, String> carnumber = new TableColumn<>("Номер машины");
        TableColumn<Driver, String> phonenumber = new TableColumn<>("Номер телефона");
        TableColumn<Driver, String> numofviolations = new TableColumn<>("Количество нарушений");

        driversTable.getColumns().addAll(name, surname, dateofbirth, licenceid, car, carnumber, phonenumber, numofviolations);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        dateofbirth.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));
        licenceid.setCellValueFactory(new PropertyValueFactory<>("licenceid"));
        car.setCellValueFactory(new PropertyValueFactory<>("car"));
        carnumber.setCellValueFactory(new PropertyValueFactory<>("carnumber"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        numofviolations.setCellValueFactory(new PropertyValueFactory<>("numofviolations"));
    }

    private void loadDataInTables() throws SQLException, ClassNotFoundException {
        dataDrivers();
        dataDTPs();
        dataTransports();
    }

    private void dataTransports() throws SQLException, ClassNotFoundException {
        ObservableList<Transport> transports = getTransportsList();
        transportTable.setItems(transports);
    }

    private void dataDrivers() throws SQLException, ClassNotFoundException {
        ObservableList<Driver> drivers = getDriversList();
        driversTable.setItems(drivers);
    }

    private void dataDTPs() throws SQLException, ClassNotFoundException {
        ObservableList<DTP> DTPs = getDTPsList();
        dtpTable.setItems(DTPs);
    }

    private ObservableList<Driver> getDriversList() throws SQLException, ClassNotFoundException {
        ConnHandler handler = new ConnHandler();
        ObservableList<Driver> list = FXCollections.observableArrayList();
        ResultSet driversSet = handler.getDrivers();
        while(driversSet.next()){
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

    private ObservableList<DTP> getDTPsList() throws SQLException, ClassNotFoundException {
        ConnHandler handler = new ConnHandler();
        ObservableList<DTP> list = FXCollections.observableArrayList();
        ResultSet DTPsSet = handler.getDTPs();
        while(DTPsSet.next()){
            DTP dtp = new DTP(DTPsSet.getNString(AllConstants.DTPConst.PLACE),
                    DTPsSet.getNString(AllConstants.DTPConst.TIME),
                    DTPsSet.getNString(AllConstants.DTPConst.CASUALTIES),
                    DTPsSet.getNString(AllConstants.DTPConst.DRIVERSINVOLVED),
                    DTPsSet.getNString(AllConstants.DTPConst.TYPEOFCAR),
                    DTPsSet.getNString(AllConstants.DTPConst.NUMBEROFCAR),
                    DTPsSet.getNString(AllConstants.DTPConst.INSPECTOR),
                    DTPsSet.getNString(AllConstants.DTPConst.REASON));
            list.add(dtp);
        }

        return list;
    }

    private ObservableList<Transport> getTransportsList() throws SQLException, ClassNotFoundException {
        ConnHandler handler = new ConnHandler();
        ObservableList<Transport> list = FXCollections.observableArrayList();
        ResultSet transportSet = handler.getTransport();
        while(transportSet.next()){
            Transport transport = new Transport(transportSet.getNString(AllConstants.TransportConst.NUMBER),
                    transportSet.getNString(AllConstants.TransportConst.BRAND),
                    transportSet.getNString(AllConstants.TransportConst.DRIVERSURNAME),
                    transportSet.getNString(AllConstants.TransportConst.DRIVERNAME),
                    transportSet.getNString(AllConstants.TransportConst.COLOR),
                    transportSet.getNString(AllConstants.TransportConst.MODEL));
            list.add(transport);
        }

        return list;
    }




















    public static void setScene(String window){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(mainWindowContr.class.getResource(window));

        try {
            loader.load();
        } catch (IOException exception) {
            System.out.println("Ошибка loader!");
        }

        Parent Root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(Root));
        stage.setTitle("Traffic Police Inspector");
        stage.getIcons().add(new Image("icon.jpg"));
        stage.show();
    }
}


