package edu.upc.dsa.minimo.domain.entity;

import edu.upc.dsa.minimo.domain.entity.exceptions.UserBussyException;
import edu.upc.dsa.minimo.domain.entity.exceptions.UserNotActiveException;
import edu.upc.dsa.minimo.domain.entity.vo.UserActivityOverGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    String userId;
    String userName;
    String userSurname;
    int userPoints;
    int userLevel;
    Game currentGame;
    Map<String,List<UserActivityOverGame>> gameInside; //Si no lo defino como HashMap me complico mucho la vida
    //De esta forma puedo obtener la actividad sobre un juego por parte de un usario dando la clave ID del juego
    //En un principio lo había puesto como una lista, pero en la implementación no sabía como seguir si era una lista
    List<UserActivityOverGame> list;
    List<Game> userGames;
    public User(){
        this.gameInside =new HashMap<>();
        this.userGames=new ArrayList<>();
        this.list=new ArrayList<>();
    }

    public User(String userId) {
        this();
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPoints=0;
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

    public int getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(int userPoints) {
        this.userPoints = userPoints;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
    public void startGame(edu.upc.dsa.minimo.domain.entity.Game game) throws UserBussyException{
        if(currentGame!=null){
            throw new UserBussyException();
        }
        this.currentGame = game;
        this.userLevel = 1;
        this.userPoints = 50;
        this.userGames.add(game);
    }
    public Game getCurrentGame() throws UserNotActiveException {
        if(this.currentGame == null){
            throw new UserNotActiveException();
        }
        return currentGame;
    }
    public void nextLevel(int points,String date) throws UserNotActiveException {
        if (this.currentGame == null) {
            throw new UserNotActiveException();
        }
        int aux=this.userLevel;
        this.gameInside.put(currentGame.getGameId(),list);
        this.userLevel = this.userLevel + 1;
        this.userPoints = this.userPoints + points;
        if (this.userLevel == this.currentGame.getNumLevels()) {
            this.currentGame = null;
        }
        //this.gameInside.put(getCurrentGame().gameId,new )
        this.list.add(new UserActivityOverGame(aux,this.userPoints,date));
        this.gameInside.put(currentGame.getGameId(),list);
        /*this.gameInside.get(currentGame.getGameId()).get(aux).setLevel(aux);
        this.gameInside.get(currentGame.getGameId()).get(aux).setDate(date);
        this.gameInside.get(currentGame.getGameId()).get(aux).setPoints(points);*/
    }
    public void finishGame(){
        this.currentGame=null;
    }

    public Map<String, List<UserActivityOverGame>> getGameInside() {
        return gameInside;
    }

    public void setGameInside(Map<String, List<UserActivityOverGame>> gameInside) {
        this.gameInside = gameInside;
    }

    public void setCurrentGame(edu.upc.dsa.minimo.domain.entity.Game currentGame) {
        this.currentGame = currentGame;
    }

    public List<edu.upc.dsa.minimo.domain.entity.Game> getUserGames() {
        return userGames;
    }

    public void setUserGames(List<edu.upc.dsa.minimo.domain.entity.Game> userGames) {
        this.userGames = userGames;
    }
}
