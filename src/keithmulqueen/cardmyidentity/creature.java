package keithmulqueen.cardmyidentity;

public abstract class creature extends entity 
{
	
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;
	
	protected float speed;
	protected float xMove, yMove;

	public creature(handler passed_handler, float x, float y, int width, int height) {
		super(passed_handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move()
	{
		if(!checkEntityCollisions(xMove, 0f))
		{
			moveX();
		}
		
		if(!checkEntityCollisions(0f, yMove))
		{
			moveY();
		}
	}
	
	public void moveX()
	{
		//Moving RIGHT
		if(xMove > 0)
		{
			int tx = (int) (x + xMove + bounds.x + bounds.width) / tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / tile.TILEHEIGHT) &&!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / tile.TILEHEIGHT))
			{
				x += xMove;
			}
			
			else
			{
				x = tx * tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}		
		}
		
		
		//Moving LEFT
		else if(xMove < 0)
		{
			int tx = (int) (x + xMove + bounds.x) / tile.TILEWIDTH;
			
			
			//if you dont collide with a solid tile's left side
			if(!collisionWithTile(tx, (int) (y + bounds.y) / tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / tile.TILEHEIGHT))
			{
				x += xMove;
			}
			
			//if its a solid tile
			else
			{
				x = tx * tile.TILEWIDTH + tile.TILEWIDTH - bounds.x;
			}			
		}
	}
	
	public void moveY()
	{
		//Moving UP
		if(yMove < 0)
		{
			int ty = (int) (y + yMove + bounds.y) / tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / tile.TILEWIDTH, ty))
			{
				y += yMove;
			}
			
			else
			{
				y = ty * tile.TILEHEIGHT + tile.TILEHEIGHT - bounds.y;
			}
			
		}
		
		//Moving DOWN
		else if(yMove > 0)
		{
			int ty = (int) (y + yMove + bounds.y + bounds.height) / tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / tile.TILEWIDTH, ty))
			{
				y += yMove;
			}
			
			else
			{
				y = ty * tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}			
		}
	}
	
	protected boolean collisionWithTile(int x, int y)
	{
		return myHandler.getWorld().getTile(x, y).isSolid();
	}
	
	//GETTERS SETTERS

	public float getxMove() 
	{
		return xMove;
	}

	public void setxMove(float xMove) 
	{
		this.xMove = xMove;
	}

	public float getyMove() 
	{
		return yMove;
	}

	public void setyMove(float yMove) 
	{
		this.yMove = yMove;
	}

	public int getHealth() 
	{
		return health;
	}

	public void setHealth(int health) 
	{
		this.health = health;
	}

	public float getSpeed() 
	{
		return speed;
	}

	public void setSpeed(float speed) 
	{
		this.speed = speed;
	}
	
}