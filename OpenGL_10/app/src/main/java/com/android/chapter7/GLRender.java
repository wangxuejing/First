package com.android.chapter7;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;

import com.wang.opengl_10.R;

import static com.android.GLESUtils.createDirectBuffer;

public class GLRender implements Renderer
{
	private Bitmap mBitmapTexture = null;
	
	int mTexture[];
	
	private float rot = 0.0f;
	
	public GLRender(Context context)
	{
		mBitmapTexture = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture);
		mTexture = new int[1];
	}
	
	//�����εĶ�������
	private float[] verticesSquare = new float[]{
			-1.0f,  1.0f, -0.0f,
			 1.0f,  1.0f, -0.0f,
			-1.0f, -1.0f, -0.0f,
			 1.0f, -1.0f, -0.0f};
	//�����εķ�������
	private float[] normalsSquare = new float[]{
			0.0f, 0.0f, 1.0f,
			0.0f, 0.0f, 1.0f,
			0.0f, 0.0f, 1.0f,
			0.0f, 0.0f, 1.0f};
	
	//�����ε���ͼ����
//	private FloatBuffer texCoordsSquare = FloatBuffer.wrap(new float[]{
//			0.0f, 0.5f,
//	        0.5f, 0.5f,
//	        0.0f, 0.0f,
//	        0.5f, 0.0f});
	
//	private FloatBuffer texCoordsSquare = FloatBuffer.wrap(new float[]{
//	        0.25f, 0.75f,
//	        0.75f, 0.75f,
//	        0.25f, 0.25f,
//	        0.75f, 0.25f});
	
//	private FloatBuffer texCoordsSquare = FloatBuffer.wrap(new float[]{
//	        0.0f, -1.0f,
//	        -1.0f, -1.0f,
//	        0.0f, 0.0f,
//	        -1.0f, 0.0f});
	
	private float []texCoordsSquare = new float[]{
			0.0f, 2.0f,
			2.0f, 2.0f,
			0.0f, 0.0f,
			2.0f, 0.0f};
	
	
	
	//�����εĶ�������
	private float[] verticesTriangle = new float[]{
			-1.0f,  1.0f, -0.0f,
			 1.0f,  1.0f, -0.0f,
			 0.0f, -1.0f, -0.0f};
	
	//�����εķ�������
	private float []normalsTriangle = new float[]{
			0.0f, 0.0f, 1.0f,
	        0.0f, 0.0f, 1.0f,
	        0.0f, 0.0f, 1.0f};
	//�����ε���ͼ����
	private float []texCoordsTriangle = new float[]{
			0.0f, 1.0f,
	        1.0f, 0.0f,
	        0.0f, 0.0f};
	
	@Override
	public void onDrawFrame(GL10 gl)
	{
		// TODO Auto-generated method stub
		
		// ����������Ļ
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		// ����ģ����ͼ����
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		
		//���þ���
		gl.glLoadIdentity();
		
		// �ӵ�任
		GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);
		

		drawSquare(gl);
		
		//drawTriangle(gl);
		
		//rot+=0.5f;
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		// TODO Auto-generated method stub
		
		float ratio = (float) width / height;
		
		// �����ӿ�(OpenGL�����Ĵ�С)
		gl.glViewport(0, 0, width, height);

		// ����ͶӰ����Ϊ͸��ͶӰ
		gl.glMatrixMode(GL10.GL_PROJECTION);
		
		// ����ͶӰ������Ϊ��λ����
		gl.glLoadIdentity();
		
		//����һ��͸��ͶӰ���������ӿڴ�С��
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 1000);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		// TODO Auto-generated method stub
		
		//����ϵͳ��Ҫ��͸�ӽ�������
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		
		//����������Ļ����ɫ
		gl.glClearColor(0, 0, 0, 1);
		
		//������Ȼ���
		gl.glEnable(GL10.GL_DEPTH_TEST);
		
		setupLight(gl);
		
		setupTexture(gl);
	}
	
	
	private void setupLight(GL10 gl)
	{
		//������Ч
		gl.glEnable(GL10.GL_LIGHTING);

		//����0�Ź�Դ
		gl.glEnable(GL10.GL_LIGHT0);
		
		//���������ɫ
		FloatBuffer light0Ambient = FloatBuffer.wrap(new float[]{0.4f, 0.4f, 0.4f, 1.0f});
		//���û�����
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, light0Ambient);
		
		//��������ɫ
		FloatBuffer light0Diffuse = FloatBuffer.wrap(new float[]{0.8f, 0.8f, 0.8f, 1.0f});
		//���������
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, light0Diffuse);
		
		//�߹����ɫ
		FloatBuffer light0Position = FloatBuffer.wrap(new float[]{10.0f, 10.0f, 10.0f});
		//���ø߹�
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, light0Position);
	}
	
	
	private void setupTexture(GL10 gl)
	{
		//��2D��ͼ
	    gl.glEnable(GL10.GL_TEXTURE_2D);
	    
	    //�򿪻�ɫ����
	    gl.glEnable(GL10.GL_BLEND);
	    
	    //ָ����ɫ����
	    gl.glBlendFunc(GL10.GL_ONE, GL10.GL_SRC_COLOR); 
	    
		IntBuffer intBuffer = IntBuffer.allocate(1);
		// ��������
		gl.glGenTextures(1, intBuffer);
		mTexture[0] = intBuffer.get();
		
		//������
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture[0]);
		
		//��������Ҫ���Ŵ����Сʱ��ʹ�����Բ�ֵ��������ͼ��
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR); 
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR); 
		
//		//�ظ�Ч��
//		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
//		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		
		//��������
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
	    
		//������������ͼ��
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmapTexture, 0);		     
	}
	
	private void drawSquare(GL10 gl)
	{
		//ƽ�Ʋ���
		gl.glTranslatef(0.0f,0.0f,-3.0f);
		
		//��ת����
		gl.glRotatef(rot,1.0f,1.0f,1.0f);
		
		//���Ų���
		gl.glScalef(3.0f, 3.0f, 3.0f);
		
		//�������ö�������
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		//�������÷�������
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		
		//��������������������
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		//������
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture[0]);
		
		//���ö�������
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, createDirectBuffer(verticesSquare));
		
		//���÷�������
		gl.glNormalPointer(GL10.GL_FLOAT, 0, createDirectBuffer(normalsSquare));
		
		//����������������
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, createDirectBuffer(texCoordsSquare));

		//����������
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
	    
		//�رն������顢�������顢������������
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	private void drawTriangle(GL10 gl)
	{
		//ƽ�Ʋ���
		gl.glTranslatef(0.0f,0.0f,-3.0f);
		
		//��ת����
		gl.glRotatef(rot,1.0f,1.0f,1.0f);
		
		//���Ų���
		gl.glScalef(3.0f, 3.0f, 3.0f);
		
		//�������ö�������
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		//�������÷�������
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		
		//��������������������
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		//������
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture[0]);
		
		//���ö�������
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, createDirectBuffer(verticesTriangle));
		
		//���÷�������
		gl.glNormalPointer(GL10.GL_FLOAT, 0, createDirectBuffer(normalsTriangle));
		
		//����������������
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, createDirectBuffer(texCoordsTriangle));
		
		//����������
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
	    
		//�رն������顢�������顢������������
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}

}

