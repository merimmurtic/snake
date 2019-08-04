package com.mygame.mygame;

import android.graphics.Bitmap;

public class LifeState {

    private int score;
    private int lifeCounter;
    private Bitmap life[] = new Bitmap[2];
    private Bitmap backgroundImage;


    public LifeState(int score, int lifeCounter) {
        this.score = score;
        this.lifeCounter = lifeCounter;
    }

    public LifeState(){

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLifeCounter() {
        return lifeCounter;
    }

    public void setLifeCounter(int lifeCounter) {
        this.lifeCounter = lifeCounter;
    }
}
