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

public class watchDTPsContr {

    @FXML
    private TableView<DTP> DTPsTable;

    @FXML
    private Button btnSearchDTPs;

    @FXML
    private Button goBackBtn;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        GetDataDTPs();

        goBackBtn.setOnAction(Event ->{
            goBackBtn.getScene().getWindow().hide();
            mainWindowContr.setScene("/sample/mainWindow.fxml");
        });

        btnSearchDTPs.setOnAction(Event ->{
            goBackBtn.getScene().getWindow().hide();
            mainWindowContr.setScene("/sample/searchDTPs.fxml");
        });

    }
    private ObservableList<DTP> getDTPsList() throws SQLException, ClassNotFoundException {
        ConnHandler handler = new ConnHandler();
        ObservableList<DTP> list = FXCollections.observableArrayList();
        ResultSet DTPsSet = handler.getDTPs();
        int count = 0;
        while(DTPsSet.next()){
            count++;
            if(count > 11){
                break;
            }
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

    private void GetDataDTPs() throws SQLException, ClassNotFoundException {
        TableColumn<DTP, String> place = new TableColumn<>("Место");
        TableColumn<DTP, String> approxtime = new TableColumn<>("Время");
        TableColumn<DTP, String> casualties = new TableColumn<>("Количество жертв");
        TableColumn<DTP, String> driversinvolved = new TableColumn<>("Водители");
        TableColumn<DTP, String> typeofcar = new TableColumn<>("Марки машин");
        TableColumn<DTP, String> numberofcar = new TableColumn<>("Номера машин");
        TableColumn<DTP, String> inspector = new TableColumn<>("Инспектор");
        TableColumn<DTP, String> reason = new TableColumn<>("Причина");

        DTPsTable.getColumns().addAll(place, approxtime, casualties, driversinvolved, typeofcar, numberofcar, inspector, reason);

        place.setCellValueFactory(new PropertyValueFactory<>("place"));
        approxtime.setCellValueFactory(new PropertyValueFactory<>("approxtime"));
        casualties.setCellValueFactory(new PropertyValueFactory<>("casualties"));
        driversinvolved.setCellValueFactory(new PropertyValueFactory<>("driversinvolved"));
        typeofcar.setCellValueFactory(new PropertyValueFactory<>("typeofcar"));
        numberofcar.setCellValueFactory(new PropertyValueFactory<>("numberofcar"));
        inspector.setCellValueFactory(new PropertyValueFactory<>("inspector"));
        reason.setCellValueFactory(new PropertyValueFactory<>("reason"));

        ObservableList<DTP> dtps = getDTPsList();
        DTPsTable.setItems(dtps);
    }
}

