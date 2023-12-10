package com.gamecodeschoolc17.workingsnake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

public interface Apple {
    public Bitmap mBitmapApple;
    public Point drawLocation = new Point();


    // Private constructor to enforce the use of the builder
    public Apple(Point spawnRange, int size, Bitmap bitmapApple) {
        mBitmapApple = bitmapApple;
        mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, size, size, false);
    }

    // This is called every time an apple is eaten
    // Draw the apple
    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mBitmapApple, drawLocation.x * mBitmapApple.getWidth(), drawLocation.y * mBitmapApple.getHeight(), paint);
    }

    // Builder class for Apple
    public static class AppleBuilder implements Apple{
        private Point mSpawnRange;
        private int mSize;
        private Point location = new Point();
        private Bitmap mBitmapApple;


        // Let SnakeGame know where the apple is
        // SnakeGame can share this with the snake
        Point getLocation() {
            return location;
        }

        public AppleBuilder spawn(Point spawnRange, boolean hide) {
            if (hide == true) {
                location.x = -10; // Hide the apple off-screen until the game starts
            } else {
                mSpawnRange = spawnRange;
                Random random = new Random();
                location.x = random.nextInt(mSpawnRange.x) + 1;
                location.y = random.nextInt(mSpawnRange.y - 1) + 1;
            }
            return this;
        }

        public AppleBuilder setSize(int size) {
            mSize = size;
            return this;
        }

        public AppleBuilder setBitmap(Context context, int resourceId) {
            mBitmapApple = BitmapFactory.decodeResource(context.getResources(), resourceId);
            return this;
        }

        public Apple build() {
            return new Apple(mSpawnRange, mSize, mBitmapApple);
        }
    }
}