package com.mygame.mygame.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import com.mygame.mygame.GameOverActivity;
import com.mygame.mygame.model.Ball;
import com.mygame.mygame.model.Snake;
import com.mygame.mygame.model.State;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

public class FlyingSnakeView extends View {

    private boolean touch = false;

    private Paint scorePaint = new Paint();

    private State state;

    private Snake snake;

    private List<Ball> balls = new ArrayList<>();

    public FlyingSnakeView(Context context) {
        super(context);

        snake = new Snake(getResources());
        state = new State(getResources());

        balls.add(new Ball(0, 0, 16, YELLOW, 15, 10));
        balls.add(new Ball(0, 0, 20, GREEN, 25, 20));
        balls.add(new Ball(0, 0, 22, BLACK, 25, -10));
        balls.add(new Ball(0, 0, 25, RED, 30, -100));

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        snake.setSnakeY(550);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        state.drawBackground(canvas);

        snake.recalculate(canvas.getHeight());
        snake.draw(canvas, touch);

        touch = false;

        for(Ball ball : balls) {
            ball.updatePosition();

            if(snake.hitBallChecker(ball)){
                state.increaseScore(ball.getScore());

                ball.hit();

                if(ball.getBallColor() == Color.RED) {
                    state.decreaseLifes();
                }
            }

            ball.draw(canvas, snake);
        }

        if(state.getLifeCounter() == 0){
            Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
            gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            gameOverIntent.putExtra("score", state.getScore());
            getContext().startActivity(gameOverIntent);
        }

        canvas.drawText("Score: " + state.getScore(), 20, 60, scorePaint);

        state.drawLifes(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            touch = true;

            snake.jump();

            //TODO: WHAT ABOUT NEGATIVE SCORE???
            if(state.getScore() % 50 == 0){
                for(Ball ball : balls) {
                    if(ball.getBallColor() != Color.BLACK) {
                        ball.increaseSpeed();
                    }
                }
            }

        }

        return true;
    }
}
