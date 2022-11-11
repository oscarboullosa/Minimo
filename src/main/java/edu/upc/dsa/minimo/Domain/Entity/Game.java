package edu.upc.dsa.minimo.Domain.Entity;

import java.util.LinkedList;
import java.util.List;

public class Game {
    String gameId;
    String gameName;
    String description;
    int gameLevelNumber;
    LinkedList<Level> gameLevels;

    public Game(String gameId, String description, int gameLevelNumber) {
        this.gameId = gameId;
        this.description = description;
        this.gameLevelNumber = gameLevelNumber;
        this.gameLevels=new LinkedList<>();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGameLevelNumber() {
        return gameLevelNumber;
    }

    public void setGameLevelNumber(int gameLevelNumber) {
        this.gameLevelNumber = gameLevelNumber;
    }

    public LinkedList<Level> getGameLevels() {
        return gameLevels;
    }

    public void setGameLevels(LinkedList<Level> gameLevels) {
        this.gameLevels = gameLevels;
    }
}
