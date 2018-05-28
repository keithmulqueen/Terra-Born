package keithmulqueen.cardmyidentity;

import java.awt.Graphics;

import java.util.Random;

public class plant extends staticEntity 
{
	private int plant_type;
	
	public plant(handler passed_handler, float x, float y, int plant_type) 
	{

		super(passed_handler, x, y, assets.plant[plant_type].getTileWidth() * 2, assets.plant[plant_type].getTileHeight() * 2);
		
		this.plant_type = plant_type;
		
		bounds.x = 10;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width - 20;
		bounds.height = (int) (height - height / 1.5f);
	}

	@Override
	public void tick() 
	{

	
	}
	
	@Override
	public void die()
	{
		if(plant_type <= 5) //Evergreen
		{
			myHandler.getWorld().getItemManager().addItem(item.seeds[plant_type].createNew((int) (x + ((tile.TILEWIDTH / 2) - (width / 4))), (int) y + (tile.TILEHEIGHT / 2) - (height / 4)));
		}
		else
		{
			Random rando = new Random();
			
			myHandler.getWorld().getItemManager().addItem(item.seeds[rando.nextInt(5)].createNew((int) (x + ((tile.TILEWIDTH / 2) - (width / 4))), (int) y + (tile.TILEHEIGHT / 2) - (height / 4)));
			myHandler.getWorld().getItemManager().addItem(item.seeds[plant_type-1].createNew((int) (x + ((tile.TILEWIDTH / 2) - (width / 4))), (int) y + (tile.TILEHEIGHT / 2) - (height / 4)));
			
			
		}	
	}

	@Override
	public void render(Graphics g) 
	{
		
		g.drawImage(assets.plant[plant_type], (int) (x - myHandler.getGameCamera().getxOffset()), (int) (y - myHandler.getGameCamera().getyOffset()), width, height, null);
	}

}