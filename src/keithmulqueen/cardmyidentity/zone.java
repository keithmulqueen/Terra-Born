package keithmulqueen.cardmyidentity;

import java.awt.Graphics;

public class zone extends staticEntity 
{
	private int zoneType;
	
	
	public zone(handler passed_handler, float x, float y, int zoneType) 
	{
		super(passed_handler, x, y, tile.TILEWIDTH, tile.TILEHEIGHT);
		
		this.zoneType = zoneType;
		
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
		g.drawImage(assets.zone[zoneType], (int) (x - myHandler.getGameCamera().getxOffset()), (int) (y - myHandler.getGameCamera().getyOffset()), width, height, null);
	}

}