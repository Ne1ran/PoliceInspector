package sample;

public class User {
    private String login;
    private String userpassword;
    private String usersurname;
    private String userfirstname;
    private String dateofbirth;
    private String usertype;
    private String workingplace;

    public User(String login, String userpassword, String usersurname, String userfirstname, String dateofbirth, String usertype, String workingplace) {
        this.login = login;
        this.userpassword = userpassword;
        this.usersurname = usersurname;
        this.userfirstname = userfirstname;
        this.dateofbirth = dateofbirth;
        this.usertype = usertype;
        this.workingplace = workingplace;
    }

    public User() {

    }

    public String getlogin() {
        return login;
    }

    public void setlogin(String login) {
        this.login = login;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsersurname() {
        return usersurname;
    }

    public void setUsersurname(String usersurname) {
        this.usersurname = usersurname;
    }

    public String getUserfirstname() {
        return userfirstname;
    }

    public void setUserfirstname(String userfirstname) {
        this.userfirstname = userfirstname;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getWorkingplace() {
        return workingplace;
    }

    public void setWorkingplace(String workingplace) {
        this.workingplace = workingplace;
    }
}

