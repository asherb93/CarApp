package com.example.carapp.Logic;

public class Enemy {

    private int row;
    private int col;

    public Enemy(int cols) {
        this.row=0;
        this.col=(int)(Math.random() *((cols-1) - 0 + 1) + 0);
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void printEnemy()
    {
        System.out.printf("my location is [%d][%d]\n",getRow(),getCol());
    }
}
