package com.android.chapter26;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/* 球体绘制 */
public class GlSphere extends GlObject
{
	// 半径
	private float			radius;
	// 把球体分成几份（片）
	private int				slices;
	// 把每一份分成几份
	private int				stacks;
	// 是否使用法线和纹理坐标
	private boolean			useNormals;
	private boolean			useTexCoords;
	
	// 数据缓冲区
	private FloatBuffer[]	slicesBuffers;
	private FloatBuffer[]	normalsBuffers;
	private FloatBuffer[]	texCoordsBuffers;


	public GlSphere(float radius, int slices, int stacks, boolean useNormals, boolean useTexCoords)
	{
		this.radius = radius;
		this.stacks = stacks;
		this.slices = slices;
		this.useNormals = useNormals;
		this.useTexCoords = useTexCoords;
		generateData();
	}

	// 生成数据
	private void generateData()
	{

		slicesBuffers = new FloatBuffer[slices];
		if (useNormals)
		{
			normalsBuffers = new FloatBuffer[slices];
		}
		if (useTexCoords)
		{
			texCoordsBuffers = new FloatBuffer[slices];
		}

		for (int i = 0; i < slices; i++)
		{

			float[] vertexCoords = new float[6 * (stacks + 1)];
			float[] normalCoords = new float[6 * (stacks + 1)];
			float[] textureCoords = new float[4 * (stacks + 1)];

			double alpha0 = i * (2 * Math.PI) / slices;
			double alpha1 = (i + 1) * (2 * Math.PI) / slices;

			float cosAlpha0 = (float) Math.cos(alpha0);
			float sinAlpha0 = (float) Math.sin(alpha0);
			float cosAlpha1 = (float) Math.cos(alpha1);
			float sinAlpha1 = (float) Math.sin(alpha1);

			for (int j = 0; j <= stacks; j++)
			{

				double beta = j * Math.PI / stacks - Math.PI / 2;

				float cosBeta = (float) Math.cos(beta);
				float sinBeta = (float) Math.sin(beta);

				Utils.setXYZ(vertexCoords, 6 * j, radius * cosBeta * cosAlpha1, radius * sinBeta, radius * cosBeta * sinAlpha1);
				Utils.setXYZ(vertexCoords, 6 * j + 3, radius * cosBeta * cosAlpha0, radius * sinBeta, radius * cosBeta * sinAlpha0);

				if (useNormals)
				{
					Utils.setXYZ(normalCoords, 6 * j, cosBeta * cosAlpha1, sinBeta, cosBeta * sinAlpha1);
					Utils.setXYZ(normalCoords, 6 * j + 3, cosBeta * cosAlpha0, sinBeta, cosBeta * sinAlpha0);
				}

				if (useTexCoords)
				{
					Utils.setXY(textureCoords, 4 * j, ((float) (i + 1)) / slices, ((float) j) / stacks);
					Utils.setXY(textureCoords, 4 * j + 2, ((float) i) / slices, ((float) j) / stacks);
				}
			}

			slicesBuffers[i] = FloatBuffer.wrap(vertexCoords);
			if (useNormals)
			{
				normalsBuffers[i] = FloatBuffer.wrap(normalCoords);
			}
			if (useTexCoords)
			{
				texCoordsBuffers[i] = FloatBuffer.wrap(textureCoords);
			}
		}
	}


	@Override
	public void draw(GL10 gl)
	{
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		if (useNormals)
		{
			gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		}
		if (useTexCoords)
		{
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		}

		for (int i = 0; i < slices; i++)
		{ // draw each slice
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, slicesBuffers[i]);
			if (useNormals)
			{
				gl.glNormalPointer(GL10.GL_FLOAT, 0, normalsBuffers[i]);
			}
			if (useTexCoords)
			{
				gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoordsBuffers[i]);
			}
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 2 * (stacks + 1));
		}

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		if (useNormals)
		{
			gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		}
		if (useTexCoords)
		{
			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		}
	}
}
