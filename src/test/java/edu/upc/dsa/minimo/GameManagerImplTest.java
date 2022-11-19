package edu.upc.dsa.minimo;

import edu.upc.dsa.minimo.domain.GameManager;
import edu.upc.dsa.minimo.domain.entity.exceptions.GameNotFoundException;
import edu.upc.dsa.minimo.domain.entity.exceptions.UserBussyException;
import edu.upc.dsa.minimo.domain.entity.exceptions.UserNotActiveException;
import edu.upc.dsa.minimo.domain.entity.exceptions.UserNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameManagerImplTest {
    GameManager gameManager;
    @Before
    public void setUp() {
        this.gameManager = new GameManagerImpl();
        this.gameManager.addUser("Fran");
        this.gameManager.addUser("Djalminha");
        this.gameManager.addUser("Valeron");
        this.gameManager.addUser("Mendieta");
        this.gameManager.addUser("Aranzubia");

        this.gameManager.addGame("G001", "Tetris", 4);
        this.gameManager.addGame("G002", "Ajedrez", 1);
        this.gameManager.addGame("G003", "Space Invaders", 6);
        this.gameManager.addGame("G004", "Monopoly", 3);
        this.gameManager.addGame("G005", "Cluedo", 10);

    }

    @After
    public void tearDown() {
        this.gameManager = null;
    }

    @Test
    public void test_UserNotFoundException(){
        Assert.assertThrows(UserNotFoundException.class, ()->this.gameManager.startGame("G001","Lucas Pérez"));
    }
    @Test
    public void test_GameNotFoundException(){
        Assert.assertThrows(GameNotFoundException.class, ()->this.gameManager.startGame("X001","Fran"));
    }

    @Test
    public void test_addGame(){
        Assert.assertEquals(5, this.gameManager.numGames());
        this.gameManager.addGame("G100", "Damas", 2);
        Assert.assertEquals(6, this.gameManager.numGames());
    }
    @Test
    public void test_startGame() throws UserNotFoundException, UserNotActiveException, GameNotFoundException, UserBussyException {
        this.gameManager.startGame("G001","Valeron");
        int i=this.gameManager.gamesByUser("Valeron").size()-1;
        Assert.assertEquals("G001",this.gameManager.gamesByUser("Valeron").get(i).getGameId());
    }
    @Test
    public void test_currentLevel() throws UserNotFoundException, UserNotActiveException, GameNotFoundException, UserBussyException {
        this.gameManager.startGame("G003","Aranzubia");
        Assert.assertEquals("G003",this.gameManager.currentLevel("Aranzubia").getGameId());
        Assert.assertEquals(1,this.gameManager.currentLevel("Aranzubia").getLevel());
    }
    @Test
    public void test_nextLevel() throws UserNotFoundException, UserNotActiveException, GameNotFoundException, UserBussyException {
        this.gameManager.startGame("G003","Aranzubia");
        this.gameManager.nextLevel("Aranzubia",20,"18/11/2022");
        Assert.assertEquals(2,this.gameManager.currentLevel("Aranzubia").getLevel());
        this.gameManager.nextLevel("Aranzubia",4,"24/11/2022");
        Assert.assertEquals(3,this.gameManager.currentLevel("Aranzubia").getLevel());
    }
    @Test
    public void test_currentPuntuation() throws UserNotFoundException, UserNotActiveException, GameNotFoundException, UserBussyException {
        this.gameManager.startGame("G003","Aranzubia");
        Assert.assertEquals(50,this.gameManager.currentPuntuation("Aranzubia"));
        this.gameManager.nextLevel("Aranzubia",20,"18/11/2022");
        Assert.assertEquals(70,this.gameManager.currentPuntuation("Aranzubia"));
    }
    @Test
    public void test_finishGame() throws UserNotFoundException, UserNotActiveException, GameNotFoundException, UserBussyException {
        this.gameManager.startGame("G003","Aranzubia");
        this.gameManager.finishGame("Aranzubia");
        Assert.assertThrows(UserNotActiveException.class, ()->this.gameManager.currentLevel("Aranzubia"));
    }
    //public List<User> usersByPuntuation (String gameId) throws UserNotFoundException, GameNotFountException{
    @Test
    public void test_usersByPuntuation() throws UserNotFoundException, GameNotFoundException, UserNotActiveException, UserBussyException {
        this.gameManager.startGame("G001","Fran");//50
        this.gameManager.startGame("G001","Valeron");//80
        this.gameManager.startGame("G001","Mendieta");//88
        this.gameManager.startGame("G001","Djalminha");//51
        this.gameManager.nextLevel("Valeron",30,"11/11/2022");
        this.gameManager.nextLevel("Mendieta",38,"18/11/2022");
        this.gameManager.nextLevel("Djalminha",51,"18/11/2022");
        Assert.assertEquals("Fran",this.gameManager.usersByPuntuation("G001").get(0).getUserId());
        Assert.assertEquals("Djalminha",this.gameManager.usersByPuntuation("G001").get(1).getUserId());
        Assert.assertEquals("Valeron",this.gameManager.usersByPuntuation("G001").get(2).getUserId());
        Assert.assertEquals("Mendieta",this.gameManager.usersByPuntuation("G001").get(3).getUserId());
    }
    @Test
    public void test_gamesByUser() throws UserNotFoundException, GameNotFoundException, UserNotActiveException, UserBussyException {
        this.gameManager.startGame("G003","Aranzubia");
        this.gameManager.finishGame("Aranzubia");
        this.gameManager.startGame("G001","Aranzubia");
        this.gameManager.finishGame("Aranzubia");
        this.gameManager.startGame("G002","Aranzubia");
        Assert.assertEquals("G001",this.gameManager.gamesByUser("Aranzubia").get(0).getGameId());
        Assert.assertEquals("G002",this.gameManager.gamesByUser("Aranzubia").get(1).getGameId());
        Assert.assertEquals("G003",this.gameManager.gamesByUser("Aranzubia").get(2).getGameId());

    }
    //    public List<UserActivityOverGame> userActivity(String userId, String gameId) throws UserNotFoundException{
    @Test
    public void test_userActivity() throws UserNotFoundException, UserNotActiveException, GameNotFoundException, UserBussyException {
        this.gameManager.startGame("G003","Aranzubia");
        this.gameManager.nextLevel("Aranzubia",30,"11/11/2022");
        this.gameManager.nextLevel("Aranzubia",10,"13/11/2022");
        Assert.assertEquals(1,this.gameManager.userActivity("Aranzubia","G003").get(0).getLevel());
        Assert.assertEquals(2,this.gameManager.userActivity("Aranzubia","G003").get(1).getLevel());

    }
}
