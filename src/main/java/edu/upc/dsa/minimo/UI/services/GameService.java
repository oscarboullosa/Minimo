package edu.upc.dsa.minimo.UI.services;

import edu.upc.dsa.minimo.Domain.Entity.Exceptions.GameNotExists;
import edu.upc.dsa.minimo.Domain.Entity.Exceptions.UserNotExists;
import edu.upc.dsa.minimo.Domain.Entity.Game;
import edu.upc.dsa.minimo.Domain.Entity.Level;
import edu.upc.dsa.minimo.Domain.Entity.TO.GameInfo;
import edu.upc.dsa.minimo.Domain.Entity.User;
import edu.upc.dsa.minimo.Domain.Repository.Manager;
import edu.upc.dsa.minimo.Infrastructure.ManagerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value="/game",description="Endpoint to Game Service")
@Path("/game")

public class GameService {
    private Manager manager;
    public GameService(){
        this.manager= ManagerImpl.getInstance();
        if(manager.size()==0){
            this.manager.addLevel("B001","Level1",10);
            this.manager.addLevel("C001","Level2",20);
            this.manager.addLevel("D001","Level3",30);
            this.manager.addLevel("E001","Level4",40);
        }
    }
    @POST
    @ApiOperation(value = "add a new user", notes = "new User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {

        if (user.getUserId()==null || user.getUserName()==null)  return Response.status(500).entity(user).build();
        this.manager.addUser(user.getUserId(), user.getUserName(), user.getUserSurname());
        return Response.status(201).entity(user).build();
    }
    @GET
    @ApiOperation(value = "get games from User", notes = "from User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Level.class, responseContainer="List"),
            @ApiResponse(code =400,message="User not found")
    })
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserGames(@PathParam("userId") String userId) {
        try {
            List<Game> games = this.manager.gameByUser(userId);
            GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {
            };
            return Response.status(201).entity(entity).build();
        } catch (UserNotExists e) {
            return Response.status(400).build();
        }
    }
    @POST
    @ApiOperation(value = "add a new game", notes = "Add Game")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= GameInfo.class)
    })
    @Path("/game")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addGame(GameInfo game) {
        this.manager.addGame(game.getGameId(), game.getDescription(), game.getNumLevels());
        return Response.status(201).entity(game).build();
    }
    @PUT
    @ApiOperation(value = "perform an level insertion into a game", notes = "Insertion")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 400, message = "Game do not exist")
    })
    @Path("/{gameId}/{levelId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response levelGame(@PathParam("gameId") String gameId, @PathParam("levelId") String levelId) {
        try {
            this.manager.levelGame(gameId, levelId);
        } catch (GameNotExists e) {
            return Response.status(400).build();
        }
        return Response.status(201).build();
    }
