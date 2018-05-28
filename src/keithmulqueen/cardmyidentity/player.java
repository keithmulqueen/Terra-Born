package keithmulqueen.cardmyidentity;

import java.awt.Graphics;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class player extends creature 
{
	
	//Animations
	private animation animDown, animUp, animLeft, animRight;
	private BufferedImage stillDown, stillUp, stillLeft, stillRight;
	
	private int playerDirection = 0;
	
	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	
	//Read timer
	private long lastReadTimer, readCooldown = 800, readTimer = readCooldown;
	
	// Inventory
	private inventory myInventory;
	
	public player(handler passed_handler, float x, float y) 
	{
		super(passed_handler, x, y, creature.DEFAULT_CREATURE_WIDTH, creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;
		
		//Animations
		animDown = new animation(500, assets.player_down);
		animUp = new animation(500, assets.player_up);
		animLeft = new animation(500, assets.player_left);
		animRight = new animation(500, assets.player_right);
		
		//Relaxed Character
		stillDown = assets.player_down_still;
		stillUp = assets.player_up_still;
		stillLeft = assets.player_left_still;
		stillRight = assets.player_right_still;
		
		
		myInventory = new inventory(myHandler);
	}

	@Override
	public void tick() 
	{
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		
		//Movement
		getInput();
		move();
		myHandler.getGameCamera().centerOnEntity(this);
		
		// Attack
		checkAttacks();
		
		//Action
		playerAction();
		
		// Inventory
		myInventory.tick();
	}
	
	private void checkAttacks()
	{
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if(attackTimer < attackCooldown)
		{
			return;
		}
		
		if(myInventory.isActive())
		{
			return;
		}

		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		
		int arSize = 20;
		
		ar.width = arSize;
		ar.height = arSize;
		
		//Player Movement
		
		if(myHandler.getKeyManager().aUp)
		{
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}
		else if(myHandler.getKeyManager().aDown)
		{
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}
		else if(myHandler.getKeyManager().aLeft)
		{
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else if(myHandler.getKeyManager().aRight)
		{
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else
		{
			return;
		}
		
		attackTimer = 0;
		
		for(entity e : myHandler.getWorld().getEntityManager().getEntities())
		{
			if(e.equals(this))
			{
				continue;
			}
			
			if((e.getCollisionBounds(0, 0).intersects(ar)) && e.breakable)
			{
				e.hurt(1);
				return;
			}
		}
		
	}
	
	private void playerAction()
	{
		readTimer += System.currentTimeMillis() - lastReadTimer;
		lastReadTimer = System.currentTimeMillis();
		
		if(readTimer < attackCooldown)
		{
			return;
		}
		
		if(myInventory.isActive())
		{
			return;
		}

		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		
		int arSize = 20;
		
		ar.width = arSize;
		ar.height = arSize;
		
		//Player Movement
		
		if(myHandler.getKeyManager().readR)
		{
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}
		else
		{
			return;
		}
		
		readTimer = 0;
		
		for(entity e : myHandler.getWorld().getEntityManager().getEntities())
		{
			if(e.equals(this))
			{
				continue;
			}
			
			if(e.getCollisionBounds(0, 0).intersects(ar))
			{
				e.entityAction();
				System.out.println(e.getMessage());
				return;
			}
		}
		
	}
	
	@Override
	public void die()
	{
		System.out.println("You lose");
	}
	
	private void getInput()
	{
		xMove = 0;
		yMove = 0;

		if(myInventory.isActive())
		{
			return;
		}
		
		if(myHandler.getKeyManager().down)
		{
			yMove = speed;
			//animation down
			playerDirection = 0;
		}
		
		if(myHandler.getKeyManager().up)
		{
			yMove = -speed;
			//animation up
			playerDirection = 1;
		}
		
		if(myHandler.getKeyManager().left)
		{
			xMove = -speed;
			//animation left
			playerDirection = 2;
		}
		
		if(myHandler.getKeyManager().right)
		{
			xMove = speed;
			//animation right
			playerDirection = 3;
		}
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(getCurrentAnimationFrame(), (int) (x - myHandler.getGameCamera().getxOffset()), (int) (y - myHandler.getGameCamera().getyOffset()), width, height, null);
	}
	
	public void postRender(Graphics g)
	{
		myInventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame()
	{
		if(xMove < 0)
		{
			return animLeft.getCurrentFrame();
			//return stillLeft;
		}
		else if(xMove > 0)
		{
			return animRight.getCurrentFrame();
			//return stillRight;
		}
		else if(yMove < 0)
		{
			return animUp.getCurrentFrame();
			//return stillUp;
		}
		else if(yMove > 0)
		{
			return animDown.getCurrentFrame();
			//return stillDown;
		}
		else
		{
			if(playerDirection == 1)
			{
				return stillUp;
			}
			else if(playerDirection == 2)
			{
				return stillLeft;
			}
			else if(playerDirection == 3)
			{
				return stillRight;
			}
			else
			{
				return stillDown;
			}
		}
	}

	public inventory getInventory() 
	{
		return myInventory;
	}

	public void setInventory(inventory passed_inventory) 
	{
		this.myInventory = passed_inventory;
	}

}