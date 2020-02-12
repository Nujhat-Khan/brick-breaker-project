package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener,ActionListener {

	private boolean play=false;
	private int score=0;
	private int totalbrick=30;
	private Timer timer;
	private int delay=8;
	private int playerX=250;
	private int ballposX=300;
	private int ballposY=350;
	private int ballXdir=-1;
	private int ballYdir=-2;
	private brickMaking brick;
	public GamePlay()
	{ 
	  brick=new brickMaking(3,10);
	  addKeyListener(this);
	  setFocusable(true);
	  setFocusTraversalKeysEnabled(false);
	  timer=new Timer(delay, this);
	  timer.start();
	  
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(2,2,692,592);
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		g.setColor(Color.BLUE);
		g.fillRect(playerX, 550, 100, 8);
		
		g.setColor(Color.white);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		if(totalbrick<=0)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("Italic",Font.BOLD,25));
			g.drawString("CONGRATULATION, YOU WON",190 , 300);
			g.drawString("Press Enter to restart...", 230, 350);
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("Italic",Font.BOLD,25));
		g.drawString("Score: "+score, 550, 30);
		
		brick.draw((Graphics2D)g);
		
		if(ballposY>590)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("Italic",Font.BOLD,25));
			g.drawString("GAME OVER. Scores:"+score,190 , 300);
			g.drawString("Press enter to restart...", 230, 350);
		}
		
		g.dispose();
	}
	
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(new Rectangle(ballposX,ballposY,20,20).intersects (new Rectangle(playerX,550,100,8)))
		{
			ballYdir=-ballYdir;
		}
		
		A:for(int i=0;i<brick.brick.length;i++)
		{
			for(int j=0;j<brick.brick[0].length;j++)
			{
				if(brick.brick[i][j]>0)
				{
					int brickX=j*brick.brickWidth+80;
					int brickY=i*brick.brickHeight+50;
					int brickWidth=brick.brickWidth;
					int brickHeight=brick.brickHeight;
					Rectangle brickrec=new Rectangle(brickX,brickY,brickWidth,brickHeight);
					Rectangle ballrec=new Rectangle(ballposX,ballposY,20,20);
					
					if(ballrec.intersects(brickrec))
					{
						brick.brickValue(0, i, j);
						totalbrick--;
						score+=5;
						if(ballposX+19<=brickrec.x || ballposX+1>=brickrec.x+brickrec.width)
						{
							ballXdir=-ballXdir;
						}
						else
							ballYdir=-ballYdir;
						break A;
					}
				}
			}
		}
		
		if(play)
		{
			ballposX+=ballXdir;
			ballposY+=ballYdir;
			if(ballposX<0)
			{
				ballXdir=-ballXdir;
			}
			if(ballposY<0)
			{
				ballYdir=-ballYdir;
			}
			if(ballposX>670)
			{
				ballXdir=-ballXdir;
			}
		}
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(playerX>=590)
			{playerX=590;}
			else
				moveRight();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(playerX<=5)
			{playerX=5;}
			else
				moveLeft();
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(!play)
			{
			    play=true;
				score=0;
				totalbrick=30;
				playerX=250;
				ballposX=300;
				ballposY=350;
				ballXdir=-1;
				ballYdir=-2;
				brick=new brickMaking(3,10);
				repaint();

			}
		}
	}
	public void moveRight()
	{
		play=true;
		playerX+=20;
	}
	public void moveLeft()
	{
		play=true;
		playerX-=20;
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}

}
