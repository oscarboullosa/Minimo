package edu.upc.dsa.minimo.Domain.Entity.TO;

public class UserInfo {
    String userId;
    String userName;
    String userSurname;
    int points;

    public UserInfo(String userId, String userName, String userSurname, int points) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.points = points;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
