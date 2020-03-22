package com.wang.opengltest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.io.IOException;

public  class MyGLSurfaceView extends GLSurfaceView {

    private  MyGLRenderer renderer;
    private  Bitmap bitmap;


    public MyGLSurfaceView(Context context){
        super(context);
        init();
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        try {
            bitmap = BitmapFactory.decodeStream(getContext().getAssets().open("cat.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        renderer = new MyGLRenderer();
        renderer.setBitmap(bitmap);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer);
    }


    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float previousX;
    private float previousY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - previousX;
                float dy = y - previousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }

                renderer.setAngle(
                        renderer.getAngle() +
                                ((dx + dy) * TOUCH_SCALE_FACTOR));
                requestRender();
        }

        previousX = x;
        previousY = y;
        return true;
    }



}