package com.example.carapp.Logic;

import java.util.ArrayList;

public class GameManager {


    private int life;

    private int currentLife;
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
        setLife(life);
        currentLife = life;
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

    public int getCurrentLife(){return currentLife;}

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

    public boolean iterateGameV2()
    {
        boolean hitFlag=false;

        if(enemyArr.size()==rows) {
            cleanHeroRow();
        }

        for(Enemy n:enemyArr)
        {
            gameMat[n.getRow()][n.getCol()] = 0;
            n.setRow(n.getRow()+1);
            if(gameMat[n.getRow()][n.getCol()]==HERO_POS)
            {
                hitFlag=true;
                if (currentLife > 0) {
                    currentLife--;
                }

            }
            else
            {
                gameMat[n.getRow()][n.getCol()]=ENEMY_POS;
            }
        }

        Enemy newEnemy=new Enemy(cols);
        enemyArr.add(newEnemy);
        gameMat[newEnemy.getRow()][newEnemy.getCol()]=ENEMY_POS;
        printBoard();
        return hitFlag;






    }

    private void cleanHeroRow() {
        enemyArr.remove(0);
        for(int i=rows-1;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(gameMat[i][j]==1)
                {
                    gameMat[i][j]=0;
                }
            }
        }
    }



    public boolean updatePlayerOnMatRight(int i, int currentPosition)
    {
        boolean hitFlag=false;
        if(gameMat[i][currentPosition]==ENEMY_POS)
        {
            hitFlag=true;
        }
        gameMat[i][currentPosition]=HERO_POS;
        gameMat[i][currentPosition-1]=0;
        return hitFlag;


    }

    public boolean updatePlayerOnMatLeft(int i, int currentPosition)
    {
        boolean hitFlag=false;
        if(gameMat[i][currentPosition]==ENEMY_POS)
        {
            hitFlag=true;
        }

        gameMat[i][currentPosition]=HERO_POS;
        gameMat[i][currentPosition+1]=0;
        return hitFlag;

    }

    public boolean isGameOver()
    {
        if(currentLife==0)
        {
            restartLives();
            return true;
        }
        return false;
    }

    public void restartLives()
    {
        currentLife=life;
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
        System.out.println();
        System.out.println();

    }


    public void removeDeadEnemies()
    {

        for(int i=0;i<enemyArr.size();i++)
        {
            if (enemyArr.get(i).getRow() == rows-1 ) {
                gameMat[enemyArr.get(i).getRow()][enemyArr.get(i).getCol()]=0;
                enemyArr.remove(enemyArr.get(i));
            }
        }

    }
}
