package edu.upc.dsa.minimo.Infrastructure;

import edu.upc.dsa.minimo.Domain.Entity.Exceptions.GameAlreadyActive;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.GameNotExists;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserNotExists;
import edu.upc.dsa.minimo.Domain.Entity.Game;
import edu.upc.dsa.minimo.Domain.Entity.Level;
import edu.upc.dsa.minimo.Domain.Entity.User;
import edu.upc.dsa.minimo.Domain.Repository.Manager;
import org.apache.log4j.Logger;

import java.util.*;

public class ManagerImpl implements Manager {
    private static Manager instance;
    Map<String, User> users;
    Queue<Level> levels;
    List<Game> games;
    final static Logger logger = Logger.getLogger(ManagerImpl.class);

    public static Manager getInstance() {
        if (instance == null) instance = new ManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.levels.size();
        logger.info("size " + ret);

        return ret;
    }

    public ManagerImpl() {
        this.levels = new LinkedList<>();
        this.users = new HashMap<>();
        this.games = new ArrayList<>();

    }
    @Override
    public void addGame(Game game) {
        this.games.add(game);
    }

    @Override
    public void addUserToGame(String gameId,String userId) throws GameNotExists, UserNotExists, GameAlreadyActive{
        if(!this.users.containsKey(userId)){
            logger.warn("User with id: "+userId+" does not exist");
            throw new UserNotExists();}
        Game game;
        if((game=getGame(gameId))==null){
            throw new GameNotExists();
        }
        if(games.size()>1){
            throw new GameAlreadyActive();
        }
        this.users.get(userId).addGamePlayed(getGame(gameId));
        this.users.get(userId).setPoints(50);
    }
    @Override
    public int numPoints(String userId) throws UserNotExists{
        if(!this.users.containsKey(userId)){
            throw new UserNotExists();}
        return this.users.get(userId).getPoints();
    }
    @Override
    public void endGame(String userId) throws UserNotExists{
        if(!this.users.containsKey(userId)){
            throw new UserNotExists();}

    }
    public Game getGame(String gameId) {
        return this.games.stream()
                .filter(x->gameId.equals(x.getGameId()))
                .findFirst()
                .orElse(null);
    }
    @Override
    public List<User> userByPoints(String gameId) throws GameNotExists{
        logger.info("Request of users from a game: "+gameId+".");
        Game game = this.games.get(gameId);
        if (game == null){
            logger.warn("User with id: "+gameId+" does not exist");
            throw new GameNotExists();
        }
        logger.info("Request was correctly effectuated");
        this.users.sort((User u1,User u2)-> Double.compare(u2.getPoints(),u1.getPoints()));
    }


}
