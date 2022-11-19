package edu.upc.dsa.minimo.domain.entity.vo;
public class UserHistory {
    String userId;
    int points;
    public UserHistory(){}

    public UserHistory(String userId, int points) {
        this.userId = userId;
        this.points = points;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
