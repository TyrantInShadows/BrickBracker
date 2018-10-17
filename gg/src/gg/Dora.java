package gg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Dora {
	public int map[][];
	public int BrickW;
	public int BrickH;
	public Dora(int red, int kolona)
	{
		map=new int [red][kolona];
		for(int i=0;i<map.length;i++)
			for(int j=0;j<map[i].length;j++)
				map[i][j]=(int)(Math.random()*100+2);
		BrickW=540/kolona;
		BrickH=150/red;
	}
	
	public void draw(Graphics2D g)
	{
		for(int i=0;i<map.length;i++)
			for(int j=0;j<map[i].length;j++)
			{
				if(map[i][j]>0 && map[i][j]<=50)
				{
					g.setColor(Color.white);
					g.fillRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
				}
				else if(map[i][j]>50&&map[i][j]<=60)
				{
					g.setColor(Color.red);
					g.fillRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
				}
				else if(map[i][j]>60&&map[i][j]<=70)
				{
					g.setColor(Color.yellow);
					g.fillRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
				}
				else if(map[i][j]>70&&map[i][j]<=80)
				{
					g.setColor(Color.blue);
					g.fillRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
				}
				else if(map[i][j]>80&&map[i][j]<=90)
				{
					g.setColor(Color.green);
					g.fillRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
				}
				else if(map[i][j]>90&&map[i][j]<=102)
				{
					g.setColor(Color.gray);
					g.fillRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*BrickW+80, i*BrickH+50, BrickW, BrickH);
				}
			}
	}
	public void setBrickValue(int value,int row,int col)
	{
		if(map[row][col]!=0)
		{
			map[row][col]=value;
			igrA.TotalBricks--;
		}
	}
	
}
