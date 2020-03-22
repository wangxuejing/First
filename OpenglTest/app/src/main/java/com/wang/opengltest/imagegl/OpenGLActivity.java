package com.wang.opengltest.imagegl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;


import android.widget.Toast;


import com.wang.opengltest.MyGLSurfaceView;
import com.wang.opengltest.R;
import com.wang.opengltest.imagegl.gles.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 使用 OpenGL 绘制
 */
public class OpenGLActivity extends Activity {
    public static final String TYPE = "draw_type";
    public static final int TYPE_TRIANGLE = 140;
    public static final int TYPE_IMAGE = 100;
    private MyGLSurfaceView mGlSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl);
        int glVersion = GLESUtils.getSupportGLVersion(this);
        String msg = "支持 GLES " + glVersion;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        mGlSurfaceView = findViewById(R.id.gl_surface);
        // Create an OpenGL ES 2.0 context
        mGlSurfaceView.setEGLContextClientVersion(2);
        GLSurfaceView.Renderer renderer = null;
        int type = getIntent().getIntExtra(TYPE, TYPE_IMAGE);
        if (type == TYPE_IMAGE) {
            File imageFile = new File(FileUtils.getExternalAssetsDir(this), "cat.jpg");
            try {
                renderer = new ImageRenderer(getAssets().open("cat.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            renderer =null;
        }
        // Set the Renderer for drawing on the GLSurfaceView
        mGlSurfaceView.setRenderer(renderer);
        // Render the view only when there is a change in the drawing data
        mGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGlSurfaceView.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGlSurfaceView.onPause();
    }

}
