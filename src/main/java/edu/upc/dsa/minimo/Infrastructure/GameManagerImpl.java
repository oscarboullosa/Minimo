package edu.upc.dsa.minimo.Infrastructure;

import edu.upc.dsa.minimo.Domain.Entity.Exceptions.GameNotFoundException;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserBussyException;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserNotActiveGameException;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserNotFoundException;
import edu.upc.dsa.minimo.Domain.Entity.Game;
import edu.upc.dsa.minimo.Domain.Entity.Level;
import edu.upc.dsa.minimo.Domain.Entity.User;
import edu.upc.dsa.minimo.Domain.Entity.VO.CurrentGame;
import edu.upc.dsa.minimo.Domain.GameManager;
import org.apache.log4j.Logger;

import java.util.*;

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
    public User getUser(String userId){
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
    @Override
    public void addUser(String userId, String userName, String userSurname){
        this.users.put(userId,new User(userId,userName,userSurname));
    }
    @Override
    public void addLevel(String levelName,int points,String levelDate){
        Level level=new Level(levelName,points,levelDate);
        this.levels.add(level);
    }
/*Inicio de una partida de un juego por parte de un usuario. Se debe
indicar el identificador del juego y el identificador del usuario. El
resultado de la operación es que el usuario entra en el primer nivel con
50 puntos iniciales. En caso que el usuario o juego no existan, se deberá
indicar el error. Un jugador SÓLO puede estar en una partida al mismo
tiempo. En caso que el jugador ya tenga una partida activa, se deberá
indicar el error.*/
public void addUserToGame(String gameId,String userId) throws GameNotFoundException, UserNotFoundException, UserBussyException {
    logger.info("Trying to add user with ID= "+userId+" to game with ID= "+gameId);
    if(!this.users.containsKey(userId)){
        logger.warn("User with id: "+userId+" does not exist");
        throw new UserNotFoundException();
    }
    Game game=getGame(gameId);
    if((game=getGame(gameId))==null){
        logger.warn("Game with id: "+gameId+" does not exist");
        throw new GameNotFoundException();
    }
    User user=getUser(userId);
    user.setUserGames(game);
    logger.info("The game will start!");
}

/*Consulta del nivel actual. Dado un identificador de un usuario que está
en una partida en curso, se deberá indicar el nivel actual y la partida. En
caso que el usuario no exista o no tenga una partida en curso, se deberá
indicar el error*/

    @Override
    public CurrentGame currentLevel(String userId) throws UserNotFoundException, UserNotActiveGameException{
        logger.info("Looking for user with ID= "+userId);
        if(!this.users.containsKey(userId)){
            logger.warn("User with id: "+userId+" does not exist");
            throw new UserNotFoundException();
        }
        if(this.users.get(userId).getCurrentGame()==null){
            logger.warn("User with id: "+userId+" has not a game started");
            throw new UserNotActiveGameException();
        }
        return new CurrentGame(users.get(userId).getCurrentGame().getGameId(),users.get(userId).getLevel());
    }

/*Consulta de la puntuación actual. Dado un identificador de usuario se
indica la puntuación actual de la partida en curso. En caso que el usuario
no exista o no esté en una partida, se deberá indicar el error*/
public int currentPuntuation(String userId) throws UserNotFoundException, UserNotActiveGameException{
    logger.info("Looking for user with ID= "+userId);
    if(!this.users.containsKey(userId)){
        logger.warn("User with id: "+userId+" does not exist");
        throw new UserNotFoundException();
    }
    if(this.users.get(userId).getCurrentGame()==null){
        logger.warn("User with id: "+userId+" has not a game started");
        throw new UserNotActiveGameException();
    }
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
public void nextLevel(String userId,int points,String date)throws UserNotFoundException, UserNotActiveGameException{
    logger.info("Looking for user with ID= "+userId);
    if(!this.users.containsKey(userId)){
        logger.warn("User with id: "+userId+" does not exist");
        throw new UserNotFoundException();
    }
    if(this.users.get(userId).getCurrentGame()==null){
        logger.warn("User with id: "+userId+" has not a game started");
        throw new UserNotActiveGameException();
    }
    User myUser=this.users.get(userId);
    CurrentGame myCurrentGame=currentLevel(userId);
    Game myGame=getGame(myCurrentGame.getGameId());
            if(myGame.getGameLevels().size()<myCurrentGame.getLevel()){//Si no estamos en el último nivel...
                myUser.pasteDateToLevel();//Pone la fecha del día en el que el usuario pasa de nivel
                myUser.setNextLevel();
            }
            else if(myGame.getGameLevels().size()==myCurrentGame.getLevel()){//Si estamos en el último nivel
                myUser.setPuntuation(myUser.getPuntuation()+100);
                myUser.finishGame();
            }
        }


/*Finalizar partida. Se indica que un determinado usuario ha finalizado la
partida actual. En caso que el usuario no exista o no esté en una partida
en curso de deberá indicar el error*/
public void endGame(String userId) throws UserNotFoundException, UserNotActiveGameException{
    logger.info("Looking for user with ID= "+userId);
    if(!this.users.containsKey(userId)){
        logger.warn("User with id: "+userId+" does not exist");
        throw new UserNotFoundException();
    }
    if(this.users.get(userId).getCurrentGame()==null){
        logger.warn("User with id: "+userId+" has not a game started");
        throw new UserNotActiveGameException();
    }
    this.users.get(userId).finishGame();

}
/*Consulta de usuarios que han participado en un juego ordenado
por puntuación (descendente). Se indicará un juego y se
proporcionará la información. En caso que no exista el juego se deberá
indicar el error.*/
public LinkedList<User> usersByPuntuation(String gameId)throws GameNotFoundException{
    Game game=getGame(gameId);//Me situo en el juego solicitado
    if((game=getGame(gameId))==null){
        logger.warn("Game with id: "+gameId+" does not exist");
        throw new GameNotFoundException();
    }
    game.getGameUsers().sort((User u1,User u2)->Integer.compare(u1.getPuntuation(), u2.getPuntuation()));

    return game.getGameUsers();
}

/*Consulta de las partidas en las que ha participado un usuario. En
caso que el usuario no exista se deberá indicar un error.*/
public List<Game> gamesFromUser(String userId)throws UserNotFoundException{
    logger.info("Looking for user with ID= "+userId);
    if(!this.users.containsKey(userId)){
        logger.warn("User with id: "+userId+" does not exist");
        throw new UserNotFoundException();
    }
    List<Game> games0=new ArrayList<>();
    for(int i=0;i<games.size();i++){
        if(this.games.get(i).getGameId().equals(getUser(userId).getUserGames().get(i).getGameId())){
            games0.add(getGame(this.games.get(i).getGameId()));
        }
    }
    return games0;
}
/*Consulta de la actividad de un usuario sobre un juego. Se
proporciona un listado de información asociada a la actividad del usuario
en el juego. Ejemplo: actividad(“juan”, “the game”): -> [ {level: 1, points:
5, date: dd-mm-aaaa}, {level:2, points:15, date: dd-mm-aaaa}, {level3:
points: 20, date: dd-mm-aaaa}]*/
public List<Level> userActivityGame(String userId,String gameId) throws UserNotFoundException,GameNotFoundException{
    logger.info("Looking for user with ID= "+userId);
    if(!this.users.containsKey(userId)){
        logger.warn("User with id: "+userId+" does not exist");
        throw new UserNotFoundException();
    }
    if((getGame(gameId))==null){
        logger.warn("Game with id: "+gameId+" does not exist");
        throw new GameNotFoundException();
    }
    int indice=0;
    for(int i=0;i<getUser(userId).getUserGames().size();i++){
        if(getUser(userId).getUserGames().get(i).getGameId().equals(getGame(gameId).getGameId())){
            indice=i;
        }
    }
    return getUser(userId).getUserGames().get(indice).getGameLevels();
}
    @Override
    public int numUsers(){
    return users.size();
    }
    @Override
    public int numGames(){
    return games.size();
    }
    @Override
    public int numLevels(){
    return levels.size();
}
}
