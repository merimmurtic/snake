package com.mygame.mygame;

public class Ball {

    private int ballX;
    private int ballY;
    private int ballSpeed;
    private int ballColor;


    public Ball(int ballX, int ballY, int ballSpeed, int ballColor) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.ballSpeed = ballSpeed;
        this.ballColor = ballColor;
    }

    public Ball(){

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
}
