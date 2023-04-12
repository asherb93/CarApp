package com.example.carapp.Models;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.view.View;
import android.widget.Toast;

import com.example.carapp.Logic.GameManager;
import com.example.carapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;


public class MainActivity extends AppCompatActivity {

    private final int DELAY=1000;
    private final int MAIN_CAR_NUMBER=3;
    private final int ROWS=5;
    private final int COLS=3;
    private final int life=3;

    private MaterialButton[] main_BTN_control;
    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[] main_IMG_Red_Car;

    private ShapeableImageView[][] main_IMG_Enemies;

    private int[][] gameMat;

    private ExtendedFloatingActionButton right_Button;
    private ExtendedFloatingActionButton left_Button;
    GameManager gameManager=new GameManager(life,ROWS,COLS);

    private final Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, DELAY); //Do it again in a second
            refreshUI();
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        startGame();
        setDirectionClickListeners();
    }

    private void startGame() {
        for(int i=0;i<ROWS-1;i++)
        {
            for(int j=0;j<COLS;j++) {
                main_IMG_Enemies[i][j].setVisibility(View.INVISIBLE);
            }
        }
        main_IMG_Red_Car[0].setVisibility(View.INVISIBLE);
        main_IMG_Red_Car[2].setVisibility(View.INVISIBLE);
        handler.postDelayed(runnable,DELAY);


    }


    private void findViews() {

        main_IMG_Enemies=new ShapeableImageView[][]{
                {findViewById(R.id.ic_enemy1),
                        findViewById(R.id.ic_enemy2),
                        findViewById(R.id.ic_enemy3)},
                {findViewById(R.id.ic_enemy4),
                        findViewById(R.id.ic_enemy5),
                        findViewById(R.id.ic_enemy6)},
                {findViewById(R.id.ic_enemy7),
                        findViewById(R.id.ic_enemy8),
                        findViewById(R.id.ic_enemy9)},
                        { findViewById(R.id.ic_enemy10),
                findViewById(R.id.ic_enemy11),
                findViewById(R.id.ic_enemy12)}


    };

        main_IMG_hearts=new ShapeableImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3),
        };

        main_IMG_Red_Car=new ShapeableImageView[]{
                findViewById(R.id.car_pos_left),
                findViewById(R.id.car_pos_center),
                findViewById(R.id.car_pose_right),
        };

        right_Button=findViewById(R.id.right_Button);
        left_Button=findViewById(R.id.left_Button);




    }

    private void setDirectionClickListeners() {
        right_Button.setOnClickListener(view -> carGoRight());
        left_Button.setOnClickListener(view -> carGoLeft());
    }

    private void carGoLeft() {
        int pos=gameManager.getCurrentPosition();
        if(pos!=0){
            for(int i=0;i<MAIN_CAR_NUMBER;i++)
            {
                main_IMG_Red_Car[i].setVisibility(View.INVISIBLE);
            }
            main_IMG_Red_Car[pos-1].setVisibility(View.VISIBLE);
            gameManager.setCurrentPosition(pos-1);
            gameManager.updatePlayerOnMatLeft(ROWS-1,gameManager.getCurrentPosition());
        }

    }

    private void carGoRight() {
        int pos=gameManager.getCurrentPosition();

        if(pos!=2){
            for(int i=0;i<MAIN_CAR_NUMBER;i++)
            {
                main_IMG_Red_Car[i].setVisibility(View.INVISIBLE);
            }
            main_IMG_Red_Car[pos+1].setVisibility(View.VISIBLE);
            gameManager.setCurrentPosition(pos+1);
            gameManager.updatePlayerOnMatRight(ROWS-1,gameManager.getCurrentPosition());

        }

    }


    private void refreshUI() {
        int turn = gameManager.getTurn();
        gameManager.printBoard();
        boolean hit=gameManager.iterateGame();
        gameMat = gameManager.getGameMat();
        for (int i = 0; i < ROWS-1; i++)
        {
            for(int j=0;j<COLS;j++)
            {
                if(gameMat[i][j]==1)
                {
                    main_IMG_Enemies[i][j].setVisibility(View.VISIBLE);
                }
                else
                {
                    main_IMG_Enemies[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
        if(hit)
        {
            //System.out.println("number of lives:"+gameManager.getLife());
            main_IMG_hearts[gameManager.getLife()].setVisibility(View.INVISIBLE);
            Toast.makeText(this,"\uD83D\uDC80 Oof!",Toast.LENGTH_LONG).show();
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }

        }
        gameManager.setTurn(turn+1);
        System.out.println(turn);


    }


}