package com.mygame.mygame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.mygame.mygame.R;

public class Snake {

    private int snakeX = 10;
    private int snakeY;
    private int snakeJump;
    private Bitmap snake[] = new Bitmap[2];

    private int minSnakeY;
    private int maxSnakeY;

    public Snake(Resources resources){
        snake[0] = BitmapFactory.decodeResource(resources, R.drawable.snake);
        snake[1] = BitmapFactory.decodeResource(resources, R.drawable.snake2);
    }

    public void recalculate(int canvasHeight) {
        minSnakeY = snake[0].getHeight();
        maxSnakeY = canvasHeight - snake[0].getHeight() * 3;
        snakeY = snakeY + snakeJump;

        if(snakeY < minSnakeY){
            snakeY = minSnakeY;
        }
        if(snakeY > maxSnakeY){
            snakeY = maxSnakeY;
        }

        snakeJump = snakeJump + 2;
    }

    public void draw(Canvas canvas, boolean touch) {
        if(touch){
            canvas.drawBitmap(snake[1], snakeX, snakeY, null);
        }else{
            canvas.drawBitmap(snake[0], snakeX, snakeY, null);
        }
    }

    public boolean hitBallChecker(Ball ball){
        if(snakeX < ball.getBallX() && ball.getBallX() < (snakeX + snake[0].getWidth())
                && snakeY < ball.getBallY() && ball.getBallY() < (snakeY + snake[0].getHeight()) ){
            return true;
        }

        return false;
    }

    public void jump() {
        snakeJump = -22;
    }

    public int generateBallYPosition() {
        return (int) Math.floor(Math.random() *(maxSnakeY - minSnakeY) + minSnakeY);
    }

    public int getSnakeX() {
        return snakeX;
    }

    public void setSnakeX(int snakeX) {
        this.snakeX = snakeX;
    }

    public int getSnakeY() {
        return snakeY;
    }

    public void setSnakeY(int snakeY) {
        this.snakeY = snakeY;
    }

    public int getSnakeJump() {
        return snakeJump;
    }

    public void setSnakeJump(int snakeJump) {
        this.snakeJump = snakeJump;
    }

    public Bitmap[] getSnake() {
        return snake;
    }

    public void setSnake(Bitmap[] snake) {
        this.snake = snake;
    }
}
