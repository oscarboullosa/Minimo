package edu.upc.dsa.minimo.Domain.Entity.TO;

public class LevelInfo {
    String levelName;
    int points;
    String levelDate;

    public LevelInfo(String levelName, int points, String levelDate) {
        this.levelName = levelName;
        this.points = points;
        this.levelDate = levelDate;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getLevelDate() {
        return levelDate;
    }

    public void setLevelDate(String levelDate) {
        this.levelDate = levelDate;
    }
}
