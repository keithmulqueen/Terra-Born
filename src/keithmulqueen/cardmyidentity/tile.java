package keithmulqueen.cardmyidentity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class tile 
{
	
	//STATIC STUFF HERE
	
	public static tile[] tiles = new tile[256];
	
	public static tile floorTileA 		= new lowTile(0);
	public static tile floorTileB 		= new lowTile(1);
	public static tile pavementTile 	= new lowTile(2);
	
	public static tile rockTile 		= new highTile(3);
	public static tile rockTile_lower 	= new highTile(4);
	
	public static tile seaTile 			= new seaTile(5);
	public static tile seaTile_north 	= new seaTile(6);
	public static tile seaTile_south 	= new seaTile(7);
	public static tile seaTile_east 	= new seaTile(8);
	public static tile seaTile_west 	= new seaTile(9);
	public static tile seaTile_northw	= new seaTile(10);
	public static tile seaTile_northe 	= new seaTile(11);
	public static tile seaTile_southw 	= new seaTile(12);
	public static tile seaTile_southe 	= new seaTile(13);
	public static tile puddle		 	= new seaTile(14);
	
	
	//CLASS
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid()
	{
		return false;
	}
	
	public int getId()
	{
		return id;
	}
	
}