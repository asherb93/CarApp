package com.example.carapp.Logic;

import java.util.ArrayList;

public class GameManager {


    private int hit;
    private int life;
    private int turn;
    private int rows;
    private int cols;

    private ArrayList<Enemy> enemyArr=new ArrayList<>();


    private int[][] gameMat;
    private final int HERO_POS=2;

    private final int ENEMY_POS=1;

    public int[][] getGameMat() {
        return gameMat;
    }

    public void setGameMat(int[][] gameMat) {
        this.gameMat = new int[rows][cols];
    }



    public GameManager(int life, int rows, int cols) {
        this.life = life;
        this.rows=rows;
        this.cols=cols;
        currentCol=1;
        turn=0;
        setGameMat(gameMat);
        gameMat[rows-1][cols/2]=HERO_POS;

    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }



    public int getCurrentPosition() {
        return currentCol;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentCol = currentPosition;
    }

    private int currentCol;

    public boolean iterateGame()
    {
        boolean hitFlag=false;
        Enemy newEnemy=new Enemy(cols);
        for(Enemy n:enemyArr)
        {
            if (n.getRow() >= rows - 2) {
                if (gameMat[n.getRow() + 1][n.getCol()] == HERO_POS) {
                    hitFlag = true;
                    if (life > 0) {
                        life--;
                    }
                }

            }
            gameMat[n.getRow()][n.getCol()] = 0;
            n.setRow(n.getRow() + 1);
        }
        enemyArr.add(newEnemy);
        printBoard();
        removeDeadEnemies();
        updateMat();
        printBoard();

        printBoard();
        return hitFlag;
    }


    public void updatePlayerOnMatRight(int i, int currentPosition)
    {
        gameMat[i][currentPosition]=HERO_POS;
        gameMat[i][currentPosition-1]=0;

    }

    public void updatePlayerOnMatLeft(int i, int currentPosition)
    {
        gameMat[i][currentPosition]=HERO_POS;
        gameMat[i][currentPosition+1]=0;
    }

    public void printBoard()
    {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                System.out.print(""+gameMat[i][j]+"");
            }
            System.out.println();
            System.out.println();

        }
    }

    public void updateMat()
    {
        for(Enemy n:enemyArr)
        {
            gameMat[n.getRow()][n.getCol()]=ENEMY_POS;
        }
    }

    public void removeDeadEnemies()
    {

        for(int i=0;i<enemyArr.size();i++)
        {
            if (enemyArr.get(i).getRow() == rows-1 ) {
                System.out.println("in");
                gameMat[enemyArr.get(i).getRow()-1][enemyArr.get(i).getCol()]=0;
                enemyArr.remove(enemyArr.get(i));
            }
        }

    }
}
