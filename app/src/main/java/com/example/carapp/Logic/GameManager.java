package com.example.carapp.Logic;

import com.example.carapp.Utilities.SignalGenerator;

import java.util.ArrayList;

public class GameManager {


    private int life;

    private int currentLife;
    private int turn;
    private int rows;
    private int cols;


    private ArrayList<Enemy> enemyArr=new ArrayList<>();

    private ArrayList<Coin> coinArr=new ArrayList<>();

    private int[][] gameMat;

    private final int COIN_POS=3;
    private final int HERO_POS=2;
    private final int ENEMY_POS=1;
    private int score=0;

    private final int COIN_WEIGHT=10;


    private final int enemyOrCoinOdds=5;

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
        currentCol=cols/2;
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

    public int getScore() {
        return score;
    }

    public int iterateGameV2()
    {

        printBoard();

        score++;
        int hitFlag=0;//if hitFlag=0 nothing happend,hitFlag=2 collision,hitFlag=3 picked coin
        if(enemyArr.size()+coinArr.size()==rows) {
            cleanHeroRow();
        }

        for(Enemy n:enemyArr)
        {
            gameMat[n.getRow()][n.getCol()] = 0;
            n.setRow(n.getRow()+1);
            if(gameMat[n.getRow()][n.getCol()]==HERO_POS)
            {
                hitFlag=ENEMY_POS;
                if (currentLife > 0) {
                    currentLife--;
                }
            }
            else
            {
                gameMat[n.getRow()][n.getCol()]=ENEMY_POS;
            }
        }

        for(Coin c:coinArr)
        {
            if(gameMat[c.getRow()][c.getCol()]!=ENEMY_POS) {
                gameMat[c.getRow()][c.getCol()] = 0;
            }
            c.setRow(c.getRow()+1);
            if(gameMat[c.getRow()][c.getCol()]==HERO_POS)
            {
                hitFlag=COIN_POS;
                pickedCoin();
            }
            else
            {
                gameMat[c.getRow()][c.getCol()]=COIN_POS;
            }

        }


        int coinOrEnemy=coinOrEnemyRand();
        if(coinOrEnemy==enemyOrCoinOdds-1){
            Coin newCoin=new Coin(cols);
            coinArr.add(newCoin);
            gameMat[newCoin.getRow()][newCoin.getCol()]=COIN_POS;
        }
        else {
            Enemy newEnemy = new Enemy(cols);
            enemyArr.add(newEnemy);
            gameMat[newEnemy.getRow()][newEnemy.getCol()]=ENEMY_POS;
        }
        return hitFlag;



    }

    public int coinOrEnemyRand()
    {
        return (int) (Math.random() *((enemyOrCoinOdds-1) - 0 + 1));
    }

    private void cleanHeroRow() {
        if(enemyArr.isEmpty()==false&&enemyArr.get(0).getRow()==rows-1) {
            enemyArr.remove(0);
        }
        else if(coinArr.isEmpty()==false&&coinArr.get(0).getRow()==rows-1)
        {
            coinArr.remove(0);
        }
        for(int i=rows-1;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(gameMat[i][j]==ENEMY_POS||gameMat[i][j]==COIN_POS)
                {
                    gameMat[i][j]=0;
                }
            }
        }
    }

    public int getENEMY_POS() {
        return ENEMY_POS;
    }

    public int getCOIN_POS(){
        return COIN_POS;
    }

    public void pickedCoin()
    {
        score+=COIN_WEIGHT;
    }
    public int updatePlayerOnMatRight(int i, int currentPosition)
    {
        int hitFlag=0;
        if(gameMat[i][currentPosition]==ENEMY_POS)
        {
            hitFlag=ENEMY_POS;
        }
        else if(gameMat[i][currentPosition]==COIN_POS)
        {
            pickedCoin();
            hitFlag=COIN_POS;
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
        else if(gameMat[i][currentPosition]==COIN_POS)
        {
            score++;
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
        System.out.println("current position"+getCurrentPosition());
        System.out.println("----------------------");
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
        System.out.println("----------------------");

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
