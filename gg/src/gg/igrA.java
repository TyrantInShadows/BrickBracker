package gg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;




public class igrA extends JPanel implements KeyListener,ActionListener{
	private boolean game=false;
	public double n=2;
	private int score=0;
	public static int TotalBricks=21;
	private Timer time;
	private int zakasnjenje=50;
	private int PlayerX=310;
	private int BallX=420;
	private int BallY=310;
	private int  Ballsize=20;
	private double BallXdir=-2*n;
	private double BallYdir=-4*n;
	private Dora dora;
	private int zivoti=2;
	private int PlayerY=550;
	private int DuzPalice=100;
	private int smanjenje=2;
	private int ubrzanje=0;
	
	
	public igrA() {
		dora=new Dora(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time=new Timer(zakasnjenje, this);
		time.start();
		
	}
	 public void paint(Graphics g)
	 {
		 g.setColor(Color.black);
		 g.fillRect(1, 1, 699, 599);
		 
		 dora.draw((Graphics2D)g);
		 g.setColor(Color.yellow);
		 g.fillRect(0,0, 692, 3);
		 g.fillRect(0, 0, 3, 592);
		 g.fillRect(691, 0, 3, 592);
		 
		 g.setColor(Color.orange);
		 g.setFont(new Font("calibri",Font.ITALIC, 25));
		 g.drawString(""+score, 590, 30);
		 
		 g.setColor(Color.green);
		 g.fillRect(PlayerX, PlayerY, DuzPalice, 8);
		 
		 g.setColor(Color.yellow);
		 g.fillOval(BallX, BallY, Ballsize, Ballsize);
		 
		 if(TotalBricks==0||zivoti==0)
		 {
			 game=false;
			 BallXdir=0;
			 BallYdir=0;
			 g.setColor(Color.red);
			 g.setFont(new Font("calibri",Font.BOLD,50));
			 g.drawString("GAME OVER", 225, 275);
			 g.setFont(new Font("calibri",Font.PLAIN,20));
			 g.drawString("Score: "+score, 325, 295);
			 time.stop();
		 }
		 else if(BallY>PlayerY+20)
		 {
			 restartZ();
			 g.setColor(Color.red);
			 g.setFont(new Font("calibri",Font.BOLD,50));
			 g.drawString("Izgubli ste zivot", 225, 275);
			 g.setFont(new Font("calibri",Font.PLAIN,20));
			 g.drawString("Imate jos: "+zivoti+" zivota", 300, 295);
		 }
		 
		 g.dispose();
	
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		if(game)
		{
			
			intersect();
			SpecijalniBrick();
			BallX+=BallXdir;
			BallY+=BallYdir;
			if(BallX<3)
				BallXdir=-BallXdir;
			if(BallY<3)
				BallYdir=-BallYdir;
			if(BallX>661)
				BallXdir=-BallXdir;
			repaint();
		}
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_D)
		{
			if(PlayerX>=690-DuzPalice)
				PlayerX=590;
			else
				MoveRight();
		}
		if(e.getKeyCode()==KeyEvent.VK_A)
		{
			if(PlayerX<=10)
				PlayerX=10;
			else
				MoveLeft();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public void MoveRight()
	{
		game=true;
		PlayerX+=20;
	}
	public void MoveLeft()
	{
		game=true;
		PlayerX-=20;
	}
	
	public void intersect()
	{
		if(BallXdir>0)
		{
			if(new Rectangle(BallX,BallY,Ballsize,Ballsize).intersects(new Rectangle(PlayerX,PlayerY,5,8)))
				{
					BallXdir=-BallXdir;
					BallYdir=-BallYdir;
				}
			else if(new Rectangle(BallX,BallY,Ballsize,Ballsize).intersects(new Rectangle(PlayerX+5,PlayerY,(int)(DuzPalice*0.3),8))||new Rectangle(BallX,BallY,Ballsize,Ballsize).intersects(new Rectangle(PlayerX+65,PlayerY,(int)(DuzPalice*0.3),8))) 
				{
					BallXdir=2*n;
					BallYdir=-4*n;
				}
		}
	
	else
		{
			if(new Rectangle(BallX,BallY,Ballsize,Ballsize).intersects(new Rectangle(PlayerX+95,PlayerY,5,8)))
				{
					BallXdir=-BallXdir;
					BallYdir=-BallYdir;
				}
		
			else if(new Rectangle(BallX,BallY,Ballsize,Ballsize).intersects(new Rectangle(PlayerX+5,PlayerY,(int)(DuzPalice*0.3),8))||new Rectangle(BallX,BallY,Ballsize,Ballsize).intersects(new Rectangle(PlayerX+65,PlayerY,(int)(DuzPalice*0.3),8)))
				{
					BallXdir=-2*n;
					BallYdir=-4*n;
				}
		}
	if(new Rectangle(BallX,BallY,Ballsize,Ballsize).intersects(new Rectangle(PlayerX+35,PlayerY,(int)(DuzPalice*0.3),8)))
					BallYdir=-6*n;
	}
	public void restartZ()
	{
		game=false;
		zivoti--;
		score=score/2;
		PlayerY-=50;
		BallX=420;
		BallY=310;
		BallXdir=1*n;
		BallYdir=-2*n;
		Ballsize=20;
		DuzPalice=100;
		n=2;
		smanjenje=2;
		ubrzanje=0;
	}
	
	public void SpecijalniBrick()
	{
		A:for(int i=0;i<dora.map.length;i++)
			for(int j=0;j<dora.map[i].length;j++)
			if(dora.map[i][j]>0)
			{
			
				int brickX=j*dora.BrickW+80;
				int brickY=i*dora.BrickH+50;
				Rectangle rect=new Rectangle(brickX,brickY,dora.BrickW,dora.BrickH);
				Rectangle ball=new Rectangle(BallX,BallY,Ballsize,Ballsize);
				if(rect.intersects(ball))
				{
					if(dora.map[i][j]>50&&dora.map[i][j]<=60)
					{
						if (ubrzanje==0)
						{
							n*=2;
							ubrzanje++;
						}
					}
					else if(dora.map[i][j]>60&&dora.map[i][j]<=70)
					{
						if(i==0&&j==0)
						{
							dora.setBrickValue(0, i+1, j);
							dora.setBrickValue(0, i, j+1);
						}
						else if(i==(dora.map.length-1)&&j==0)
						{
							dora.setBrickValue(0, i-1, j);
							dora.setBrickValue(0, i, j+1);
						}
						else if(i==(dora.map.length-1)&&j==(dora.map[i].length-1) )
						{
							dora.setBrickValue(0, i-1, j);
							dora.setBrickValue(0, i, j-1);
						}
						else if(i==0&&j==(dora.map[i].length-1))
						{
							dora.setBrickValue(0, i, j-1);
							dora.setBrickValue(0, i+1, j);
						}
						else if(i==0)
						{
							dora.setBrickValue(0, i, j-1);
							dora.setBrickValue(0, i+1, j);
							dora.setBrickValue(0, i, j+1);
						}
						else if(j==0)
						{
							dora.setBrickValue(0, i-1, j);
							dora.setBrickValue(0, i+1, j);
							dora.setBrickValue(0, i, j+1);
						}	
						else if(i==(dora.map.length-1))
						{
							dora.setBrickValue(0, i-1, j);
							dora.setBrickValue(0, i, j-1);
							dora.setBrickValue(0, i, j+1);
						}
						else if(j==(dora.map[i].length-1))
						{
							dora.setBrickValue(0, i-1, j);
							dora.setBrickValue(0, i+1, j);
							dora.setBrickValue(0, i, j-1);
						}
						else 
						{
							dora.setBrickValue(0, i-1, j);
							dora.setBrickValue(0, i+1, j);
							dora.setBrickValue(0, i, j-1);
							dora.setBrickValue(0, i, j+1);
						}
					}
					else if(dora.map[i][j]>70&&dora.map[i][j]<=80)
					{
						Ballsize+=10;
						BallX+=10;
						BallY+=10;
					}
					else if(dora.map[i][j]>80&&dora.map[i][j]<=90)
					{
						if(smanjenje!=0)
						{DuzPalice/=2;
						smanjenje--;}
					}
					if(dora.map[i][j]>90&&dora.map[i][j]<=100)
					{
						dora.map[i][j]=1;
					}
					if(dora.map[i][j]!=1)
					dora.setBrickValue(0, i, j);
					score+=5;
					if(BallX+19<=rect.x || BallX+1>=rect.x+dora.BrickW)
					{
						BallXdir=-BallXdir;
					}
					else
						BallYdir=-BallYdir;
					if(dora.map[i][j]==1)
					dora.map[i][j]=20;
					break A;
				}
				
	
	}
	}
		public void UpisT(int s)
	{
		try
		{
			int []niz=new int[11];
			int i=1;
			FileReader fr=new FileReader("HighScores.txt");
			BufferedReader br=new BufferedReader(fr);
			niz[0]=s;
			String a=br.readLine();
			while(a!=null && i<11)
			{
				niz[i]=Integer.parseInt(a);
				i++;
				a=br.readLine();
			}
			br.close();
			for(int j=0;j<i-1;j++)
			{
				for(int k=j+1;k<i;k++)
				{
					if(niz[j]<niz[k])
					{
						int p=niz[j];
						niz[j]=niz[k];
						niz[k]=p;
					}
				}
				
			}
			if(i>10)
			{
				i=10;
			}
		FileWriter fw=new FileWriter("HighScores.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		for(int x=0;x<i;x++)
		{
			bw.write(Integer.toString(niz[x]));
			bw.newLine();
		}
		bw.close();
	}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}
