package edu.upc.dsa.minimo.Domain.Entity;

import javax.ws.rs.core.Link;
import java.util.LinkedList;
import java.util.List;

public class Game {
    String gameId;
    String gameName;
    String description;
    int gameLevelNumber;
    LinkedList<Level> gameLevels;
    LinkedList<User> gameUsers;
    int currentLevel;


    public Game(String gameId, String description, int gameLevelNumber) {
        this.gameId = gameId;
        this.description = description;
        this.gameLevelNumber = gameLevelNumber;
        this.gameLevels = new LinkedList<>();
        this.gameUsers=new LinkedList<>();
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
    } //Obtiene el número de niveles que tiene el juego

    public void setGameLevelNumber(int gameLevelNumber) {
        this.gameLevelNumber = gameLevelNumber;
    } //Introduce el número de niveles

    public LinkedList<Level> getGameLevels() {
        return gameLevels;
    }//Devuelve una lista con los niveles del juego
    public LinkedList<User> getGameUsers(){
        return gameUsers;
    }

    public void setGameLevels(LinkedList<Level> gameLevels) {
        this.gameLevels = gameLevels;
    }
    public void setGameUsers(User user){
        this.gameUsers.add(user);
    }

    public void addLevelToGame(String levelName, int points, String levelDate) {
        gameLevels.add(new Level(levelName, points, levelDate));
    }
    public void addUserToGame(String userName, String userSurname){
        gameUsers.add(new User(userName,userSurname));
    }

    public Level getLevel(int i) {
        return gameLevels.get(i - 1);//Si quiero obtener el primer nivel de un juego, pondré un 1 y me devolverá la posición 0, la primera posición
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
