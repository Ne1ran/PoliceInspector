package sample;

public class AllConstants {
    public static class UsersConst {
        public static final String USERSTABLE = "users";

        public static final String USER_ID = "idusers";
        public static final String USER_LOGIN = "username";
        public static final String USER_PASSWORD = "userpassword";
        public static final String USER_SURNAME = "usersurname";
        public static final String USER_NAME = "userfirstname";
        public static final String DATEOFBIRTH = "dateofbirth";
        public static final String USER_TYPE = "usertype";
        public static final String WORKINGPLACE = "workingplace";
    }
    public static class DriversConst {
        public static final String DRIVERSTABLE = "drivers";

        public static final String ID = "idDrivers";
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String DoB = "dateofbirth";
        public static final String LICENCEID = "licenceid";
        public static final String CARTYPE = "car";
        public static final String CARNUMBER = "carnumber";
        public static final String PHONENUM = "phonenumber";
        public static final String VIOLATIONS = "numofviolations";

    }

    public static class TransportConst {
        public static final String TRANSPORT_TABLE = "transport";

        public static final String ID = "idtransport";
        public static final String NUMBER = "number";
        public static final String BRAND = "brand";
        public static final String DRIVERSURNAME = "driversurname";
        public static final String DRIVERNAME = "drivername";
        public static final String COLOR = "color";
        public static final String MODEL = "model";
    }

    public static class DTPConst {
        public static final String DTPs_TABLE = "incidents";

        public static final String ID = "incidentid";
        public static final String PLACE = "place";
        public static final String TIME = "approxtime";
        public static final String CASUALTIES = "casualties";
        public static final String DRIVERSINVOLVED = "driversinvolved";
        public static final String TYPEOFCAR = "typeofcar";
        public static final String NUMBEROFCAR = "numberofcar";
        public static final String INSPECTOR = "inspector";
        public static final String REASON = "reason";

    }


}