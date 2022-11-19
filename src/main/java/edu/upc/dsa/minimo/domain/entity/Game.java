package edu.upc.dsa.minimo.domain.entity;

import edu.upc.dsa.minimo.domain.entity.exceptions.UserNotActiveException;
import edu.upc.dsa.minimo.domain.entity.vo.UserHistory;

import java.util.ArrayList;
import java.util.List;

public class Game {
    String gameId;
    String gameDescription;
    int numLevels;
    List<UserHistory> userHistory;
    public Game(){
        this.userHistory =new ArrayList<>();
    }

    public Game(String gameId, String gameDescription, int numLevels) {
        this();
        this.gameId = gameId;
        this.gameDescription = gameDescription;
        this.numLevels = numLevels;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public int getNumLevels() {
        return numLevels;
    }

    public void setNumLevels(int numLevels) {
        this.numLevels = numLevels;
    }
    public void gamesPlayedByUser(User user) throws UserNotActiveException{
        this.userHistory.add(new UserHistory(user.getUserId(),user.getUserPoints()));
    }

    public List<UserHistory> getHistory() {
        return userHistory;
    }

    public void setHistory(List<UserHistory> userHistory) {
        this.userHistory = userHistory;
    }
    public void orderByPoints(){
        this.userHistory.sort((UserHistory h1, UserHistory h2)->(h1.getPoints() - h2.getPoints()));
    }
}
