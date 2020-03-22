package com.android.chapter17;

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

public class GLRender implements Renderer
{	
	private float rot = 0.0f;
	
	//��������
	private FloatBuffer vertices = FloatBuffer.wrap(new float[] {
			// FRONT
			 0.5f, -0.5f,  0.5f,
			 0.5f,  0.5f,  0.5f,
			-0.5f, -0.5f,  0.5f,
			-0.5f,  0.5f,  0.5f,
			// BACK
			-0.5f, -0.5f, -0.5f,
			-0.5f,  0.5f, -0.5f,
			 0.5f, -0.5f, -0.5f,
			 0.5f,  0.5f, -0.5f,
			// LEFT
			-0.5f, -0.5f,  0.5f,
			-0.5f,  0.5f,  0.5f,
			-0.5f, -0.5f, -0.5f,
			-0.5f,  0.5f, -0.5f,
			// RIGHT
			 0.5f, -0.5f, -0.5f,
			 0.5f,  0.5f, -0.5f,
			 0.5f, -0.5f,  0.5f,
			 0.5f,  0.5f,  0.5f,
			// TOP
			-0.5f,  0.5f,  0.5f,
			 0.5f,  0.5f,  0.5f,
			 -0.5f,  0.5f, -0.5f,
			 0.5f,  0.5f, -0.5f,
			// BOTTOM
			-0.5f, -0.5f,  0.5f,
			-0.5f, -0.5f, -0.5f,
			 0.5f, -0.5f,  0.5f,
			 0.5f, -0.5f, -0.5f,
		}); 
	
	//������������
	private FloatBuffer texCoords = FloatBuffer.wrap(new float[] {
			// FRONT
			 0.0f, 1.0f,
			 0.0f, 0.0f,
			 1.0f, 1.0f,
			 1.0f, 0.0f,
			// BACK
			 1.0f, 0.0f,
			 1.0f, 1.0f,
			 0.0f, 0.0f,
			 0.0f, 1.0f,
			// LEFT
			 1.0f, 0.0f,
			 1.0f, 1.0f,
			 0.0f, 0.0f,
			 0.0f, 1.0f,
			// RIGHT
			 1.0f, 0.0f,
			 1.0f, 1.0f,
			 0.0f, 0.0f,
			 0.0f, 1.0f,
			// TOP
			 0.0f, 0.0f,
			 1.0f, 0.0f,
			 0.0f, 1.0f,
			 1.0f, 1.0f,
			// BOTTOM
			 1.0f, 0.0f,
			 1.0f, 1.0f,
			 0.0f, 0.0f,
			 0.0f, 1.0f
		});
	
	private Bitmap mBitmapTexture1,mBitmapTexture2;
	
	private int mTexture[];
	
	float lightAmbient[] = new float[] { 0.2f, 0.2f, 0.2f, 1.0f };
	float lightDiffuse[] = new float[] { 1f, 1f, 1f, 1.0f };
	float[] lightPos = new float[] {0,0,3,1};
	
	float matAmbient[] = new float[] { 1f, 1f, 1f, 1.0f };
	float matDiffuse[] = new float[] { 1f, 1f, 1f, 1.0f };
	
	public GLRender(Context context)
	{
		mTexture = new int[2];
		mBitmapTexture1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture);
		mBitmapTexture2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.light);
	}
	
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
		
		drawCube(gl);
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
		
		// ��Ч��������
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, matAmbient, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, matDiffuse, 0);
		
		// ��Ч
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient,	0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse,	0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
		
		// ������Ȼ���
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// ������Ȼ��������
		gl.glDepthFunc(GL10.GL_LEQUAL);
		
		// ����2D������ͼ
		gl.glEnable(GL10.GL_TEXTURE_2D);
		// ������Ȳ���
		gl.glClearDepthf(1.0f);
		
		// �޳�����
		gl.glEnable(GL10.GL_CULL_FACE);
		// ����ƽ����Ӱ
		gl.glShadeModel(GL10.GL_SMOOTH);
		
		// װ������
		loadTexture(gl);
	}
	
	private void loadTexture(GL10 gl)
	{
		IntBuffer intBuffer = IntBuffer.allocate(2);
		// ��������
		gl.glGenTextures(2, intBuffer);
		mTexture[0] = intBuffer.get();
		
		//������
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture[0]);
		//��������Ҫ���Ŵ����Сʱ��ʹ�����Բ�ֵ��������ͼ��
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR); 
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR); 
		//������������ͼ��
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmapTexture1, 0);
		
		//������
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture[1]);
		//��������Ҫ���Ŵ����Сʱ��ʹ�����Բ�ֵ��������ͼ��
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR); 
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR); 
		//������������ͼ��
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmapTexture2, 0);	
		
		mBitmapTexture1.recycle();
		mBitmapTexture1 = null;
		mBitmapTexture2.recycle();
		mBitmapTexture2 = null;
	}
	
	
	private void drawCube(GL10 gl)
	{
		gl.glFrontFace(GL10.GL_CCW);
		
		//ƽ�Ʋ���
		gl.glTranslatef(0.0f,0.0f,-3.0f);
		
		//��ת����
		gl.glRotatef(rot,1.0f,0.0f,0.0f);
		gl.glRotatef(rot,0.0f,1.0f,0.0f);
		gl.glRotatef(rot,0.0f,0.0f,1.0f);
		
		//���Ų���
		gl.glScalef(4.0f, 4.0f, 4.0f);
		
		//�������ö�������
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		//��������������������
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glShadeModel(GL10.GL_SMOOTH);
		
		//���ö�������
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertices);
		
		// ���õ�һ������
		gl.glActiveTexture(GL10.GL_TEXTURE0); 
		gl.glClientActiveTexture(GL10.GL_TEXTURE0); 
		gl.glEnable(GL10.GL_TEXTURE_2D); 
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture[0]); 
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoords);
		
		// ���õڶ�������
		gl.glActiveTexture(GL10.GL_TEXTURE1); 
		gl.glClientActiveTexture(GL10.GL_TEXTURE1); 
		gl.glEnable(GL10.GL_TEXTURE_2D); 
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexture[1]); 
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoords);

		// ��������ӳ�䷽ʽ
		gl.glTexEnvx(GL10.GL_TEXTURE_ENV , GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE);
		/**
		 * target: ����ΪGL_TEXTURE_FILTER_CONTROL��GL_TEXTURE_ENV
		 * pname: ��targetΪGL_TEXTURE_FILTER_CONTROL, pname����ΪGL_TEXTURE_LOD_BIAS.
         *	      ��targetΪGL_TEXTURE_ENV, pname��ΪGL_TEXTURE_ENV_MODE, GL_TEXTURE_ENV_COLOR
		 * param: ��pnameΪGL_TEXTURE_LOD_BIAS, param��һ��������, ����ָ��GL_TEXTURE_LOD_BIAS��ֵ.
       	 *   	  ��pnameΪGL_TEXTURE_ENV_MODE,param��ȡֵΪGL_DECAL,GL_REPLACE,GL_MODULATE,GL_BLEND, GL_ADD��GL_COMBINE. ָ������ν�����ֵ��ƬԪ����ɫֵ�ϲ�����.
       	 *	      ��pnameΪGL_TEXTURE_ENV_COLOR, �����pname��һ������4��������������, ��4��Ԫ�طֱ���R, G, B, A����, ָ����һ������GL_BLEND��������ɫ.
		 */
		
		//����
		{
			gl.glColor4f(1.0f, 1, 1, 1.0f);
			gl.glNormal3f(0,0,1);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
			gl.glNormal3f(0,0,-1);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
		
			gl.glColor4f(1, 1.0f, 1, 1.0f);
			gl.glNormal3f(-1,0,0);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
			gl.glNormal3f(1,0,0);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
			
			gl.glColor4f(1, 1, 1.0f, 1.0f);
			gl.glNormal3f(0,1,0);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
			gl.glNormal3f(0,-1,0);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
		}
		// �����һ������
		gl.glActiveTexture(GL10.GL_TEXTURE0); 
		gl.glClientActiveTexture(GL10.GL_TEXTURE0);
		
		//��������ͨ����ȫ���رգ�
		gl.glClientActiveTexture(GL10.GL_TEXTURE0);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glClientActiveTexture(GL10.GL_TEXTURE1);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		//�رյڶ�������ͨ��
		gl.glActiveTexture(GL10.GL_TEXTURE1);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glActiveTexture(GL10.GL_TEXTURE0);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		//ȡ���������������
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
		//������ת�Ƕ�
		rot+=2.0f;
	}

}

