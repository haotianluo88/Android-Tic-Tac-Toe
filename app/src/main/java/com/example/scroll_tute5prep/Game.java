package com.example.scroll_tute5prep;

import android.widget.Chronometer;

public class Game {
    private int[] gridArray;
    private int movescount;
    private int movesLeft;
    Chronometer timer;

    public Game(int[] inGridArray, int inMovesLeft, int inMovesCount){
        gridArray = inGridArray;
        movescount = inMovesCount;
        movesLeft = inMovesLeft;
    }

    public int[] getGridArray() {
        return gridArray;
    }

    public void setGridArray(int gridSize) {
        this.gridArray = new int[gridSize];
    }

    public int getMovescount() {
        return movescount;
    }

    public void setMovescount(int movescount) {
        this.movescount = movescount;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    public Chronometer getTimer() {
        return timer;
    }

    public void setTimer(Chronometer timer) {
        this.timer = timer;
    }
}
