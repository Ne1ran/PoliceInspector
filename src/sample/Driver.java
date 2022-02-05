package sample;

public class Driver {
    private String name;
    private String surname;
    private String dateofbirth;
    private String licenceid;
    private String car;
    private String carnumber;
    private String phonenumber;
    private String numofviolations;

    public Driver(String name, String surname, String dateofbirth, String licenceid, String car, String carnumber, String phonenumber, String numofviolations) {
        this.name = name;
        this.surname = surname;
        this.dateofbirth = dateofbirth;
        this.licenceid = licenceid;
        this.car = car;
        this.carnumber = carnumber;
        this.phonenumber = phonenumber;
        this.numofviolations = numofviolations;
    }

    public Driver() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getLicenceid() {
        return licenceid;
    }

    public void setLicenceid(String licenceid) {
        this.licenceid = licenceid;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getNumofviolations() {
        return numofviolations;
    }

    public void setNumofviolations(String numofviolations) {
        this.numofviolations = numofviolations;
    }
}
