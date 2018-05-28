package keithmulqueen.cardmyidentity;

import java.awt.Graphics;


public class well extends staticEntity 
{
	public well(handler passed_handler, float x, float y) 
	{
		super(passed_handler, x, y, tile.TILEWIDTH * 2, tile.TILEHEIGHT * 1);
		
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
		g.drawImage(assets.well, (int) (x - myHandler.getGameCamera().getxOffset()), (int) (y - myHandler.getGameCamera().getyOffset()), width, height, null);
	}

}