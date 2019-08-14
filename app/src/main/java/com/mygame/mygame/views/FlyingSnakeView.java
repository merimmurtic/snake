package com.mygame.mygame.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import com.mygame.mygame.GameOverActivity;
import com.mygame.mygame.R;
import com.mygame.mygame.model.Ball;
import com.mygame.mygame.model.Snake;
import com.mygame.mygame.model.State;

import java.lang.reflect.Array;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

public class FlyingSnakeView extends View {

    private int canvasWidth, canvasHeight;


    private int redX, redY, redSpeed = 25;
    private Paint redPaint = new Paint();


    private boolean touch = false;

    private Paint scorePaint = new Paint();

    private State state;

    private Snake snake;

    private Ball ball;

    public FlyingSnakeView(Context context) {
        super(context);

        snake = new Snake(getResources());
        state = new State(getResources());
        ball = new Ball();

        ball.createBall(0, 0, 16, YELLOW);
        ball.createBall(0, 0, 20, GREEN);
        ball.createBall(0, 0, 22, BLACK);



        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);



        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        snake.setSnakeY(550);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        state.drawBackground(canvas);

        snake.recalculate(canvasHeight);
        snake.draw(canvas, touch);

        touch = false;

        if(ball.getBallColor().toString().equals("YELLOW")){
            ball.setBallX(ball.getBallX() - ball.getBallSpeed());
        }


        if(ball.getBallColor().toString().equals("YELLOW")){
            if (snake.hitBallChecker(ball.getBallX(), ball.getBallY())) {
                state.increaseScore(10);
                ball.setBallX(ball.getBallX() - 100);
            }
        }

        if(ball.getBallColor().toString().equals("YELLOW")){
            if (ball.getBallX() < 0) {
                ball.setBallX(canvasWidth + 21);
                ball.setBallY(snake.generateBallYPosition());
            }
            canvas.drawCircle(ball.getBallX(), ball.getBallY(), 15, ball.getBallColor());
        }

        if(ball.getBallColor().toString().equals("GREEN")) {
            ball.setBallX(ball.getBallX() - ball.getBallSpeed());
        }

        if(ball.getBallColor().toString().equals("GREEN")) {
            if (snake.hitBallChecker(ball.getBallX(), ball.getBallY())) {
                state.increaseScore(20);
                ball.setBallX(ball.getBallX() - 100);
            }
        }

        if(ball.getBallColor().toString().equals("GREEN")) {
            if (ball.getBallX() < 0) {
                ball.setBallX(canvasWidth + 21);
                ball.setBallY(snake.generateBallYPosition());
            }
            canvas.drawCircle(ball.getBallX(), ball.getBallY(), 25, ball.getBallColor());
        }

        if(ball.getBallColor().toString().equals("BLACK")) {
            ball.setBallX(ball.getBallX() - ball.getBallSpeed());

            if (snake.hitBallChecker(ball.getBallX(), ball.getBallY())) {
                state.increaseScore(-10);
                ball.setBallX(ball.getBallX() - 100);
            }

            if (ball.getBallX() < 0) {
                ball.setBallX(canvasWidth + 21);
                ball.setBallY(snake.generateBallYPosition());
            }
            canvas.drawCircle(ball.getBallX(), ball.getBallY(), 25, ball.getBallColor());
        }

        redX = redX - redSpeed;

        if(snake.hitBallChecker(redX, redY)){

            redX = - 100;
            state.decreaseLifes();
            if(state.getLifeCounter() == 0){
                Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score", state.getScore());
                getContext().startActivity(gameOverIntent);
            }
        }

        if(redX < 0){
            redX = canvasWidth + 21;
            redY = snake.generateBallYPosition();
        }
        canvas.drawCircle(redX, redY, 30, redPaint);

        canvas.drawText("Score: " + state.getScore(), 20, 60, scorePaint);

        state.drawLifes(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            touch = true;

            snake.jump();

            if(state.getScore() % 50 == 0){
                if(ball.getBallColor().equals(YELLOW)){
                    ball.setBallSpeed(ball.getBallSpeed() + 1);
                }
                 redSpeed += 1;
                if(ball.getBallColor().equals(GREEN)){
                    ball.setBallSpeed(ball.getBallSpeed() + 1);
                }

            }

        }

        return true;
    }
}
