package keithmulqueen.cardmyidentity;

public class gameCamera 
{
	private handler myHandler;
	private float xOffset, yOffset;
	
	public gameCamera(handler passed_handler, float xOffset, float yOffset)
	{
		this.myHandler = passed_handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void checkBlankSpace()
	{
		if(xOffset < 0)
		{
			xOffset = 0;
		}		
		else if(xOffset > myHandler.getWorld().getWidth() * tile.TILEWIDTH - myHandler.getWidth())
		{
			xOffset = myHandler.getWorld().getWidth() * tile.TILEWIDTH - myHandler.getWidth();
		}
		
		
		if(yOffset < 0)
		{
			yOffset = 0;
		}
		else if(yOffset > myHandler.getWorld().getHeight() * tile.TILEHEIGHT - myHandler.getHeight())
		{
			yOffset = myHandler.getWorld().getHeight() * tile.TILEHEIGHT - myHandler.getHeight();
		}
	}
	
	public void centerOnEntity(entity e)
	{
		xOffset = e.getX() - myHandler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - myHandler.getHeight() / 2 + e.getHeight() / 2;
		
		checkBlankSpace();
	}
	
	public void move(float xAmt, float yAmt)
	{
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	public float getxOffset() 
	{
		return xOffset;
	}

	public void setxOffset(float passed_xOffset) 
	{
		this.xOffset = passed_xOffset;
	}

	public float getyOffset() 
	{
		return yOffset;
	}

	public void setyOffset(float passed_yOffset) 
	{
		this.yOffset = passed_yOffset;
	}
}