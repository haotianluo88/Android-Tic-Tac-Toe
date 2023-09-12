package com.example.scroll_tute5prep;
/*
* Self Explanatory GameStat Object
* By Janet Joy
*/
public class GameStat {
    int totalGames;
    int wins;
    int loses;
    int draws;
    double winRate;

    public GameStat()
    {
        totalGames = 0;
        wins = 0;
        loses = 0;
        draws = 0;
        winRate = 0;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }
    public void setLoses(int loses) {
        this.loses = loses;
    }
    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }
    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getTotalGames(){return totalGames;}
    public int getWins(){return wins;}
    public int getLoses(){return loses;}
    public int getDraws(){return draws;}
    public double getWinRate(){return winRate;}
}
