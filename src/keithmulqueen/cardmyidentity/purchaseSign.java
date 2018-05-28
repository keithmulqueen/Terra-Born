package keithmulqueen.cardmyidentity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class purchaseSign extends staticEntity 
{
	private String message;
	private String[] requirements = new String[19];
	private int[] amountRequired;
	

	private int invWidth = 500;
	private int invHeight = 300;
	private int invX = (1120/2) - (invWidth/2);
	private int invY = (480/2) - (invHeight/2);
	private int invListCenterX = invX + 171;
	private int	invListCenterY = invY + invHeight / 2 + 5;
	private int  invListSpacing = 30;
	private boolean active;

	private int invImageX = 452;
	private int invImageY = 82;
	private int invImageWidth = 64; 
	private int invImageHeight = 64;
	
	private int invCountX = 484; 
	private int invCountY = 172;
	
	private int selectedItem = 0;
	
	public purchaseSign(handler passed_handler, float x, float y, String passed_message, int[] passed_amountRequired) 
	{
		super(passed_handler, x, y, tile.TILEWIDTH, tile.TILEHEIGHT);
		
		active = false;
		breakable = false;
		
		this.amountRequired = passed_amountRequired;

		requirements = new String[] {		
				"Gun", "PickAxe", "Flower Petals", "Coal", "Blob Mind", "Shovel","Wheel", "Metal", "Wood", "Rock", "Glass", "Parcel", 
				"Mars Seed", "Uranus Seed", "Mercury Seed", "Neptune Seed", "Saturn Seed", "Venus Seed", 
				"Bucket", "Bucket of Water", "Bucket of Oil", "Bucket of Sand", 
				"Skalium", "Kinglium", "Dazzium", "Shinium", "Shinium", "Citrium", 
				"Flask", "Flask of Acid", "Flask of Blob", "Bronze Pouch", "Silver Pouch", "Gold Pouch", "Wax", "Wax Candle"
				};
	
		
		this.message = passed_message;
		
		bounds.x = 10;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width - 20;
		bounds.height = (int) (height - height / 1.5f);
	}

	@Override
	public void tick() 
	{
		if(!active)
		{
			return;
		}
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		
		int arSize = 20;
		
		ar.width = arSize;
		ar.height = arSize;
		
		//Player Movement
		
		
		ar.x = cb.x + cb.width / 2 - arSize / 2;
		ar.y = cb.y - arSize;


		
		if((myHandler.getKeyManager().keyJustPressed(KeyEvent.VK_R)) && (myHandler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(ar)))
		{
			active = !active;
		}
	
		
		/*
		//scroll up the list
		if(myHandler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
		{
			selectedItem--;
		}
		
		//scroll down the list
		if(myHandler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
		{
			selectedItem++;
		}
		
		if(selectedItem < 0)
		{
			selectedItem = inventoryItems.size() - 1;
		}
		else if(selectedItem >= inventoryItems.size())
		{
			selectedItem = 0;
		}
		*/
	}
	
	@Override
	public void die()
	{
		myHandler.getWorld().getItemManager().addItem(item.woodItem.createNew((int) x, (int) y));
		System.out.println(message + requirements[1] + amountRequired[1]);
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(assets.sign, (int) (x - myHandler.getGameCamera().getxOffset()), (int) (y - myHandler.getGameCamera().getyOffset()), width, height, null);
			
		if(!active)
		{
			return;
		}
		else
		{
			g.drawImage(assets.signScreen, invX, invY, invWidth, invHeight, null);
			
			/*
			
			int len = inventoryItems.size();
			
			if(len == 0)
			{
				return;
			}

			for(int i = -5;i < 6;i++)
			{
				if(selectedItem + i < 0 || selectedItem + i >= len)
				{
					continue;
				}
				
				if(i == 0)
				{
					text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, invListCenterY + i * invListSpacing, true, Color.YELLOW, assets.font28);
				}
				
				else
				{
					text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpacing, true, Color.WHITE, assets.font28);
				}
			}
			
			item myItem = inventoryItems.get(selectedItem);
			
			
			g.drawImage(myItem.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
			
			
			private final String happy = "I am happy";
			
			text.drawString(g, Integer.toString(myItem.getCount()), invCountX, invCountY, true, Color.WHITE, assets.font28);
			*/
		}
		
		
	}

	@Override
	public String getMessage()
	{
		for(int i = 0; i < amountRequired.length; i++)
		{
			if(amountRequired[i] > 0)
			{
				message = message + "%n" + requirements[i] + ": " + amountRequired[i];
			}
		}
		
		return message;
	}
	
}