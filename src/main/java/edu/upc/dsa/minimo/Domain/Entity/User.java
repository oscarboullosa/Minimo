package edu.upc.dsa.minimo.Domain.Entity;

import edu.upc.dsa.minimo.Domain.Entity.Exceptions.GameNotExists;

import java.util.LinkedList;
import java.util.List;

public class User {
    String userId;
    String userName;
    String userSurname;
    int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    List<Game> gamesPlayed;
    List<Level> levelsPassed;

    public User(String userId, String userName, String userSurname, List<Game> gamesPlayed) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.gamesPlayed = new LinkedList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
    public void addGamePlayed(Game game){
        this.gamesPlayed.add(game);
    }
    public List<Game> getGamesPlayed() {
        return gamesPlayed;
    }

}