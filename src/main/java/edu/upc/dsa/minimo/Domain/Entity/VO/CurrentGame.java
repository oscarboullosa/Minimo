package edu.upc.dsa.minimo.Domain.Entity.VO;

public class CurrentGame {
    String gameId;
    int level;

    public CurrentGame(String gameId, int level) {
        this.gameId = gameId;
        this.level = level;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
