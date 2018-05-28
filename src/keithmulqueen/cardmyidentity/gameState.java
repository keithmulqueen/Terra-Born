package keithmulqueen.cardmyidentity;

import java.awt.Graphics;

public class gameState extends state 
{
	
	private world world1;
	
	public gameState(handler myHandler)
	{
		super(myHandler);
		
		world1 = new world(myHandler, "res/worlds/world1.txt");
		
		myHandler.setWorld(world1);
	}
	
	@Override
	public void tick() 
	{
		world1.tick();
	}

	@Override
	public void render(Graphics g) 
	{
		world1.render(g);
	}

}