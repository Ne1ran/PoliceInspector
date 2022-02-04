package sample;

public class CurrentlyLoggedUser { //пока не юзаем
    String perms;
    String surname;
    String name;

    public CurrentlyLoggedUser(String perms, String surname, String name) {
        this.perms = perms;
        this.surname = surname;
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
