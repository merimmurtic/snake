package com.mygame.mygame.model;

import android.graphics.Paint;

public class Ball {

    private int ballX;
    private int ballY;
    private int ballSpeed;
    private Paint ballColor;

    public Ball(){

    }



    public void createBall(int ballX, int ballY, int ballSpeed, int ballColor){
        ballX = ballX;
        ballY = ballY;
        ballSpeed = ballSpeed;
        ballColor = ballColor;
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

    public Paint getBallColor() {
        return ballColor;
    }

    public void setBallColor(Paint ballColor) {
        this.ballColor = ballColor;
    }
}
