package edu.upc.dsa.minimo.Domain.Entity;

import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserBussyException;
import edu.upc.dsa.minimo.Domain.Entity.TO.LevelInfo;

import java.util.LinkedList;
import java.util.List;

import static edu.upc.dsa.minimo.Domain.Entity.VO.Date.getDate;

public class User {
    String userId;
    String userName;
    String userSurname;
    int puntuation;
    LinkedList<Game> userGames;
    Game currentGame;//Lo uso por simplificar un poco las cosas. Realmente sería el último elemento de la lista de userGames.
    int currentLevel;//Posicion en la que está el nivel actual en la lista de niveles de un juego
    LevelInfo level;
    public User(String userId,String userName, String userSurname) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userId=userId;
        this.userGames=new LinkedList<>();
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

    public int getPuntuation() {
        return puntuation;
    }

    public void setPuntuation(int puntuation) {
        this.puntuation = puntuation;
    }

    public List<Game> getUserGames() {//Devuelve una lista de juegos a los que ha jugado el usuario. Logicamente, el juego actual será el último que se ha añadido
        return userGames;
    }


    public void setUserGames(Game newGame) throws UserBussyException { //Esta funcion añade un juego a un usuario, de esta forma podremos obtener todos los juegos a los que ha jugado el usuario. El último juego que añade será el juego al que juega actualmente
        if(currentGame!=null){
            throw new UserBussyException();
        }
        this.userGames.add(newGame);
        this.currentGame=newGame;
        this.currentLevel=1;
        this.puntuation=50;

    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public int getLevel() {

        return this.currentGame.getCurrentLevel();//me da el nivel actual en el que está el usuario
    }

    public void pasteDateToLevel(){
        level.setLevelDate(getDate());
    }
    public void setNextLevel() {
        this.currentLevel=this.currentGame.getCurrentLevel()+1;
    }
    public void finishGame(){
        this.currentGame=null;
        this.currentLevel=1;
    }
}