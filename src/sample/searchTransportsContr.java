package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class searchTransportsContr {

    @FXML
    private TableView<Transport> TransportsTable;

    @FXML
    private TextField carnumberField;

    @FXML
    private Button goBackBtn;

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
                searchTransports();
            } catch (SQLException | ClassNotFoundException throwables) {
                nothingFound.setText("Ошибка поиска");
            }
        });
    }

    private ObservableList<Transport> getTransportsList() throws SQLException, ClassNotFoundException {

        String surname = surnameField.getText().trim();
        String name = nameField.getText().trim();
        String carnumber = carnumberField.getText().trim();

        if (!(surname.equals("") && name.equals("") && carnumber.equals(""))) {

            nothingFound.setText("");

            String query = "";
            if (!surname.equals("")){
                query += AllConstants.TransportConst.DRIVERSURNAME + " = '" + surname + "' AND ";
            }
            if (!name.equals("")){
                query += AllConstants.TransportConst.DRIVERNAME + " = '" + name + "' AND ";
            }
            if (!carnumber.equals("")){
                query += AllConstants.TransportConst.NUMBER + " = '" + carnumber + "'";
            }
            if (query.substring(query.length() - 4).contains("AND")){
                query = query.substring(0, query.length()-4);
            }
            ConnHandler handler = new ConnHandler();
            ObservableList<Transport> list = FXCollections.observableArrayList();
            ResultSet transportSet = handler.searchTransports(query.trim());
            int count = 0;
            while (transportSet.next()) {
                count++;
                if (count > 6) {
                    break;
                }
                Transport transport = new Transport(transportSet.getNString(AllConstants.TransportConst.NUMBER),
                        transportSet.getNString(AllConstants.TransportConst.BRAND),
                        transportSet.getNString(AllConstants.TransportConst.DRIVERSURNAME),
                        transportSet.getNString(AllConstants.TransportConst.DRIVERNAME),
                        transportSet.getNString(AllConstants.TransportConst.COLOR),
                        transportSet.getNString(AllConstants.TransportConst.MODEL));
                list.add(transport);
            }

            return list;
        } else {
            nothingFound.setText("Данные отсутствуют.");
            return null;
        }
    }
    private void searchTransports() throws SQLException, ClassNotFoundException {

        ObservableList<Transport> transports = getTransportsList();
        TransportsTable.setItems(transports);

    }
    private void loadTable(){
        TableColumn<Transport, String> number = new TableColumn<>("Номер");
        TableColumn<Transport, String> brand = new TableColumn<>("Название бренда");
        TableColumn<Transport, String> driversurname = new TableColumn<>("Фамилия водителя");
        TableColumn<Transport, String> drivername = new TableColumn<>("Имя водителя");
        TableColumn<Transport, String> color = new TableColumn<>("Цвет машины");
        TableColumn<Transport, String> model = new TableColumn<>("Модель машины");

        TransportsTable.getColumns().addAll(number, brand, driversurname, drivername, color, model);

        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        driversurname.setCellValueFactory(new PropertyValueFactory<>("driversurname"));
        drivername.setCellValueFactory(new PropertyValueFactory<>("drivername"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
    }
}
