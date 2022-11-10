package edu.upc.dsa.minimo.Domain.Entity.TO;

import edu.upc.dsa.minimo.Domain.Entity.Level;

import java.util.LinkedList;

public class GameInfo {
    String gameId;
    String description;
    int numLevels;

    LinkedList<Level> elements;

    public GameInfo(String gameId, String description, int numLevels) {
        this.gameId = gameId;
        this.description = description;
        this.numLevels = numLevels;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumLevels() {
        return numLevels;
    }

    public void setNumLevels(int numLevels) {
        this.numLevels = numLevels;
    }

    public LinkedList<Level> getElements() {
        return elements;
    }

    public void setElements(LinkedList<Level> elements) {
        this.elements = elements;
    }
}
