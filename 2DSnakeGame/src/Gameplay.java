import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{

	private int[] xSnakeLength = new int[750];
	private int[] ySnakeLength = new int[750];
	
	private boolean rightDirection = false;
	private boolean leftDirection = false;
	private boolean upDirection = false;
	private boolean downDirection = false;
	
	private ImageIcon rightMouthIcon;
	private ImageIcon leftMouthIcon;
	private ImageIcon upMouthIcon;
	private ImageIcon downMouthIcon;
	private ImageIcon snakeIcon;
	
	private int[] xEnemyPos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
			600,625,650,675,700,725,750,775,800,825,850};
	
	private int[] yEnemyPos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
			600,625};
	
	private ImageIcon enemyIcon;
	
	private Random random=new Random();
	
	private int xEnemyPosIndex = random.nextInt(34);
	private int yEnemyPosIndex = random.nextInt(23);
	

	private Timer timer;
	private int delay=90;
	
	private int snakeLength=3;
	private int moves=0;
	private int score=0;
	
	private ImageIcon titleImage;
	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer=new Timer(delay,this);
		timer.start();
	}
	public void paint(Graphics g) {
		
		if(moves==0)
		{
			xSnakeLength[0]=100;
			xSnakeLength[1]=75;
			xSnakeLength[2]=50;
			
			ySnakeLength[0]=100;
			ySnakeLength[1]=100;
			ySnakeLength[2]=100;
			
		}
		
		
		//border of title image
		g.setColor(Color.white);
		g.drawRect(24,10,851,55);
		
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//border of gameplay
		g.setColor(Color.white);
		g.drawRect(24,74,851,577);
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
		//draw the scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Scores : "+score,780, 30);
		
		g.drawString("Length : "+snakeLength,780, 50);
		

		rightMouthIcon=new ImageIcon("rightmouth.png");
		rightMouthIcon.paintIcon(this,g,xSnakeLength[0],ySnakeLength[0]);
		
		for(int a=0;a<snakeLength;a++)
		{
			if(a==0 && rightDirection)
			{
				rightMouthIcon=new ImageIcon("rightmouth.png");
				rightMouthIcon.paintIcon(this,g,xSnakeLength[a],ySnakeLength[a]);
			}
			if(a==0 && leftDirection)
			{
				leftMouthIcon=new ImageIcon("leftmouth.png");
				leftMouthIcon.paintIcon(this,g,xSnakeLength[a],ySnakeLength[a]);
			}
			if(a==0 && upDirection)
			{
				upMouthIcon=new ImageIcon("upmouth.png");
				upMouthIcon.paintIcon(this,g,xSnakeLength[a],ySnakeLength[a]);
			}
			if(a==0 && downDirection)
			{
				downMouthIcon=new ImageIcon("downmouth.png");
				downMouthIcon.paintIcon(this,g,xSnakeLength[a],ySnakeLength[a]);
			}
			
			if(a!=0)
			{
				snakeIcon=new ImageIcon("snakeimage.png");
				snakeIcon.paintIcon(this,g,xSnakeLength[a],ySnakeLength[a]);
			}
		}
		
		enemyIcon = new ImageIcon("enemy.png");
		
		if(xEnemyPos[xEnemyPosIndex]==xSnakeLength[0] && yEnemyPos[yEnemyPosIndex]==ySnakeLength[0])
		{
			snakeLength++;
			score++;
			xEnemyPosIndex = random.nextInt(34);
			yEnemyPosIndex = random.nextInt(23);
		}
		enemyIcon.paintIcon(this, g, xEnemyPos[xEnemyPosIndex], yEnemyPos[yEnemyPosIndex]);
		
		for(int b=1;b<snakeLength;b++)
		{
			if(xSnakeLength[b]==xSnakeLength[0] && ySnakeLength[b]==ySnakeLength[0])
			{
				rightDirection=false;
				leftDirection=false;
				upDirection=false;
				downDirection=false;
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game Over!", 300, 300);
				
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Space to RESTART", 350, 340);
				
			}
		}
		
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(rightDirection)
		{
			for(int i=snakeLength-1;i>=0;i--)
			{
				ySnakeLength[i+1]=ySnakeLength[i];
			}
			for(int i=snakeLength;i>=0;i--)
			{
				if(i==0)
				{
					xSnakeLength[i]=xSnakeLength[i]+25;
				}
				else
				{
					xSnakeLength[i]=xSnakeLength[i-1];
				}
				if(xSnakeLength[i]>850)
				{
					xSnakeLength[i]=25;
				}
			}
			
			repaint();
		}
		if(leftDirection)
		{
			for(int i=snakeLength-1;i>=0;i--)
			{
				ySnakeLength[i+1]=ySnakeLength[i];
			}
			for(int i=snakeLength;i>=0;i--)
			{
				if(i==0)
				{
					xSnakeLength[i]=xSnakeLength[i]-25;
				}
				else
				{
					xSnakeLength[i]=xSnakeLength[i-1];
				}
				if(xSnakeLength[i]<25)
				{
					xSnakeLength[i]=850;
				}
			}
			
			repaint();
		}
		
		if(upDirection)
		{
			for(int i=snakeLength-1;i>=0;i--)
			{
				xSnakeLength[i+1]=xSnakeLength[i];
			}
			for(int i=snakeLength;i>=0;i--)
			{
				if(i==0)
				{
					ySnakeLength[i]=ySnakeLength[i]-25;
				}
				else
				{
					ySnakeLength[i]=ySnakeLength[i-1];
				}
				if(ySnakeLength[i]<75)
				{
					ySnakeLength[i]=625;
				}
			}
			
			repaint();
		}
		if(downDirection)
		{
			for(int i=snakeLength-1;i>=0;i--)
			{
				xSnakeLength[i+1]=xSnakeLength[i];
			}
			for(int i=snakeLength;i>=0;i--)
			{
				if(i==0)
				{
					ySnakeLength[i]=ySnakeLength[i]+25;
				}
				else
				{
					ySnakeLength[i]=ySnakeLength[i-1];
				}
				if(ySnakeLength[i]>625)
				{
					ySnakeLength[i]=75;
				}
			}
			
			repaint();
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			moves++;
			if(!leftDirection)
			{
				rightDirection=true;
			}
			else
			{
				rightDirection=false;
				leftDirection=true;
			}
			upDirection=false;
			downDirection=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			moves++;
			if(!rightDirection)
			{
				leftDirection=true;
			}
			else
			{
				leftDirection=false;
				rightDirection=true;
			}
			upDirection=false;
			downDirection=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			moves++;
			if(!downDirection)
			{
				upDirection=true;
			}
			else
			{
				upDirection=false;
				downDirection=true;
			}
			leftDirection=false;
			rightDirection=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			moves++;
			if(!upDirection)
			{
				downDirection=true;
			}
			else
			{
				downDirection=false;
				upDirection=true;
			}
			leftDirection=false;
			rightDirection=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			score=0;
			moves=0;
			snakeLength=3;
			repaint();
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
