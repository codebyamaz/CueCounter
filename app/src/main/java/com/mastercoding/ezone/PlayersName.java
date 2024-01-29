package com.mastercoding.ezone;

public class PlayersName {
    private String playerName;
    private int score;

    public PlayersName(String playerName) {
        this.playerName = playerName;
        this.score = 0;
    }

    public PlayersName() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int delta) {
        score += delta;
    }
}

