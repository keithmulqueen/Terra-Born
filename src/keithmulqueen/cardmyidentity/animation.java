package keithmulqueen.cardmyidentity;

import java.awt.image.BufferedImage;

public class animation 
{	
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public animation(int passed_speed, BufferedImage[] frames)
	{
		this.speed = passed_speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick()
	{
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed)
		{
			index++;
			timer = 0;
			
			if(index >= frames.length)
			{
				index = 0;
			}	
		}
	}
	
	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}

}
