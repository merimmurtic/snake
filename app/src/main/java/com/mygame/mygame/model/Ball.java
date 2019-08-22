package com.mygame.mygame.model;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {

    private int ballX;
    private int ballY;
    private int ballSpeed;
    private int ballColor;

    private int radius;
    private int score;

    private Paint paint = new Paint();

    public Ball(int ballX, int ballY, int ballSpeed, int ballColor, int radius, int score){
        this.ballX = ballX;
        this.ballY = ballY;
        this.ballSpeed = ballSpeed;
        this.ballColor = ballColor;
        this.radius = radius;
        this.score = score;

        paint.setColor(ballColor);
        paint.setAntiAlias(false);
    }

    public void updatePosition() {
        this.ballX = ballX - ballSpeed;
    }

    public void increaseSpeed() {
        ballSpeed += 1;
    }

    public void draw(Canvas canvas, Snake snake) {
        if(ballX < 0){
            ballX = canvas.getWidth() + 21;
            ballY = snake.generateBallYPosition();
        }

        canvas.drawCircle(ballX, ballY, radius, paint);
    }

    public void hit() {
        ballX = -100;
    }

    public int getBallX() {
        return ballX;
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
    }

    public int getBallY() {
        return ballY;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
    }

    public int getBallSpeed() {
        return ballSpeed;
    }

    public void setBallSpeed(int ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    public int getBallColor() {
        return ballColor;
    }

    public void setBallColor(int ballColor) {
        this.ballColor = ballColor;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
