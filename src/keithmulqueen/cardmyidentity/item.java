package keithmulqueen.cardmyidentity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class item 
{
	
	public static final int ITEMWIDTH = 32;
	public static final int ITEMHEIGHT = 32;
	
	protected handler myHandler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	
	protected Rectangle bounds;
	
	protected int x, y, count;
	protected boolean pickedUp = false;
	protected boolean reusable = false;
	
	
	public static item[] items = new item[256];
	
	//Singular Items
	
	public static item gunItem = 			new item(assets.gun, "Gun", 0, true);
	public static item pickaxeItem = 		new item(assets.pickaxe, "PickAxe", 1, true);
	public static item petalsItem = 		new item(assets.petals, "Flower Petals", 2, false);
	public static item coalItem = 			new item(assets.coal, "Coal", 3, false);
	public static item blobItem = 			new item(assets.blob_mind, "Blob Mind", 4, false);
	public static item shovellItem = 		new item(assets.shovel, "Shovel", 5, true);
	public static item wheelItem = 			new item(assets.wheel, "Wheel", 6, false);
	public static item metalItem = 			new item(assets.metal, "Metal", 7, false);
	public static item woodItem = 			new item(assets.wood, "Wood", 8, false);
	public static item rockItem = 			new item(assets.stone, "Rock", 9, false);
	public static item glassItem = 			new item(assets.glass, "Glass", 10, false);
	public static item fruitItem =			new item(assets.berry, "Berries", 11, false);
	
	
	public static item[] seeds = new item[] { 
			new item(assets.seed[0], "Mars Seed", 12, false), 
			new item(assets.seed[1], "Uranus Seed", 13, false), 
			new item(assets.seed[2], "Mercury Seed", 14, false),
			new item(assets.seed[3], "Neptune Seed", 15, false),
			new item(assets.seed[4], "Saturn Seed", 16, false),
			new item(assets.seed[5], "Venus Seed", 17, false)};
	
	public static item[] buckets = new item[] {
			new item(assets.bucket[0], "Bucket", 18, false), 
			new item(assets.bucket[1], "Bucket of Water", 19, false), 
			new item(assets.bucket[2], "Bucket of Oil", 20, false),
			new item(assets.bucket[2], "Bucket of Sand", 21, false)};
	
	public static item[] minerals = new item[] { 
			new item(assets.pearl[0], "Skalium", 22, false), 
			new item(assets.pearl[1], "Hystium", 23, false), 
			new item(assets.pearl[2], "Latnium", 24, false),
			new item(assets.pearl[3], "Xynium", 25, false),
			new item(assets.pearl[4], "Neutrium", 26, false),
			new item(assets.pearl[5], "Citrium", 27, false)};
	
	public static item[] beakers = new item[] {
			new item(assets.beaker[0], "Flask", 28, false), 
			new item(assets.beaker[1], "Flask of Acid", 29, false), 
			new item(assets.beaker[2], "Flask of Blob", 30, false)};
	
	public static item[] pouches = new item[] {
			new item(assets.pouch[0], "Bronze Pouch", 31, false),
			new item(assets.pouch[1], "Silver Pouch", 32, false),
			new item(assets.pouch[2], "Gold Pouch", 33, false)};

	public static item[] candles = new item[] {
			new item(assets.wax[0], "Wax", 34, false),
			new item(assets.wax[1], "Wax Candle", 35, false)};
	
	
	public item(BufferedImage passed_texture, String name, int id, boolean passed_reusable)
	{
		//Item constructor
		
		this.reusable = passed_reusable;
		
		//Apply the texture
		this.texture = passed_texture;
		
		//Set the name
		this.name = name;
		
		//Each items id number
		this.id = id;
		
		//How many added
		count = 1;
		
		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		
		items[id] = this;
	}
	
	public void tick()
	{
		//If the player collides with the item
		if(myHandler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds))
		{
			
			//Set the item to picked up
			pickedUp = true;
			
			//Add item to the inventory
			myHandler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}
	
	public void render(Graphics g)
	{
		if(myHandler == null)
		{
			return;
		}
			
		render(g, (int) (x - myHandler.getGameCamera().getxOffset()), (int) (y - myHandler.getGameCamera().getyOffset()));
	}
	
	
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}
	
	public item createNew(int count)
	{
		item i = new item(texture, name, id, reusable);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
	}
	
	public item createNew(int x, int y)
	{
		item i = new item(texture, name, id, reusable);
		i.setPosition(x, y);
		return i;
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	
	// Getters and Setters
	
	public handler getHandler() 
	{
		return myHandler;
	}

	public void setPickedUp(boolean pickedUp) 
	{
		//Set the pick up value true or false
		
		this.pickedUp = pickedUp;
	}

	public void setHandler(handler passed_handler) 
	{
		this.myHandler = passed_handler;
	}

	public BufferedImage getTexture() 
	{
		return texture;
	}

	public void setTexture(BufferedImage texture) 
	{
		this.texture = texture;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public int getCount() 
	{
		return count;
	}

	public void setCount(int count) 
	{
		this.count = count;
	}

	public int getId() 
	{
		return id;
	}

	public boolean isPickedUp() 
	{
		return pickedUp;
	}

}