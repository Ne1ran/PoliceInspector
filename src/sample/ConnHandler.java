package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ConnHandler extends Config {
    Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://"+ Host + ":" + Port + "/" + Name;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connStr, User, Password);
        return connection;
    }

    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        String Table = AllConstants.UsersConst.USERSTABLE;
        String insert = "INSERT INTO " + Table + "(" + AllConstants.UsersConst.USER_LOGIN +
                ',' + AllConstants.UsersConst.USER_PASSWORD + ',' + AllConstants.UsersConst.USER_SURNAME + ',' + AllConstants.UsersConst.USER_NAME +
                ',' + AllConstants.UsersConst.DATEOFBIRTH + ',' + AllConstants.UsersConst.USER_TYPE + ',' + AllConstants.UsersConst.WORKINGPLACE + ')' +
                "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement prst = getConnection().prepareStatement(insert);
        prst.setString(1, user.getlogin());
        prst.setString(2, user.getUserpassword());
        prst.setString(3, user.getUsersurname());
        prst.setString(4, user.getUserfirstname());
        prst.setString(5, user.getDateofbirth());
        prst.setString(6, user.getUsertype());
        prst.setString(7, user.getWorkingplace());
        prst.executeUpdate();
    }

    public void addDTP(DTP dtp) throws SQLException, ClassNotFoundException{
        String table = AllConstants.DTPConst.DTPs_TABLE;
        String insert = "INSERT INTO " + table + "(" + AllConstants.DTPConst.PLACE + ',' + AllConstants.DTPConst.TIME + ',' +
                AllConstants.DTPConst.CASUALTIES + ',' + AllConstants.DTPConst.DRIVERSINVOLVED + ',' + AllConstants.DTPConst.TYPEOFCAR + ',' +
                AllConstants.DTPConst.NUMBEROFCAR + ',' + AllConstants.DTPConst.INSPECTOR + ',' + AllConstants.DTPConst.REASON + ')' +
                "VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement prst = getConnection().prepareStatement(insert);
        prst.setString(1, dtp.getPlace());
        prst.setString(2, dtp.getApproxtime());
        prst.setString(3, dtp.getCasualties());
        prst.setString(4, dtp.getDriversinvolved());
        prst.setString(5, dtp.getTypeofcar());
        prst.setString(6, dtp.getNumberofcar());
        prst.setString(7, dtp.getInspector());
        prst.setString(8, dtp.getReason());
        prst.executeUpdate();
    }

    public void addDriver(Driver driver) throws SQLException,ClassNotFoundException{
        String table = AllConstants.DriversConst.DRIVERSTABLE;
        String insert = "INSERT INTO " + table + "(" + AllConstants.DriversConst.NAME + ',' + AllConstants.DriversConst.SURNAME + ',' +
                AllConstants.DriversConst.DoB + ',' + AllConstants.DriversConst.LICENCEID + ',' + AllConstants.DriversConst.CARTYPE + ',' +
                AllConstants.DriversConst.CARNUMBER + ',' + AllConstants.DriversConst.PHONENUM + ',' + AllConstants.DriversConst.VIOLATIONS +
                ")VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement prst = getConnection().prepareStatement(insert);
        prst.setString(1, driver.getName());
        prst.setString(2, driver.getSurname());
        prst.setString(3, driver.getDateofbirth());
        prst.setString(4, driver.getLicenceid());
        prst.setString(5, driver.getCar());
        prst.setString(6, driver.getCarnumber());
        prst.setString(7, driver.getPhonenumber());
        prst.setString(8, driver.getNumofviolations());
        prst.executeUpdate();
    }

    public void addTransport(Transport transport) throws SQLException,ClassNotFoundException {
        String table = AllConstants.TransportConst.TRANSPORT_TABLE;
        String insert = "INSERT INTO " + table + "(" + AllConstants.TransportConst.NUMBER + ',' + AllConstants.TransportConst.BRAND + ',' +
                AllConstants.TransportConst.DRIVERSURNAME + ',' + AllConstants.TransportConst.DRIVERNAME + ',' + AllConstants.TransportConst.COLOR + ',' +
                AllConstants.TransportConst.MODEL + ")VALUES(?,?,?,?,?,?)";
        PreparedStatement prst = getConnection().prepareStatement(insert);
        prst.setString(1, transport.getNumber());
        prst.setString(2, transport.getBrand());
        prst.setString(3, transport.getDriversurname());
        prst.setString(4, transport.getDrivername());
        prst.setString(5, transport.getColor());
        prst.setString(6, transport.getModel());
        prst.executeUpdate();
    }

    public ResultSet getUser(User user) throws  SQLException, ClassNotFoundException{
        ResultSet rset = null;
        String select = "SELECT * FROM " + AllConstants.UsersConst.USERSTABLE + " WHERE " +
                AllConstants.UsersConst.USER_LOGIN + "=? AND " + AllConstants.UsersConst.USER_PASSWORD + "=?";

        PreparedStatement prst = getConnection().prepareStatement(select);
        prst.setString(1, user.getlogin());
        prst.setString(2, user.getUserpassword());
        rset = prst.executeQuery();

        return rset;
    }

    public ResultSet getDrivers() throws  SQLException, ClassNotFoundException{
        ResultSet rset = null;
        String select = "SELECT * FROM " + AllConstants.DriversConst.DRIVERSTABLE;

        PreparedStatement prst = getConnection().prepareStatement(select);
        rset = prst.executeQuery();

        return rset;
    }
    public ResultSet getDTPs() throws  SQLException, ClassNotFoundException{
        ResultSet rset = null;
        String select = "SELECT * FROM " + AllConstants.DTPConst.DTPs_TABLE;

        PreparedStatement prst = getConnection().prepareStatement(select);
        rset = prst.executeQuery();

        return rset;
    }
    public ResultSet getTransport() throws  SQLException, ClassNotFoundException{
        ResultSet rset = null;
        String select = "SELECT * FROM " + AllConstants.TransportConst.TRANSPORT_TABLE;

        PreparedStatement prst = getConnection().prepareStatement(select);
        rset = prst.executeQuery();

        return rset;
    }
}
