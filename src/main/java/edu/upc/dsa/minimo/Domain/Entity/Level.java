package edu.upc.dsa.minimo.Domain.Entity;

import edu.upc.dsa.minimo.Domain.Entity.VO.Date;
import edu.upc.dsa.minimo.Domain.Entity.VO.RandomId;

public class Level {
    String levelId;
    String levelName;
    String levelDescription;
    int points;
    String levelDate;
    public Level(){
        this.levelId= RandomId.getId();
    }

    public Level(String levelName, String levelDescription, int points) {
        this.levelName = levelName;
        this.levelDescription = levelDescription;
        this.points = points;
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

    public String getLevelDescription() {
        return levelDescription;
    }

    public void setLevelDescription(String levelDescription) {
        this.levelDescription = levelDescription;
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

    public void setLevelDate() {
        this.levelDate = Date.getDate();
    }
}
