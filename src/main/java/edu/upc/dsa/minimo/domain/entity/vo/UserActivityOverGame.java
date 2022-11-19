package edu.upc.dsa.minimo.domain.entity.vo;

public class UserActivityOverGame {
    int level;
    int points;
    String date;
    public UserActivityOverGame(){}

    public UserActivityOverGame(int level, int points, String date) {
        this.level = level;
        this.points = points;
        this.date = date;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
