package edu.upc.dsa.minimo.Domain.Entity;

import java.util.LinkedList;

public class Game {
    String gameId;
    String description;
    int numLevels;

    LinkedList<Level> elements;
    public Game(String gameId,String description,int numLevels){
        this.gameId = gameId;
        this.elements = new LinkedList<>();
        this.description=description;
        this.numLevels=numLevels;
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

    public int getNumLevels() {
        return numLevels;
    }

    public void setNumLevels(int numLevels) {
        this.numLevels = numLevels;
    }
    public void addLevel(String id,String name,int points){
        elements.add(new Level(id,name,points));
    }
    public Level getLevel(LinkedList<Level> levels,int i){
        return elements.get(i);
    }
    public int getNumberLevels(){
        return elements.size();
    }
    public void nextLevel(User user){
        int i=0;
        if(getLevel(elements,i).getPoints()<=user.getPoints()){
            i=i+1;
        }
    }
}
