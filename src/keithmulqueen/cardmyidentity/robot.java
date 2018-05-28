package keithmulqueen.cardmyidentity;

import java.awt.Graphics;

public class robot extends staticEntity 
{
	
	private boolean broken;
	
	public robot(handler passed_handler, float x, float y) 
	{
		super(passed_handler, x, y, tile.TILEWIDTH, tile.TILEHEIGHT);
		
		
		this.broken = true;
		
		bounds.x = 10;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width - 20;
		bounds.height = (int) (height - height / 1.5f);
		
		breakable = false;
	}

	@Override
	public void tick() 
	{
		
	}
	
	@Override
	public void die()
	{
		myHandler.getWorld().getItemManager().addItem(item.woodItem.createNew((int) x, (int) y));
	}

	@Override
	public void render(Graphics g) 
	{
		if(broken)
		{
			g.drawImage(assets.robot[0], (int) (x - myHandler.getGameCamera().getxOffset()), (int) (y - myHandler.getGameCamera().getyOffset()), width, height, null);
		}
		else
		{
			g.drawImage(assets.robot[1], (int) (x - myHandler.getGameCamera().getxOffset()), (int) (y - myHandler.getGameCamera().getyOffset()), width, height, null);
		}
		
	}
	
	public void setBroken()
	{
		this.broken = false;
	}
	
	@Override
	public void entityAction()
	{
		myHandler.getWorld().getItemManager().addItem(item.woodItem.createNew((int) x, (int) y));
	}

}
