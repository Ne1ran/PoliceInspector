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

public class watchTransportsContr {

    @FXML
    private TableView<Transport> TransportsTable;

    @FXML
    private Button btnSearchTransport;

    @FXML
    private Button goBackBtn;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        GetDataTransport();

        goBackBtn.setOnAction(Event ->{
            goBackBtn.getScene().getWindow().hide();
            mainWindowContr.setScene("/sample/mainWindow.fxml");
        });

        btnSearchTransport.setOnAction(Event ->{
            goBackBtn.getScene().getWindow().hide();
            mainWindowContr.setScene("/sample/searchTransport.fxml");
        });

    }
    private ObservableList<Transport> getTransportList() throws SQLException, ClassNotFoundException {
        ConnHandler handler = new ConnHandler();
        ObservableList<Transport> list = FXCollections.observableArrayList();
        ResultSet transportSet = handler.getTransport();
        int count = 0;
        while(transportSet.next()){
            count++;
            if(count > 11){
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
    }
    private void GetDataTransport() throws SQLException, ClassNotFoundException {
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

        ObservableList<Transport> transports = getTransportList();
        TransportsTable.setItems(transports);
    }
}

