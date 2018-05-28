package keithmulqueen.cardmyidentity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class uiImageButton extends uiObject 
{

	private BufferedImage[] images;
	private clickListener clicker;
	
	public uiImageButton(float x, float y, int width, int height, BufferedImage[] passed_images, clickListener passed_clicker) 
	{
		super(x, y, width, height);
		this.images = passed_images;
		this.clicker = passed_clicker;
	}

	@Override
	public void tick() 
	{
		
	}

	@Override
	public void render(Graphics g) 
	{
		if(hovering)
		{
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		}
		
		else
		{
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
		}
	}

	@Override
	public void onClick() 
	{
		clicker.onClick();
	}

}