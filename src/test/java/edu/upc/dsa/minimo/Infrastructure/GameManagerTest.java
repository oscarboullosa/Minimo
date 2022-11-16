package edu.upc.dsa.minimo.Infrastructure;

import edu.upc.dsa.minimo.Domain.Entity.Exceptions.GameNotFoundException;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserBussyException;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserNotFoundException;
import edu.upc.dsa.minimo.Domain.Entity.Game;
import edu.upc.dsa.minimo.Domain.GameManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameManagerTest {
    GameManager gameManager;

    @Before
    public void setUp(){
        this.gameManager=new GameManagerImpl();

        this.gameManager.addGame("A001","El juego va de rescatar a otro personaje",3);
        this.gameManager.addGame("A002","El juego va de atrapar a otro personaje",5);
        this.gameManager.addGame("A003","El juego va de perseguir a otro personaje",2);
        this.gameManager.addGame("A004","El juego va de ordenar piezas",4);
        this.gameManager.addGame("A005","El juego va de darle la vuelta a piezas",4);

        this.gameManager.addUser("U001","Óscar","Boullosa Dapena");
        this.gameManager.addUser("U002","Itziar","Mensa Minguito");
        this.gameManager.addUser("U003","Diego","Armando Maradona");
        this.gameManager.addUser("U004","Francisco Javier", "Gonzalez Perez");
        this.gameManager.addUser("U005","Djalma","Feitosa Dias");

        this.gameManager.addLevel("Inicio",55,"16/09/2022");
        this.gameManager.addLevel("Jungla",60,"16/10/2022");
        this.gameManager.addLevel("Desierto",65,"18/10/2022");
        this.gameManager.addLevel("Cárcel",70,"18/11/2022");
        this.gameManager.addLevel("Fin",75,"20/11/2022");

    }
    @After
    public void tearDown(){
        this.gameManager=null;
    }

    @Test
    public void test_addGameLevelUser(){
        Assert.assertEquals(5,this.gameManager.numGames());
        Assert.assertEquals(5,this.gameManager.numUsers());
        Assert.assertEquals(5,this.gameManager.numLevels());
        this.gameManager.addGame("B001","El juego va de decubrir un misterio",8);
        this.gameManager.addUser("U006","Donato","Gama da Silva");
        this.gameManager.addLevel("Bosque",62,"20/11/2021");
        Assert.assertEquals(6,this.gameManager.numGames());
        Assert.assertEquals(6,this.gameManager.numUsers());
        Assert.assertEquals(6,this.gameManager.numLevels());

    }

    @Test
    public void test_addUserToGame() throws UserNotFoundException,UserBussyException,GameNotFoundException{
        Assert.assertEquals(5,this.gameManager.numGames());
        Assert.assertEquals(5,this.gameManager.numUsers());
        Assert.assertEquals(5,this.gameManager.numLevels());
        this.gameManager.addUserToGame("A001","U002");
        Assert.assertEquals("A001",this.gameManager.gamesFromUser("U002").get(0).getGameId());
    }

}
