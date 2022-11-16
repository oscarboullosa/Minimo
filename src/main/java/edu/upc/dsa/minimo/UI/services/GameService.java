package edu.upc.dsa.minimo.UI.services;

import edu.upc.dsa.minimo.Domain.GameManager;
import edu.upc.dsa.minimo.Infrastructure.GameManagerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Api(value = "/game",description ="Endpoint to Game service")
@Path("/game")
public class GameService {
    private GameManager gameManager;
    public GameService(){
        this.gameManager= GameManagerImpl.getInstance();
        if(gameManager.size()==0){
            this.gameManager.addUser("Óscar","Boullosa Dapena");
            this.gameManager.addUser("Diego","Armando Maradona");
            this.gameManager.addUser("Diego","Tristán Herrera");
            this.gameManager.addUser("Djalma","Feitosa Dias");
            this.gameManager.addUser("Francisco Javier","González Pérez");

            this.gameManager.addLevel("Nivel 1: Inicio",60,"16/11/2022");
            this.gameManager.addLevel("Nivel 2: Jungla",70,"16/11/2022");
            this.gameManager.addLevel("Nivel 3: Desierto",80,"16/11/2022");
            this.gameManager.addLevel("Nivel 4: Final",90,"16/11/2022");

            this.gameManager.addGame("A001","Tetris",3);
            this.gameManager.addGame("A002","Buscaminas",4);
            this.gameManager.addGame("A003","Mario Bros",2);
        }
    }
    @POST
    @ApiOperation(value="Add a new user",notes="Add user")
    @ApiResponse()
}
