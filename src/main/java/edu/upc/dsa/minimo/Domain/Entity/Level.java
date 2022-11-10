package edu.upc.dsa.minimo.Domain.Entity;

import java.util.Objects;

public class Level {
    String levelId;
    String levelName;
    int points;
    int numLevels;
    String date;

    public Level(String levelId, String levelName, int points, String date,int numLevels) {
        this.levelId = levelId;
        this.levelName = levelName;
        this.points = points;
        this.date = date;
        this.numLevels=numLevels;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void levelsPassed(int quantity){
        this.numLevels=numLevels+quantity;
    }
    public boolean isNull(){
        return(Objects.equals(levelId,""));
    }
}
