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
        String Table = AllConstants.USERSTABLE;
        String insert = "INSERT INTO " + Table + "(" + AllConstants.USER_LOGIN +
                ',' + AllConstants.USER_PASSWORD + ',' + AllConstants.USER_SURNAME + ',' + AllConstants.USER_NAME +
                ',' + AllConstants.DATEOFBIRTH + ',' + AllConstants.USER_TYPE + ',' + AllConstants.WORKINGPLACE + ')' +
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

    public ResultSet getUser(User user) throws  SQLException, ClassNotFoundException{
        ResultSet rset = null;
        String select = "SELECT * FROM " + AllConstants.USERSTABLE + " WHERE " +
                AllConstants.USER_LOGIN + "=? AND " + AllConstants.USER_PASSWORD + "=?";

        PreparedStatement prst = getConnection().prepareStatement(select);
        prst.setString(1, user.getlogin());
        prst.setString(2, user.getUserpassword());
        rset = prst.executeQuery();

        return rset;
    }
}
