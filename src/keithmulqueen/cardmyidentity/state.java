package keithmulqueen.cardmyidentity;

import java.awt.Graphics;

public abstract class state 
{
	private static state currentState = null;
	
	//Get and set the states
	
	public static void setState(state passed_state)
	{
		currentState = passed_state;
	}
	
	public static state getState()
	{
		return currentState;
	}
	
	protected handler myHandler;
	
	//Constructor sets the handler
	
	public state(handler passed_handler)
	{
		this.myHandler = passed_handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}