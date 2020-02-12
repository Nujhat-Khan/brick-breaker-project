package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class brickMaking {
	public int brick[][];
	public int brickWidth;
	public int brickHeight;
	public brickMaking(int row,int col)
	{
		brick=new int [row][col];
		for(int i=0;i<brick.length;i++)
		{
			for(int j=0;j<brick[0].length;j++)
			{
				brick[i][j]=1;
			}
		}
		brickWidth=540/col;
		brickHeight=150/row;
	}
	public void draw(Graphics2D g)
	{
		for(int i=0;i<brick.length;i++)
		{
			for(int j=0;j<brick[0].length;j++)
			{
				if(brick [i][j]>0)
				{
					g.setColor(Color.white);
					g.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
					
				}
			}
		}
	}
	public void brickValue(int value,int row,int col)
	{
		brick[row][col]=0;
	}
}
