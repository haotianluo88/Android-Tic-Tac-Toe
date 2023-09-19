package com.example.mad_tictactoe.game;

import android.widget.Chronometer;

public class Game {
    private int[] gridArray;
    private int movesCount;
    private int movesLeft;
    long timer;
    LinkedList list;
    int whosTurn;
    int maxMoveCount;
    int winner;

    public Game(int[] inGridArray, int inMovesLeft, int inMovesCount, LinkedList inList, int inWhosTurn, int inMaxMoveCount, int inWinner, long inTimer){
        gridArray = inGridArray;
        movesCount = inMovesCount;
        movesLeft = inMovesLeft;
        list = inList;
        whosTurn = inWhosTurn;
        maxMoveCount = inMaxMoveCount;
        winner = inWinner;
        timer = inTimer;
    }

    public int[] getGridArray() {
        return gridArray;
    }

    public void setGridArray(int[] array) {
        this.gridArray = array;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    public LinkedList getList() {
        return list; }

    public void setList(LinkedList list) {
        this.list = list;}

    public int getWhosTurn() {
        return whosTurn;
    }

    public void setWhosTurn(int whosTurn) {
        this.whosTurn = whosTurn;
    }

    public int getMaxMoveCount() {
        return maxMoveCount;
    }

    public void setMaxMoveCount(int maxMoveCount) {
        this.maxMoveCount = maxMoveCount;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
