package com.mygame.mygame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.mygame.mygame.R;

public class State {

    private int score = 0;
    private int lifeCounter = 3;
    private Bitmap life[] = new Bitmap[2];
    private Bitmap backgroundImage;

    public State(Resources resources){
        life[0]= BitmapFactory.decodeResource(resources, R.drawable.hearts);
        life[1]= BitmapFactory.decodeResource(resources, R.drawable.heart_grey);

        backgroundImage = BitmapFactory.decodeResource(resources, R.drawable.background);
    }

    public void drawBackground(Canvas canvas) {
        canvas.drawBitmap(backgroundImage, 0, 0, null);
    }

    public void drawLifes(Canvas canvas) {
        for(int i = 0; i < 3; i++){
            int x = (int) (508 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if(i < lifeCounter){
                canvas.drawBitmap(life[0], x, y, null);
            }else{
                canvas.drawBitmap(life[1], x, y, null);
            }
        }
    }

    public void increaseScore(int value) {
        score += value;
    }

    public void decreaseLifes() {
        lifeCounter--;
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
