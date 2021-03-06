package com.android.chapter10;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

import static com.android.GLESUtils.createDirectBuffer;

public class GLRender implements Renderer
{
	private Context mcontext;
	
	private float rot = 0.0f;
	
	//顶点数组
	private float[] vertices = new float[]{
			 0.f, 			-0.525731f,  0.850651f,
			 0.850651f, 		   0.f,  0.525731f,
			 0.850651f, 		   0.f, -0.525731f,
			-0.850651f, 		   0.f, -0.525731f,
			-0.850651f, 		   0.f,  0.525731f,
			-0.525731f, 	 0.850651f,  0.f,
			 0.525731f, 	 0.850651f,  0.f,
			 0.525731f, 	-0.850651f,  0.f,
			-0.525731f, 	-0.850651f,  0.f,
			 0.f, 			-0.525731f, -0.850651f,
			 0.f, 			 0.525731f, -0.850651f,
			 0.f, 			 0.525731f,  0.850651f};
	
	//颜色数组
	private float[] colors = new float[]{
			1.0f, 0.0f, 0.0f, 1.0f,
			1.0f, 0.5f, 0.0f, 1.0f,
			1.0f, 1.0f, 0.0f, 1.0f,
			0.5f, 1.0f, 0.0f, 1.0f,
			0.0f, 1.0f, 0.0f, 1.0f,
			0.0f, 1.0f, 0.5f, 1.0f,
			0.0f, 1.0f, 1.0f, 1.0f,
			0.0f, 0.5f, 1.0f, 1.0f,
			0.0f, 0.0f, 1.0f, 1.0f,
			0.5f, 0.0f, 1.0f, 1.0f,
			1.0f, 0.0f, 1.0f, 1.0f,
			1.0f, 0.0f, 0.5f, 1.0f};
	
	//索引数组
	private ByteBuffer icosahedronFaces = ByteBuffer.wrap(new byte[]{
			 1,  2,  6,
	         1,  7,  2,
	         3,  4,  5,
	         4,  3,  8,
	         6,  5, 11,
	         5,  6, 10,
	         9, 10,  2,
	        10,  9,  3,
	         7,  8,  9,
	         8,  7,  0,
	        11,  0,  1,
	         0, 11,  4,
	         6,  2, 10,
	         1,  6, 11,
	         3,  5, 10,
	         5,  4, 11,
	         2,  7,  9,
	         7,  1,  0,
	         3,  9,  8,
	         4,  8,  0});
	
	public GLRender(Context context)
	{
		mcontext = context;
	}
	@Override
	public void onDrawFrame(GL10 gl)
	{
		// 首先清理屏幕
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		// 设置模型视图矩阵
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		
		//重置矩阵
		gl.glLoadIdentity();
		
		// 视点变换
		GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);
		
		draw3D(gl);
		
		drawText(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		
		mWidth = width;
		
		mHeight = height;
		
		float ratio = (float) width / height;
		
		// 设置视口(OpenGL场景的大小)
		gl.glViewport(0, 0, width, height);

		// 设置投影矩阵为透视投影
		gl.glMatrixMode(GL10.GL_PROJECTION);
		
		// 重置投影矩阵（置为单位矩阵）
		gl.glLoadIdentity();
		
		//创建一个透视投影矩阵（设置视口大小）
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 1000);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		
		//告诉系统需要对透视进行修正
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		
		//设置清理屏幕的颜色
		gl.glClearColor(0, 0, 0, 1);
		
		//启用深度缓存
		gl.glEnable(GL10.GL_DEPTH_TEST);
		
		initText(gl);
	}
	
	private void draw3D(GL10 gl)
	{
		gl.glFrontFace(GL10.GL_CCW);
		
		//平移操作
		gl.glTranslatef(0.0f,-1.0f,-3.0f);
		
		//旋转操作
		gl.glRotatef(rot,1.0f,1.0f,1.0f);
		
		//缩放操作
		gl.glScalef(3.0f, 3.0f, 3.0f);
		
		//允许设置顶点数组
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		//允许设置颜色数组
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);;
		
		gl.glShadeModel(GL10.GL_SMOOTH);
		
		//设置顶点数组
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, createDirectBuffer(vertices));
		
		//设置颜色数组
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, createDirectBuffer(colors));
		
		//绘制
		gl.glDrawElements(GL10.GL_TRIANGLES, 60, GL10.GL_UNSIGNED_BYTE, icosahedronFaces);

		//取消顶点数组和颜色数组的设置
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

		//更改旋转角度
		rot+=0.5;
	}
	
	////////////////////////////////
	private LabelMaker mLabels;
	private int mString1,mString2,mString3;
	private Paint mLabelPaint;
	private int mWidth,mHeight;
	private Typeface mFace; 
	
	private void initText(GL10 gl)
	{
		mLabelPaint = new Paint();
		mLabelPaint.setAntiAlias(true);
		// 消除锯齿
		mLabelPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

		mFace = Typeface.createFromAsset(mcontext.getAssets(),"fonts/samplefont.ttf");  
		
		mLabelPaint.setTypeface(mFace);   
		
		if (mLabels != null)
		{
			mLabels.shutdown(gl);
		}
		else
		{
			mLabels = new LabelMaker(true, 320, 480);
		}
		mLabels.initialize(gl);
		mLabels.beginAdding(gl);
		mLabelPaint.setARGB(0xff, 0xff, 0x00, 0x00);
		mLabelPaint.setTextSize(50);
		mString1 = mLabels.add(gl, "this is text", mLabelPaint);
		mLabelPaint.setARGB(0xff, 0xff, 0xff, 0x00);
		mString2 = mLabels.add(gl, "Android OpenGL ES develop", mLabelPaint);
		mLabelPaint.setARGB(0xff, 0xff, 0xff, 0xff);
		mLabelPaint.setTextSize(50);
		mString3 = mLabels.add(gl, "chapter10 : 2D text", mLabelPaint);
		mLabels.endAdding(gl);
	}
	
	private void drawText(GL10 gl)
	{		
		// 开始绘制
        mLabels.beginDrawing(gl, mWidth, mHeight);
        
        float x = (mWidth - mLabels.getWidth(mString1))/2;
        mLabels.draw(gl, x, 350, mString1);
        
        x = (mWidth - mLabels.getWidth(mString2))/2;
        mLabels.draw(gl, x, 350-mLabels.getHeight(mString1), mString2);
        
        x = (mWidth - mLabels.getWidth(mString3))/2;
        mLabels.draw(gl, x, 350-mLabels.getHeight(mString1)-mLabels.getHeight(mString2), mString3);
        // 结束绘制
        mLabels.endDrawing(gl);
	}
}
