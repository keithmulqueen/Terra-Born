package keithmulqueen.cardmyidentity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class menuState extends state 
{
	
	private uiManager myUIManager;

	public menuState(handler myHandler) 
	{
		super(myHandler);
			
		myUIManager = new uiManager(myHandler);
			
		myUIManager.addObject(new uiImageButton(200, 200, 128, 64, assets.beaker, new clickListener() {

			@Override
			public void onClick() 
			{
				// TODO Auto-generated method stub
				
				state.setState(myHandler.getGame().gameState);
				
			}}));
		
	//	myHandler.getMouseManager().setUIManager(myUIManager);

		
	}
		
	@Override
	public void tick() 
	{
		myUIManager.tick();
		
		// Temporarily just go directly to the GameState, skip the menu state!
		myHandler.getMouseManager().setUIManager(null);
		state.setState(myHandler.getGame().gameState);
	}

	@Override
	public void render(Graphics g) 
	{
		myUIManager.render(g);
	}
}
