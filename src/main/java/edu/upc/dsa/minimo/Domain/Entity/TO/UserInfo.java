package edu.upc.dsa.minimo.Domain.Entity.TO;

public class UserInfo {
    String userName;
    String userSurname;
    int puntuation;

    public UserInfo(String userName, String userSurname, int puntuation) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.puntuation = puntuation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public int getPuntuation() {
        return puntuation;
    }

    public void setPuntuation(int puntuation) {
        this.puntuation = puntuation;
    }
}
