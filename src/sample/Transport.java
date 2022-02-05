package sample;

public class Transport {
    private String number;
    private String brand;
    private String driversurname;
    private String drivername;
    private String color;
    private String model;

    public Transport(String number, String brand, String driversurname, String drivername, String color, String model) {
        this.number = number;
        this.brand = brand;
        this.driversurname = driversurname;
        this.drivername = drivername;
        this.color = color;
        this.model = model;
    }

    public Transport() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDriversurname() {
        return driversurname;
    }

    public void setDriversurname(String driversurname) {
        this.driversurname = driversurname;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
