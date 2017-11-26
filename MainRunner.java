import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;
public class MainRunner extends JFrame implements KeyListener,ActionListener{

	protected Timer gameTimer;
	protected int frameCount;
	protected WindowBlock[] runningBlock;
	protected WindowBlock[][] blockArray;
	protected int frameSpeed=50;
	protected int frameSpeedReference=50;
	protected int xSize = 140;
	protected int ySize = 140;
	public MainRunner()
	{
		System.out.println("(Head of WindowTetris) Runned Sucessfully!");
		gameTimer = new Timer(15,this);
		runningBlock = new WindowBlock[4];
		blockArray = new WindowBlock[8][10];
		
		newShape();
		gameTimer.start();
	}
	public void newShape()
	{
		frameSpeed = frameSpeedReference;
		int shapeNumber = (int)(Math.random() * 4) + 1;
		if(shapeNumber == 1)
		{
		runningBlock[1] = new WindowBlock(720,140,Color.RED,false,this);
		runningBlock[2] = new WindowBlock(440,140,Color.RED,false,this);
		runningBlock[3] = new WindowBlock(580,0,Color.RED,false,this);
		runningBlock[0] = new WindowBlock(580,140,Color.RED,true,this);
		}
		else if(shapeNumber == 2)
		{
			runningBlock[1] = new WindowBlock(440,140,Color.GREEN,false,this);
			runningBlock[2] = new WindowBlock(580,140,Color.GREEN,false,this);
			runningBlock[3] = new WindowBlock(720,0,Color.GREEN,false,this);
			runningBlock[0] = new WindowBlock(580,0,Color.GREEN,true,this);
		}
		else if(shapeNumber == 3)
		{
			runningBlock[1] = new WindowBlock(300,0,Color.BLUE,false,this);
			runningBlock[2] = new WindowBlock(580,0,Color.BLUE,false,this);
			runningBlock[3] = new WindowBlock(720,0,Color.BLUE,false,this);
			runningBlock[0] = new WindowBlock(440,0,Color.BLUE,true,this);
	
		}
		else if(shapeNumber == 4)
		{
			runningBlock[1] = new WindowBlock(440,0,Color.YELLOW,false,this);
			runningBlock[2] = new WindowBlock(580,0,Color.YELLOW,false,this);
			runningBlock[3] = new WindowBlock(440,140,Color.YELLOW,false,this);
			runningBlock[0] = new WindowBlock(580,140,Color.YELLOW,true,this);
	
		}
		
		
	}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == gameTimer)
		{
			nextFrame();
		}
		
	}
	public void nextFrame()
	{
		repaint();
		requestFocus();
		frameCount++;
		if(frameCount >= frameSpeed)
		{
			frameCount = 0;
			
			tryToMoveShape(1);
			
		}
	}
	public void clearRow(int row)
	{
		System.out.println("yeh clear");
		for(int a = 0; a < 8; a++)
		{
			blockArray[a][row].dispose();
	
			blockArray[a][row] = null;
		}
		for(int a = row; a > 0; a--)
		{
			for(int b = 0; b < 8; b++)
			{
				blockArray[b][a] = blockArray[b][a-1];
				if(blockArray[b][a] !=null)
					blockArray[b][a].move(1);
			}
		}
	}
	public void checkRow(int row)
	{
		boolean mustClear = true;
		for(int a = 0; a < 8; a++)
		{
			
			if(blockArray[a][row] ==null)
				mustClear = false;
			
		}
		if(mustClear)
			clearRow(row);
	}
	public void tryToMoveShape(int direction)
	{
			boolean canMove = true;
			boolean dontMove = false;
			for(int a = 0; a < 4;a++)
			{
				int shapex = (int)runningBlock[a].getLocation().getX() /140;
				int shapey = (int)runningBlock[a].getLocation().getY() /140;
				
				if(direction == 1)
				{
					if(shapey +1 >=8 || blockArray[shapex][shapey+1] != null)
					{
						canMove = false;
					}
				}
				else if(direction == -1)
				{
					if(shapex - 1 < 0 || blockArray[shapex-1][shapey] !=null)
					{
						dontMove = true;
					}
				}
				else if(direction == 2)
				{
					if(shapex + 1 >=8 || blockArray[shapex+1][shapey]!=null)
					{
						dontMove = true;
					}
				}
				else if(direction == 10)
				{
					int relativeX = (int)runningBlock[0].getLocation().getX() / 140 - shapex;
					int relativeY = (int)runningBlock[0].getLocation().getY() / 140 - shapey;
					
					if(blockArray[relativeY +(int)runningBlock[0].getLocation().getX() / 140][(int)runningBlock[0].getLocation().getY() / 140-relativeY] !=null)
					{
						dontMove = true;
					}
				}
			}
			if(direction == 10 && dontMove == false)
			{
				
				for(int a = 0; a < 4; a++)
				{
					int shapex = (int)runningBlock[a].getLocation().getX() /140;
					int shapey = (int)runningBlock[a].getLocation().getY() /140;
				
					int relativeX = -(int)runningBlock[0].getLocation().getX() / 140 + shapex;
					int relativeY = -(int)runningBlock[0].getLocation().getY() / 140 + shapey;
					runningBlock[a].setLocation(relativeY*140 + (int)runningBlock[0].getLocation().getX(),(int)runningBlock[0].getLocation().getY()  -relativeX*140);

						
				}
			}
			if(canMove == false)
			{
				int yCount[] = {111,111,111,111};
				for(int a = 0; a < 4; a++)
				{
					int shapex = (int)runningBlock[a].getLocation().getX() / 140;
					int shapey = (int)runningBlock[a].getLocation().getY() / 140;
					blockArray[shapex][shapey] = runningBlock[a];
					boolean willCheckRow = true;
				
					yCount[a] = shapey;
									
				}	
				
				for(int b = 0; b < 4; b++)
				{
					int lowest = 100;
					int counter=0;
					for(int a = 0; a < 4; a++)
					{
						if(yCount[a] < lowest&& yCount[a]!= 1000)
						{
						lowest = yCount[a];
						counter = a;
						}
					}
					yCount[counter] = 1000;
					checkRow(lowest);
				}
				newShape();			
			}
			else if(dontMove == false)
			{
				for(int a = 0; a < 4; a++)
				{
					runningBlock[a].move(direction);
				}
	}
			}
	public void keyPressed(KeyEvent arg0) {
		System.out.println("huh");
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
		{
			tryToMoveShape(-1);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			tryToMoveShape(2);
		}
		
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}
}
