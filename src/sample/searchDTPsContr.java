package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class searchDTPsContr {

    @FXML
    private TextField DTPnumberField;

    @FXML
    private TableView<DTP> DTPsTable;

    @FXML
    private TextField carnumberField;

    @FXML
    private Button goBackBtn;

    @FXML
    private TextField inspectorField;

    @FXML
    private Label nothingFound;

    @FXML
    private Button searchBtn;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        loadTable();

        goBackBtn.setOnAction(Event -> {
            goBackBtn.getScene().getWindow().hide();
            mainWindowContr.setScene("/sample/mainWindow.fxml");
        });

        searchBtn.setOnAction(Event -> {
            try {
                searchDTPs();
            } catch (SQLException | ClassNotFoundException throwables) {
                nothingFound.setText("Ошибка поиска");
            }
        });
    }

    private ObservableList<DTP> getDTPsList() throws SQLException, ClassNotFoundException {

        String dtpnumber = DTPnumberField.getText().trim();
        String inspector = inspectorField.getText().trim();
        String carnumber = carnumberField.getText().trim();

        if (!(dtpnumber.equals("") && inspector.equals("") && carnumber.equals(""))) {

            nothingFound.setText("");

            String query = "";
            if (!dtpnumber.equals("")){
                query += AllConstants.DTPConst.ID + " = '" + dtpnumber + "' AND ";
            }
            if (!inspector.equals("")){
                query += AllConstants.DTPConst.INSPECTOR + " = '" + inspector + "' AND ";
            }
            if (!carnumber.equals("")){
                query += AllConstants.DTPConst.NUMBEROFCAR + " = '" + carnumber + "' AND ";
            }
            if (query.substring(query.length() - 4).contains("AND")){
                query = query.substring(0, query.length()-4);
            }
            ConnHandler handler = new ConnHandler();
            ObservableList<DTP> list = FXCollections.observableArrayList();
            ResultSet DTPsSet = handler.searchDTPs(query.trim());
            int count = 0;
            while (DTPsSet.next()) {
                count++;
                if (count > 6) {
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
        } else {
            nothingFound.setText("Данные отсутствуют.");
            return null;
        }
    }
    private void searchDTPs() throws SQLException, ClassNotFoundException {
        ObservableList<DTP> DTPs = getDTPsList();
        DTPsTable.setItems(DTPs);
    }
    private void loadTable(){
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
    }
}

