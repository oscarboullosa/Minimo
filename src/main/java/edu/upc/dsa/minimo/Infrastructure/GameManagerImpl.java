package edu.upc.dsa.minimo.Infrastructure;

import edu.upc.dsa.minimo.Domain.Entity.Game;
import edu.upc.dsa.minimo.Domain.Entity.Level;
import edu.upc.dsa.minimo.Domain.Entity.TO.LevelInfo;
import edu.upc.dsa.minimo.Domain.Entity.User;
import edu.upc.dsa.minimo.Domain.Entity.VO.CurrentGame;
import edu.upc.dsa.minimo.Domain.GameManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    protected List<Game> games;
    protected List<Level> levels;
    protected Map<String, User> users;
    final static Logger logger= Logger.getLogger(GameManagerImpl.class);
    public GameManagerImpl(){
        this.games=new ArrayList<>();
        this.levels=new ArrayList<>();
        this.users=new HashMap<>();
    }
    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }
    @Override
    public int size() {
        int ret = this.users.size();
        logger.info("size " + ret);

        return ret;
    }
    /*
    --------------------------------------------------------------------*/
    public User getUser(String userId) {
        return this.users.get(userId);
    }
    public Game getGame(String gameId){
        Game game = this.games.stream()
                .filter(x->gameId.equals(x.getGameId()))
                .findFirst()
                .orElse(null);
        return game;
    }

    /*--------------------------------------------------------------------
    */
/*Creación de un juego. Para la creación de un juego se debe indicar un
identificador, una descripción y un número de niveles (nivel1, nivel2, …
nivel3).*/
    @Override
    public void addGame(String gameId,String description,int levelNum){
        this.games.add(new Game(gameId,description,levelNum));
    }
/*Inicio de una partida de un juego por parte de un usuario. Se debe
indicar el identificador del juego y el identificador del usuario. El
resultado de la operación es que el usuario entra en el primer nivel con
50 puntos iniciales. En caso que el usuario o juego no existan, se deberá
indicar el error. Un jugador SÓLO puede estar en una partida al mismo
tiempo. En caso que el jugador ya tenga una partida activa, se deberá
indicar el error.*/

/*Consulta del nivel actual. Dado un identificador de un usuario que está
en una partida en curso, se deberá indicar el nivel actual y la partida. En
caso que el usuario no exista o no tenga una partida en curso, se deberá
indicar el error*/

    @Override
    public CurrentGame currentLevel(String userId){
        return new CurrentGame(users.get(userId).getCurrentGame().getGameId(),users.get(userId).getLevel());
    }

/*Consulta de la puntuación actual. Dado un identificador de usuario se
indica la puntuación actual de la partida en curso. En caso que el usuario
no exista o no esté en una partida, se deberá indicar el error*/
public int currentPuntuation(String userId){
    return this.users.get(userId).getPuntuation();//La puntuacion que tiene un usario en una partida es directamente la que tiene, ya que despues de cada partida se reestablece
}
/*Pasar de nivel. Se deberá indicar el identificador de usuario, los puntos
conseguidos con el paso de nivel y la fecha en la que se produce el
cambio de nivel. El resultado de la operación consiste en un cambio del
nivel que será el siguiente respecto al que estaba y se mantendrá el
acumulado de puntos de esa partida, de ese jugadorl. En caso que el
usuario esté en el último nivel, se incrementará la puntuación acumulada
en 100 puntos y la partida finalizará. En caso que el usuario no exista o
no esté en una partida en curso, se deberá indicar el error*/
public void nextLevel(String userId,int points,String date){
    User myUser=this.users.get(userId);
    CurrentGame myCurrentGame=currentLevel(userId);
    Game myGame=getGame(myCurrentGame.getGameId());
            if(myGame.getGameLevels().size()>myCurrentGame.getLevel()){//Si no estamos en el último nivel...
                myUser.pasteDateToLevel();//Pone la fecha del día en el que el usuario pasa de nivel
                myUser.setNextLevel();
            }
            else if(myGame.getGameLevels().size()>myCurrentGame.getLevel()){//Si estamos en el último nivel
                myUser.setPuntuation(myUser.getPuntuation()+100);
                myUser.finishGame();
            }
        }


/*Finalizar partida. Se indica que un determinado usuario ha finalizado la
partida actual. En caso que el usuario no exista o no esté en una partida
en curso de deberá indicar el error*/
public void endGame(String userId){
    this.users.get(userId).finishGame();

}
/*Consulta de usuarios que han participado en un juego ordenado
por puntuación (descendente). Se indicará un juego y se
proporcionará la información. En caso que no exista el juego se deberá
indicar el error.*/


/*Consulta de las partidas en las que ha participado un usuario. En
caso que el usuario no exista se deberá indicar un error.*/
/*Consulta de la actividad de un usuario sobre un juego. Se
proporciona un listado de información asociada a la actividad del usuario
en el juego. Ejemplo: actividad(“juan”, “the game”): -> [ {level: 1, points:
5, date: dd-mm-aaaa}, {level:2, points:15, date: dd-mm-aaaa}, {level3:
points: 20, date: dd-mm-aaaa}]*/
public List<Level> userActivityGame(String userId,String gameId){
    int indice=0;
    for(int i=0;i<getUser(userId).getUserGames().size();i++){
        if(getUser(userId).getUserGames().get(i).equals(gameId)){
            indice=i;
        }
    }
    return getUser(userId).getUserGames().get(indice).getGameLevels();
}

}
