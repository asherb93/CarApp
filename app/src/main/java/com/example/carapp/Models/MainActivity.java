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

    private final int DELAY = 1000;
    private final int MAIN_CAR_NUMBER = 3;
    private final int ROWS = 5;
    private final int COLS = 3;
    private final int life = 3;

    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[] main_IMG_Red_Car;

    private ShapeableImageView[][] main_IMG_Enemies;

    private ShapeableImageView[] main_IMG_Boom;


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
        main_IMG_Red_Car[0].setVisibility(View.INVISIBLE);
        main_IMG_Red_Car[2].setVisibility(View.INVISIBLE);
        handler.postDelayed(runnable, DELAY);


    }


    private void findViews() {

        main_IMG_Enemies = new ShapeableImageView[][]{
                {findViewById(R.id.ic_enemy1),
                        findViewById(R.id.ic_enemy2),
                        findViewById(R.id.ic_enemy3)},
                {findViewById(R.id.ic_enemy4),
                        findViewById(R.id.ic_enemy5),
                        findViewById(R.id.ic_enemy6)},
                {findViewById(R.id.ic_enemy7),
                        findViewById(R.id.ic_enemy8),
                        findViewById(R.id.ic_enemy9)},
                {findViewById(R.id.ic_enemy10),
                        findViewById(R.id.ic_enemy11),
                        findViewById(R.id.ic_enemy12)},
                {findViewById(R.id.enemy_on_left_hero),
                        findViewById(R.id.enemy_on_center_hero),
                        findViewById(R.id.enemy_on_right_hero)}

        };

        main_IMG_Boom = new ShapeableImageView[]{
                findViewById(R.id.boom_left),
                findViewById(R.id.boom_center),
                findViewById(R.id.boom_right),
        };


        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3),
        };

        main_IMG_Red_Car = new ShapeableImageView[]{
                findViewById(R.id.car_pos_left),
                findViewById(R.id.car_pos_center),
                findViewById(R.id.car_pose_right),
        };

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
                refreshUI();
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

        if (pos != 2) {
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
        main_IMG_Red_Car[gameManager.getCurrentPosition()].setVisibility(View.VISIBLE);
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

        gameManager.setTurn(turn + 1);


    }


}