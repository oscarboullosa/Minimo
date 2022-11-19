package edu.upc.dsa.minimo;

import edu.upc.dsa.minimo.domain.GameManager;
import edu.upc.dsa.minimo.domain.entity.Game;
import edu.upc.dsa.minimo.domain.entity.User;
import edu.upc.dsa.minimo.domain.entity.exceptions.GameNotFoundException;
import edu.upc.dsa.minimo.domain.entity.exceptions.UserBussyException;
import edu.upc.dsa.minimo.domain.entity.exceptions.UserNotActiveException;
import edu.upc.dsa.minimo.domain.entity.exceptions.UserNotFoundException;
import edu.upc.dsa.minimo.domain.entity.vo.CurrentGame;
import edu.upc.dsa.minimo.domain.entity.vo.UserActivityOverGame;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManagerImpl implements GameManager{
    private static GameManager instance;

    protected List<Game> games;

    protected Map<String, User> users;
    protected List<User> usersList;

    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    public GameManagerImpl(){
        this.games = new ArrayList<>();
        this.users = new HashMap<>();
        this.usersList=new ArrayList<>();
    }

    public static GameManager getInstance(){
        if(instance==null) instance=new GameManagerImpl();
        return instance;
    }
    public Game getGame(String gameId) throws GameNotFoundException {
        Game game = this.games.stream()
                .filter(x->gameId.equals(x.getGameId()))
                .findFirst()
                .orElse(null);
        if (game == null){
            throw new GameNotFoundException();
        }
        return game;
    }
    @Override
    public int size() {
        int ret = this.users.size();
        logger.info("size " + ret);
        return ret;
    }
    @Override
    public void addGame(String gameId, String gameDescription, int numLevels) {
        this.games.add(new Game(gameId, gameDescription, numLevels));
    }
    @Override
    public void addUser(String userId) {
        this.users.put(userId, new User(userId));
        this.usersList.add(new User(userId));
    }
    @Override
    public void startGame(String gameId, String userId) throws GameNotFoundException, UserNotFoundException, UserBussyException, UserNotActiveException {
        logger.info("Trying to add user with ID= "+userId+" to game with ID= "+gameId);
        if(!this.users.containsKey(userId)){
            logger.warn("User with id: "+userId+" does not exist");
            throw new UserNotFoundException();
        }
        if((getGame(gameId))==null){
            logger.warn("Game with id: "+gameId+" does not exist");
            throw new GameNotFoundException();
        }
        this.users.get(userId).startGame(getGame(gameId));
        getGame(gameId).gamesPlayedByUser(users.get(userId));
        logger.info("The game will start!");
    }
    @Override
    public CurrentGame currentLevel(String userId) throws UserNotFoundException, UserNotActiveException{
        logger.info("Looking for user with ID= "+userId);
        if(!this.users.containsKey(userId)){
            logger.warn("User with id: "+userId+" does not exist");
            throw new UserNotFoundException();
        }
        if(this.users.get(userId).getCurrentGame()==null){
            logger.warn("User with id: "+userId+" has not a game started");
            throw new UserNotActiveException();
        }
        return new CurrentGame(users.get(userId).getCurrentGame().getGameId(),users.get(userId).getUserLevel());
    }
    @Override
    public int currentPuntuation(String userId) throws UserNotFoundException, UserNotActiveException{
        logger.info("Looking for user with ID= "+userId);
        if(!this.users.containsKey(userId)){
            logger.warn("User with id: "+userId+" does not exist");
            throw new UserNotFoundException();
        }
        if(this.users.get(userId).getCurrentGame()==null){
            logger.warn("User with id: "+userId+" has not a game started");
            throw new UserNotActiveException();
        }
        return users.get(userId).getUserPoints();
    }
    @Override
    public void nextLevel(String userId, int points,String date) throws UserNotFoundException, UserNotActiveException, GameNotFoundException {
        logger.info("Looking for user with ID= "+userId);
        if(!this.users.containsKey(userId)){
            logger.warn("User with id: "+userId+" does not exist");
            throw new UserNotFoundException();
        }
        if(this.users.get(userId).getCurrentGame()==null){
            logger.warn("User with id: "+userId+" has not a game started");
            throw new UserNotActiveException();
        }
        this.users.get(userId).nextLevel(points, date);
        //this.users.get(userId).getGameInside().get(this.users.get(userId).getCurrentGame().getGameId()).get(size()-1).setLevel(size());
        getGame(this.users.get(userId).getCurrentGame().getGameId()).gamesPlayedByUser(this.users.get(userId));
    }
    @Override
    public void finishGame(String userId) throws UserNotFoundException{
        logger.info("Looking for user with ID= "+userId);
        if(!this.users.containsKey(userId)){
            logger.warn("User with id: "+userId+" does not exist");
            throw new UserNotFoundException();
        }
        users.get(userId).finishGame();
    }
    @Override
    public List<User> usersByPuntuation (String gameId) throws UserNotFoundException, GameNotFoundException {
        if((getGame(gameId))==null){
            logger.warn("Game with id: "+gameId+" does not exist");
            throw new GameNotFoundException();
        }
        getGame(gameId).orderByPoints();
        List<User> users=new ArrayList<>();
        for(int i=0;i<this.usersList.size();i++){
            for(int j=0;j<this.games.size();j++){
                if(this.games.get(j).getGameId().equals(gameId)){
                    users.add(usersList.get(i));
                }
            }

        }
        return users;
    }
    @Override
    public List<Game> gamesByUser(String userId) throws UserNotFoundException, GameNotFoundException {

        logger.info("Looking for user with ID= "+userId);
        if(!this.users.containsKey(userId)){
            logger.warn("User with id: "+userId+" does not exist");
            throw new UserNotFoundException();
        }
        List<Game> games=new ArrayList<>();
        for(int i=0;i<this.games.size();i++){
            for(int j=0;j<this.users.get(userId).getUserGames().size();j++){
                if(this.games.get(i).getGameId().equals(this.users.get(userId).getUserGames().get(j).getGameId())) {
                    games.add(getGame(this.games.get(i).getGameId()));
                }
            }
        }
        return games;
    }
    @Override
    public List<UserActivityOverGame> userActivity(String userId, String gameId) throws UserNotFoundException{
        logger.info("Looking for user with ID= "+userId);
        if(!this.users.containsKey(userId)){
            logger.warn("User with id: "+userId+" does not exist");
            throw new UserNotFoundException();
        }
        return this.users.get(userId).getGameInside().get(gameId);
    }


    public int numUsers(){
        return this.users.size();
    }
    public int numGames(){
        return this.games.size();
    }


}
