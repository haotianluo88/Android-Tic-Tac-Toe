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
        winRate = 0.0;
    }
//    TEST
    public void setWins(int wins) {
    this.wins = wins;
    }
    public void setLoses(int loses) {
        this.loses = loses;
    }
    public void setDraws(int draws) {
        this.draws = draws;
    }
    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }
    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public void updateWins(int wins) {
        this.wins = this.wins + wins;
        updateTotalGames();
        updateWinRate();
    }
    public void updateLoses(int loses) {
        this.loses = this.loses + loses;
        updateTotalGames();
        updateWinRate();
    }
    public void updateDraws(int draws) {
        this.draws = this.draws + draws;
        updateTotalGames();
        updateWinRate();
    }
    public void updateTotalGames(){
        this.totalGames = wins + draws + loses;
    }
    public void updateWinRate(){
        this.winRate = (wins/totalGames) * 100;
    }



    public int getTotalGames(){return totalGames;}
    public int getWins(){return wins;}
    public int getLoses(){return loses;}
    public int getDraws(){return draws;}
    public double getWinRate(){return winRate;}
}
