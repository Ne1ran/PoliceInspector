package sample;

import java.lang.annotation.Target;
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
        String insert = "INSERT INTO " + table + "(" + AllConstants.DTPConst.WHERE + ',' + AllConstants.DTPConst.TIME + ',' +
                AllConstants.DTPConst.CASUALTIES + ',' + AllConstants.DTPConst.DRIVERSINVOLVED + ',' + AllConstants.DTPConst.TYPEOFCAR + ',' +
                AllConstants.DTPConst.NUMBEROFCAR + ',' + AllConstants.DTPConst.INSPECTOR + ',' + AllConstants.DTPConst.REASON + ')' +
                "VALUES(?,?,?,?,?,?,?,?)";
        System.out.println("bbabb");
        PreparedStatement prst = getConnection().prepareStatement(insert);
        prst.setString(1, dtp.getWhere());
        prst.setString(2, dtp.getApproxtime());
        prst.setString(3, dtp.getCasualties());
        prst.setString(4, dtp.getDriversinvolved());
        prst.setString(5, dtp.getTypeofcar());
        prst.setString(6, dtp.getNumberofcar());
        prst.setString(7, dtp.getInspector());
        prst.setString(8, dtp.getReason());
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
