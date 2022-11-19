package edu.upc.dsa.minimo.domain;

import edu.upc.dsa.minimo.domain.entity.Game;
import edu.upc.dsa.minimo.domain.entity.User;
import edu.upc.dsa.minimo.domain.entity.exceptions.GameNotFoundException;
import edu.upc.dsa.minimo.domain.entity.exceptions.UserBussyException;
import edu.upc.dsa.minimo.domain.entity.exceptions.UserNotActiveException;
import edu.upc.dsa.minimo.domain.entity.exceptions.UserNotFoundException;
import edu.upc.dsa.minimo.domain.entity.vo.CurrentGame;
import edu.upc.dsa.minimo.domain.entity.vo.UserActivityOverGame;

import java.util.List;


public interface GameManager {
    //Añado Juego y Usuario
    public void addGame(String gameId, String gameDescription, int numLevels);
    public void addUser(String userId);
    //Número de Juegos y Usuarios
    public int size();
    public int numUsers();
    public int numGames();

    /*Inicio de una partida de un juego por parte de un usuario. Se debe
    indicar el identificador del juego y el identificador del usuario. El
    resultado de la operación es que el usuario entra en el primer nivel con
    50 puntos iniciales. En caso que el usuario o juego no existan, se deberá
    indicar el error. Un jugador SÓLO puede estar en una partida al mismo
    tiempo. En caso que el jugador ya tenga una partida activa, se deberá
    indicar el error.*/
    public void startGame(String gameId, String userId) throws GameNotFoundException, UserNotFoundException, UserBussyException, UserNotActiveException;

    /*Consulta del nivel actual. Dado un identificador de un usuario que está
    en una partida en curso, se deberá indicar el nivel actual y la partida. En
    caso que el usuario no exista o no tenga una partida en curso, se deberá
    indicar el error*/
    public CurrentGame currentLevel(String userId) throws UserNotFoundException, UserNotActiveException;

    /*Consulta de la puntuación actual. Dado un identificador de usuario se
    indica la puntuación actual de la partida en curso. En caso que el usuario
    no exista o no esté en una partida, se deberá indicar el error*/
    public int currentPuntuation(String userId) throws UserNotFoundException, UserNotActiveException;

    /*Pasar de nivel. Se deberá indicar el identificador de usuario, los puntos
    conseguidos con el paso de nivel y la fecha en la que se produce el
    cambio de nivel. El resultado de la operación consiste en un cambio del
    nivel que será el siguiente respecto al que estaba y se mantendrá el
    acumulado de puntos de esa partida, de ese jugadorl. En caso que el
    usuario esté en el último nivel, se incrementará la puntuación acumulada
    en 100 puntos y la partida finalizará. En caso que el usuario no exista o
    no esté en una partida en curso, se deberá indicar el error*/
    public void nextLevel(String userId, int points,String date) throws UserNotFoundException, UserNotActiveException,GameNotFoundException;
    /*Finalizar partida. Se indica que un determinado usuario ha finalizado la
partida actual. En caso que el usuario no exista o no esté en una partida
en curso de deberá indicar el error*/
    public void finishGame(String userId) throws UserNotFoundException;
    /*Consulta de usuarios que han participado en un juego ordenado
    por puntuación (descendente). Se indicará un juego y se
    proporcionará la información. En caso que no exista el juego se deberá
    indicar el error.*/
    public List<User> usersByPuntuation (String gameId) throws UserNotFoundException, GameNotFoundException;
    /*Consulta de las partidas en las que ha participado un usuario. En
    caso que el usuario no exista se deberá indicar un error.*/
    public List<Game> gamesByUser(String userId) throws UserNotFoundException, GameNotFoundException;
    /*Consulta de la actividad de un usuario sobre un juego. Se
    proporciona un listado de información asociada a la actividad del usuario
    en el juego. Ejemplo: actividad(“juan”, “the game”): -> [ {level: 1, points:
    5, date: dd-mm-aaaa}, {level:2, points:15, date: dd-mm-aaaa}, {level3:
    points: 20, date: dd-mm-aaaa}]*/
    public List<UserActivityOverGame> userActivity(String userId,String gameId) throws UserNotFoundException;

}
