package keithmulqueen.cardmyidentity;

import java.awt.Graphics;


public class survivor extends staticEntity 
{
	// 0 = DEAD
	// 1 = SICK
	// 2 = HEALTHY
		
	private int status;
	
	public survivor(handler passed_handler, float x, float y, int status) 
	{
		super(passed_handler, x, y, tile.TILEWIDTH, tile.TILEHEIGHT);
		
		
		this.status = status;
		
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

		g.drawImage(assets.survivor[status], (int) (x - myHandler.getGameCamera().getxOffset()), (int) (y - myHandler.getGameCamera().getyOffset()), width, height, null);

	}
	
	public void setHealthy()
	{
		this.status = 3;
	}

}
