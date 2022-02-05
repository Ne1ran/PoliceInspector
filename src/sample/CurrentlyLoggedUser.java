package sample;

public class CurrentlyLoggedUser {
    public static String perms;
    public static String surname;
    public static String name;

    public CurrentlyLoggedUser() {

    }

    public static String getPerms() {
        return perms;
    }

    public static void setPerms(String perms) {
        CurrentlyLoggedUser.perms = perms;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        CurrentlyLoggedUser.surname = surname;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        CurrentlyLoggedUser.name = name;
    }
}
