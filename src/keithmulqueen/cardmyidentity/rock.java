package keithmulqueen.cardmyidentity;

import java.awt.Graphics;


public class rock extends staticEntity 
{
	private int rockType;
	
	private int x;
	private int y;
	
	public rock(handler passed_handler, float passed_x, float passed_y, int rockType) 
	{
		super(passed_handler, passed_x, passed_y, tile.TILEWIDTH, tile.TILEHEIGHT);
		
		this.x = (int) passed_x;
		this.y = (int) passed_y;
		
		this.rockType = rockType;
		bounds.x = 3;
		bounds.y = (int) (height / 2f);
		bounds.width = width - 6;
		bounds.height = (int) (height - height / 2f);
	}

	@Override
	public void tick() 
	{
		
	}
	
	@Override
	public void die()
	{
		//myHandler.getWorld().getItemManager().addItem(item.minerals[rockType].createNew((int) (x + ((tile.TILEWIDTH / 2) - (width / 4))), (int) y + (tile.TILEHEIGHT / 2) - (height / 4)));
		//myHandler.getWorld().getItemManager().addItem(item.rockItem.createNew((int) (x + ((tile.TILEWIDTH / 2) - (width / 4))), (int) y + (tile.TILEHEIGHT / 2) - (height / 4)));
		
		myHandler.getWorld().getEntityManager().addEntity(new hole(myHandler, x, y+80));
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(assets.rock[rockType], (int) (x - myHandler.getGameCamera().getxOffset()), (int) (y - myHandler.getGameCamera().getyOffset()), width, height, null);
	}

}
