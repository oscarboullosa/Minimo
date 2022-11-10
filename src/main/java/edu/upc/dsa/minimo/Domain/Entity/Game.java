package edu.upc.dsa.minimo.Domain.Entity;

import java.util.LinkedList;

public class Game {
    String gameId;
    String description;

    LinkedList<Level> elements;
    public Game(String gameId){
        this.gameId = gameId;
        this.elements = new LinkedList<>();
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

    public LinkedList<Level> getElements() {
        return elements;
    }

    public void setElements(LinkedList<Level> elements) {
        this.elements = elements;
    }
}
