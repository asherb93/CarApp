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
import com.google.android.material.textview.MaterialTextView;


public class MainActivity extends AppCompatActivity {

    private final int DELAY = 1000;
    private final int MAIN_CAR_NUMBER = 5;
    private final int ROWS = 7;
    private final int COLS = 5;
    private final int life = 3;

    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[] main_IMG_Red_Car;

    private ShapeableImageView[][] main_IMG_Enemies;

    private ShapeableImageView[] main_IMG_Boom;

    private MaterialTextView scoreTextView;


    private int[][] gameMat;

    private MaterialButton right_Button;
    private MaterialButton left_Button;
    GameManager gameManager = new GameManager(life, ROWS, COLS);

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

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("App pause ");
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, DELAY);
    }


    private void startGame() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                main_IMG_Enemies[i][j].setVisibility(View.INVISIBLE);
            }
        }
        for (int i = 0; i < main_IMG_Boom.length; i++) {
            main_IMG_Boom[i].setVisibility(View.INVISIBLE);
        }
        for(int i=0;i<main_IMG_Red_Car.length;i++)
        {
            if(i != COLS / 2) {
                main_IMG_Red_Car[i].setVisibility(View.INVISIBLE);
            }
        }
        handler.postDelayed(runnable, DELAY);


    }


    private void findViews() {

        main_IMG_Enemies = new ShapeableImageView[][]{
                {findViewById(R.id.ic_enemy0_0),
                        findViewById(R.id.ic_enemy0_1),
                        findViewById(R.id.ic_enemy0_2),
                        findViewById(R.id.ic_enemy0_3),
                        findViewById(R.id.ic_enemy0_4)
                },
                {findViewById(R.id.ic_enemy1_0),
                        findViewById(R.id.ic_enemy1_1),
                        findViewById(R.id.ic_enemy1_2),
                        findViewById(R.id.ic_enemy1_3),
                        findViewById(R.id.ic_enemy1_4)
                },
                {findViewById(R.id.ic_enemy2_0),
                        findViewById(R.id.ic_enemy2_1),
                        findViewById(R.id.ic_enemy2_2),
                        findViewById(R.id.ic_enemy2_3),
                        findViewById(R.id.ic_enemy2_4)
                },
                {findViewById(R.id.ic_enemy3_0),
                        findViewById(R.id.ic_enemy3_1),
                        findViewById(R.id.ic_enemy3_2),
                        findViewById(R.id.ic_enemy3_3),
                        findViewById(R.id.ic_enemy3_4)
                },
                {findViewById(R.id.ic_enemy4_0),
                        findViewById(R.id.ic_enemy4_1),
                        findViewById(R.id.ic_enemy4_2),
                        findViewById(R.id.ic_enemy4_3),
                        findViewById(R.id.ic_enemy4_4)
                },
                {findViewById(R.id.ic_enemy5_0),
                        findViewById(R.id.ic_enemy5_1),
                        findViewById(R.id.ic_enemy5_2),
                        findViewById(R.id.ic_enemy5_3),
                        findViewById(R.id.ic_enemy5_4)
                },

                {findViewById(R.id.enemy6_0),
                        findViewById(R.id.enemy6_1),
                        findViewById(R.id.enemy6_2),
                        findViewById(R.id.enemy6_3),
                        findViewById(R.id.enemy6_4)
                },


        };

        main_IMG_Boom = new ShapeableImageView[]{
                findViewById(R.id.boom6_0),
                findViewById(R.id.boom6_1),
                findViewById(R.id.boom6_2),
                findViewById(R.id.boom6_3),
                findViewById(R.id.boom6_4),

        };


        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3),
        };

        main_IMG_Red_Car = new ShapeableImageView[]{
                findViewById(R.id.car6_0),
                findViewById(R.id.car6_1),
                findViewById(R.id.car6_2),
                findViewById(R.id.car6_3),
                findViewById(R.id.car6_4),

        };
        scoreTextView=findViewById(R.id.score_LBL_score);

        right_Button = findViewById(R.id.right_Button);
        left_Button = findViewById(R.id.left_Button);



    }

    private void setDirectionClickListeners() {
        right_Button.setOnClickListener(view -> carGoRight());
        left_Button.setOnClickListener(view -> carGoLeft());
    }

    private void carGoLeft() {
        int pos = gameManager.getCurrentPosition();
        if (pos != 0) {
            for (int i = 0; i < MAIN_CAR_NUMBER; i++) {
                main_IMG_Red_Car[i].setVisibility(View.INVISIBLE);
            }
            main_IMG_Red_Car[pos - 1].setVisibility(View.VISIBLE);
            gameManager.setCurrentPosition(pos - 1);
            if (gameManager.updatePlayerOnMatLeft(ROWS - 1, gameManager.getCurrentPosition())) {
                main_IMG_Enemies[ROWS - 1][gameManager.getCurrentPosition()].setVisibility(View.INVISIBLE);
            }
        }
    }

    private void carHit() {
        main_IMG_hearts[gameManager.getCurrentLife()].setVisibility(View.INVISIBLE);
        main_IMG_Boom[gameManager.getCurrentPosition()].setVisibility(View.VISIBLE);
        main_IMG_Red_Car[gameManager.getCurrentPosition()].setVisibility(View.INVISIBLE);
        Toast.makeText(this, "\uD83D\uDC80 Crashed!", Toast.LENGTH_LONG).show();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }

    }

    private void carGoRight() {
        int pos = gameManager.getCurrentPosition();
        if (pos != COLS-1) {
            for (int i = 0; i < MAIN_CAR_NUMBER; i++) {
                main_IMG_Red_Car[i].setVisibility(View.INVISIBLE);
            }
            main_IMG_Red_Car[pos + 1].setVisibility(View.VISIBLE);
            gameManager.setCurrentPosition(pos + 1);
            if (gameManager.updatePlayerOnMatRight(ROWS - 1, gameManager.getCurrentPosition())) {
                main_IMG_Enemies[ROWS - 1][gameManager.getCurrentPosition()].setVisibility(View.INVISIBLE);
            }
        }

    }


    private void refreshUI() {
        for (int i = 0; i < main_IMG_Boom.length; i++) {
            main_IMG_Boom[i].setVisibility(View.INVISIBLE);
        }
        int turn = gameManager.getTurn();
        boolean hit = gameManager.iterateGameV2();
        gameMat = gameManager.getGameMat();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (gameMat[i][j] == 1) {
                    main_IMG_Enemies[i][j].setVisibility(View.VISIBLE);
                } else {
                    main_IMG_Enemies[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
        if (hit) {
            carHit();
        }

        if (gameManager.isGameOver()) {
            for (ShapeableImageView main_img_heart : main_IMG_hearts) {
                main_img_heart.setVisibility(View.VISIBLE);
            }
        }
        scoreTextView.setText("SCORE:"+gameManager.getScore());

        gameManager.setTurn(turn + 1);


    }


}