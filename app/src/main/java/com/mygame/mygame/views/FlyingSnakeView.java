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
import com.mygame.mygame.model.Snake;
import com.mygame.mygame.model.State;

public class FlyingSnakeView extends View {

    private int canvasWidth, canvasHeight;

    private int yellowX, yellowY, yellowSpeed = 16;
    private Paint yellowPaint = new Paint();

    private int greenX, greenY, greenSpeed = 20;
    private Paint greenPaint = new Paint();

    private int redX, redY, redSpeed = 25;
    private Paint redPaint = new Paint();

    private int blackX, blackY, blackSpeed = 22;
    private Paint blackPaint = new Paint();

    private boolean touch = false;

    private Paint scorePaint = new Paint();

    private State state;

    private Snake snake;

    public FlyingSnakeView(Context context) {
        super(context);

        snake = new Snake(getResources());
        state = new State(getResources());

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);


        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        blackPaint.setColor(Color.BLACK);
        blackPaint.setAntiAlias(false);


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

        yellowX = yellowX - yellowSpeed;

        if(snake.hitBallChecker(yellowX, yellowY)){
            state.increaseScore(10);
            yellowX = - 100;
        }

        if(yellowX < 0){
            yellowX = canvasWidth + 21;
            yellowY = snake.generateBallYPosition();
        }
        canvas.drawCircle(yellowX, yellowY, 15, yellowPaint);

        greenX = greenX - greenSpeed;

        if(snake.hitBallChecker(greenX, greenY)){
            state.increaseScore(20);
            greenX = - 100;
        }

        if(greenX < 0){
            greenX = canvasWidth + 21;
            greenY = snake.generateBallYPosition();
        }
        canvas.drawCircle(greenX, greenY, 25, greenPaint);

        blackX = blackX - blackSpeed;

        if(snake.hitBallChecker(blackX, blackY)){
            state.increaseScore(-10);
            blackX = - 100;
        }

        if(blackX < 0){
            blackX = canvasWidth + 21;
            blackY = snake.generateBallYPosition();
        }
        canvas.drawCircle(blackX, blackY, 25, blackPaint);

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
                 yellowSpeed += 1;
                 redSpeed += 1;
                 greenSpeed += 1;
            }

        }

        return true;
    }
}
