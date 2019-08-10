package com.mygame.mygame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameOverActivity extends AppCompatActivity {

    private Button startGameAgain;
    private TextView displayScore;
    private TextView highScoreLabel;
    private int score;
    private  static   int highScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        score = (int) getIntent().getExtras().get("score");

        startGameAgain = findViewById(R.id.play_again_id);

        displayScore = findViewById(R.id.displayScore);
        highScoreLabel = findViewById(R.id.bestScore);

        startGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        displayScore.setText("Score: "+ score);

        SharedPreferences settings = getSharedPreferences("GAME DATA", Context.MODE_PRIVATE);
        highScore = settings.getInt("HIGH_SCORE", highScore);

        if(score > highScore){
            highScore = score;
            highScoreLabel.setText("High Score : "+ highScore);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", highScore);
            editor.commit();
        } else {
            highScoreLabel.setText("High Score: "+ highScore);
        }
    }
}
