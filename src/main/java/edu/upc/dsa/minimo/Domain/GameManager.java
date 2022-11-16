package edu.upc.dsa.minimo.Domain;

import edu.upc.dsa.minimo.Domain.Entity.Exceptions.GameNotFoundException;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserBussyException;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserNotActiveGameException;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserNotFoundException;
import edu.upc.dsa.minimo.Domain.Entity.Game;
import edu.upc.dsa.minimo.Domain.Entity.Level;
import edu.upc.dsa.minimo.Domain.Entity.TO.LevelInfo;
import edu.upc.dsa.minimo.Domain.Entity.User;
import edu.upc.dsa.minimo.Domain.Entity.VO.CurrentGame;

import java.util.List;

public interface GameManager {
/*Creación de un juego. Para la creación de un juego se debe indicar un
identificador, una descripción y un número de niveles (nivel1, nivel2, …
nivel3).*/
    public void addGame(String gameId,String description,int levelNum);
/*Inicio de una partida de un juego por parte de un usuario. Se debe
indicar el identificador del juego y el identificador del usuario. El
resultado de la operación es que el usuario entra en el primer nivel con
50 puntos iniciales. En caso que el usuario o juego no existan, se deberá
indicar el error. Un jugador SÓLO puede estar en una partida al mismo
tiempo. En caso que el jugador ya tenga una partida activa, se deberá
indicar el error.*/
    public void addUserToGame(String gameId,String userId) throws UserNotFoundException, UserBussyException,GameNotFoundException;
/*Consulta del nivel actual. Dado un identificador de un usuario que está
en una partida en curso, se deberá indicar el nivel actual y la partida. En
caso que el usuario no exista o no tenga una partida en curso, se deberá
indicar el error*/
    public CurrentGame currentLevel(String userId) throws UserNotFoundException, UserNotActiveGameException;
/*Consulta de la puntuación actual. Dado un identificador de usuario se
indica la puntuación actual de la partida en curso. En caso que el usuario
no exista o no esté en una partida, se deberá indicar el error*/
    public int currentPuntuation(String userId) throws UserNotFoundException, UserNotActiveGameException;
/*Pasar de nivel. Se deberá indicar el identificador de usuario, los puntos
conseguidos con el paso de nivel y la fecha en la que se produce el
cambio de nivel. El resultado de la operación consiste en un cambio del
nivel que será el siguiente respecto al que estaba y se mantendrá el
acumulado de puntos de esa partida, de ese jugadorl. En caso que el
usuario esté en el último nivel, se incrementará la puntuación acumulada
en 100 puntos y la partida finalizará. En caso que el usuario no exista o
no esté en una partida en curso, se deberá indicar el error*/
    public void nextLevel(String userId,int points,String date) throws UserNotFoundException, UserNotActiveGameException;
/*Finalizar partida. Se indica que un determinado usuario ha finalizado la
partida actual. En caso que el usuario no exista o no esté en una partida
en curso de deberá indicar el error*/
    public void endGame(String userId) throws UserNotFoundException, UserNotActiveGameException;
/*Consulta de usuarios que han participado en un juego ordenado
por puntuación (descendente). Se indicará un juego y se
proporcionará la información. En caso que no exista el juego se deberá
indicar el error.*/
    public List<User> usersByPuntuation(String gameId) throws GameNotFoundException;
/*Consulta de las partidas en las que ha participado un usuario. En
caso que el usuario no exista se deberá indicar un error.*/
    public List<Game> gamesFromUser(String userId) throws UserNotFoundException;
    /*Consulta de la actividad de un usuario sobre un juego. Se
proporciona un listado de información asociada a la actividad del usuario
en el juego. Ejemplo: actividad(“juan”, “the game”): -> [ {level: 1, points:
5, date: dd-mm-aaaa}, {level:2, points:15, date: dd-mm-aaaa}, {level3:
points: 20, date: dd-mm-aaaa}]*/
    public List<Level> userActivityGame(String userId, String gameId) throws UserNotFoundException,GameNotFoundException;

    /*Se añaden clases que no se demandan, pero se sobreentiende*/
    public int size();

    public void addUser(String userId,String userName,String userSurname);


    public void addLevel(String levelName, int points, String levelDate);
    public int numUsers();
    public int numGames();
    public int numLevels();



}
