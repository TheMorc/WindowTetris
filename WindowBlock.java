import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class WindowBlock extends JFrame implements KeyListener{
	
	int xLoc;
	int yLoc;
	String blockColor;
	boolean listenToKey;
	MainRunner gameWindow;
	@SuppressWarnings("deprecation")
	public WindowBlock(int x, int y, Color color,boolean listen,MainRunner w)
	{
		System.out.println("(Tetris Window) Initialized Sucessfully! Color:" + color);
		gameWindow = w;
		xLoc = x;
		yLoc = y;
		ImageIcon icn = new ImageIcon("icon.png");
		this.setIconImage(icn.getImage());
		this.setLocation(x,y);
		setSize(140,140);
		listenToKey=listen;
		this.getContentPane().setBackground(color);
		if(listenToKey)
		{
			addKeyListener(this);
			requestFocus();
		}
		show();
	}
	public void move(int direction)
	{
		if(direction == 1)
		{
			
			setLocation(getLocation().x,getLocation().y+140);
		}
		else if(direction == -1)
		{
			setLocation(getLocation().x - 140,getLocation().y);
		}
		else if(direction == 2)
		{
			setLocation(getLocation().x+140,getLocation().y);
			
		}
		else if(direction == 10)
		{
		}
	}
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
		{
			gameWindow.tryToMoveShape(-1);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			gameWindow.tryToMoveShape(2);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_A)
		{
			gameWindow.tryToMoveShape(-1);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_D)
		{
			gameWindow.tryToMoveShape(2);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_W)
		{
			gameWindow.tryToMoveShape(10);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_S)
		{
			gameWindow.tryToMoveShape(10);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
		{
			gameWindow.frameSpeed = 0;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
		{
			gameWindow.tryToMoveShape(10);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_UP)
		{
			gameWindow.tryToMoveShape(10);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
		{
			gameWindow.tryToMoveShape(10);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			System.exit(10);
		}
		
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}
}
