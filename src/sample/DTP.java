package sample;

public class DTP {
    private String where;
    private String approxtime;
    private String casualties;
    private String driversinvolved;
    private String typeofcar;
    private String numberofcar;
    private String inspector;
    private String reason;

    public DTP(String where, String approxtime, String casualties, String driversinvolved, String typeofcar, String numberofcar, String inspector, String reason) {
        this.where = where;
        this.approxtime = approxtime;
        this.casualties = casualties;
        this.driversinvolved = driversinvolved;
        this.typeofcar = typeofcar;
        this.numberofcar = numberofcar;
        this.inspector = inspector;
        this.reason = reason;
    }

    public DTP() {

    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getApproxtime() {
        return approxtime;
    }

    public void setApproxtime(String approxtime) {
        this.approxtime = approxtime;
    }

    public String getCasualties() {
        return casualties;
    }

    public void setCasualties(String casualties) {
        this.casualties = casualties;
    }

    public String getDriversinvolved() {
        return driversinvolved;
    }

    public void setDriversinvolved(String driversinvolved) {
        this.driversinvolved = driversinvolved;
    }

    public String getTypeofcar() {
        return typeofcar;
    }

    public void setTypeofcar(String typeofcar) {
        this.typeofcar = typeofcar;
    }

    public String getNumberofcar() {
        return numberofcar;
    }

    public void setNumberofcar(String numberofcar) {
        this.numberofcar = numberofcar;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
