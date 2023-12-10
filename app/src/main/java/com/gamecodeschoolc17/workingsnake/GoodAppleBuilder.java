package com.gamecodeschoolc17.workingsnake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class GoodAppleBuilder implements AppleBuilder {
    private NewApple Apple;
    public Bitmap mBitmapAppleGood;
    public Point locationGood;
    public Point mSpawnRangeGood;
    public int mSizeGood;

    // GoodAppleBuilder constructor
    public GoodAppleBuilder(Bitmap mBitmapApple, Point location, Point mSpawnRange, int mSize){
        mBitmapAppleGood = mBitmapApple;    // Bitmap
        locationGood = location;            // Point
        mSpawnRangeGood = mSpawnRange;      // Point
        mSizeGood = mSize;                  // Int
    };

    @Override
    public Point getLocation() {
        return Apple.location;
    }
    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(Apple.mBitmapApple,
                Apple.location.x * Apple.mBitmapApple.getWidth(),
                Apple.location.y * Apple.mBitmapApple.getHeight(), paint);
    }

    @Override
    public void spawn(Point spawnRange, boolean hide) {
        if (hide == true) {
            Apple.location.x = -10; // Hide the apple off-screen until the game starts
        } else {
            Apple.mSpawnRange = spawnRange;
            Random random = new Random();
            Apple.location.x = random.nextInt(Apple.mSpawnRange.x) + 1;
            Apple.location.y = random.nextInt(Apple.mSpawnRange.y - 1) + 1;
        }
    }

    @Override
    public void setSize(int size) {
        Apple.mSize = size;
    }

    @Override
    public void setBitmap(Context context, int resourceId) {
        Apple.mBitmapApple = BitmapFactory.decodeResource(context.getResources(), resourceId);
    }

    public NewApple returnApple() {
        return Apple;
    }

//    @Override
//    public NewApple build() {
//        return Apple(Apple.mSpawnRange, Apple.mSize, Apple.mBitmapApple);
//    }

}