package edu.upc.dsa.minimo.Domain.Repository;

import edu.upc.dsa.minimo.Domain.Entity.Exceptions.GameAlreadyActive;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.GameNotExists;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserNotExists;
import edu.upc.dsa.minimo.Domain.Entity.Game;
import edu.upc.dsa.minimo.Domain.Entity.Level;
import edu.upc.dsa.minimo.Domain.Entity.User;

import java.util.List;

public interface Manager {
    public void addUserToGame(String gameId,String userId) throws GameNotExists, UserNotExists, GameAlreadyActive;
    public String gameByUser(String userId) throws UserNotExists;
    public int numPoints(String userId);
    public void addUser(String s,String name, String surname);
    public void addLevel(String levelId, String name, int point);
    public Level getLevel(String levelId);
    public Level processLevel();
    public void endGame(String userId);
    public int size();

    public void addGame(String gameId, String description, int numLevels);
    public List<User> userByPoints(String gameId) throws GameNotExists;

    public void levelGame(String gameId, String levelId);
}
