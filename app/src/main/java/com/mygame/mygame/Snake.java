package com.mygame.mygame;

import android.graphics.Bitmap;

public class Snake {

    private int snakeX;
    private int snakeY;
    private int snakeJump;
    private Bitmap snake[] = new Bitmap[2];

    public Snake(int snakeX, int snakeY, int snakeJump, Bitmap[] snake) {
        this.snakeX = snakeX;
        this.snakeY = snakeY;
        this.snakeJump = snakeJump;
        this.snake = snake;
    }

    public Snake(){

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
