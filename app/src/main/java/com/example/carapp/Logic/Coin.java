package com.example.carapp.Logic;

public class Coin {

    private int row;
    private int col;

    public Coin(int cols) {
        this.row=0;
        this.col=(int)(Math.random() *((cols-1) - 0 + 1) + 0);
    }

    public int getCol() {
        return col;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }


}
