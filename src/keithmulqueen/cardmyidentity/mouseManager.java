package keithmulqueen.cardmyidentity;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class mouseManager implements MouseListener, MouseMotionListener 
{

	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private uiManager myUIManager;
	
	public mouseManager()
	{
		
	}
	
	public void setUIManager(uiManager passed_uiManager)
	{
		this.myUIManager = passed_uiManager;
	}
	
	// Getters
	
	public boolean isLeftPressed()
	{
		return leftPressed;
	}
	
	public boolean isRightPressed()
	{
		return rightPressed;
	}
	
	public int getMouseX()
	{
		return mouseX;
	}
	
	public int getMouseY()
	{
		return mouseY;
	}
	
	// Implemented methods
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			leftPressed = true;
		}
		
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			rightPressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			leftPressed = false;
		}
		
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			rightPressed = false;
		}
		
		if(myUIManager != null)
		{
			myUIManager.onMouseRelease(e);
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(myUIManager != null)
		{
			myUIManager.onMouseMove(e);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

}