package keithmulqueen.cardmyidentity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class inventory 
{

	private handler myHandler;
	private boolean active = false;
	private ArrayList<item> inventoryItems;
	
	private int invX = 64;
	private int invY = 48;
	private int invWidth = 512;
	private int invHeight = 384;
	private int invListCenterX = invX + 171;
	private int	invListCenterY = invY + invHeight / 2 + 5;
	private int  invListSpacing = 30;

	private int invImageX = 452;
	private int invImageY = 82;
	private int invImageWidth = 64; 
	private int invImageHeight = 64;
	
	private int invCountX = 484; 
	private int invCountY = 172;
	
	private int selectedItem = 0;
	
	public inventory(handler passed_handler)
	{
		this.myHandler = passed_handler;
		
		inventoryItems = new ArrayList<item>();
	}
	
	public void tick()
	{
		//switch between on and off
		
		if(myHandler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
		{
			active = !active;
		}
		
		if(!active)
		{
			return;
		}
		
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
	}
	
	public void render(Graphics g)
	{
		if(!active)
		{
			return;
		}
		
		g.drawImage(assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
		
		
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
		
		text.drawString(g, Integer.toString(myItem.getCount()), invCountX, invCountY, true, Color.WHITE, assets.font28);
	}
	
	// Inventory methods
	
	public void addItem(item passed_item)
	{
		for(item i : inventoryItems)
		{
			if(i.getId() == passed_item.getId())
			{
				i.setCount(i.getCount() + passed_item.getCount());
				return;
			}
		}
		
		inventoryItems.add(passed_item);
	}
	
	// GETTERS SETTERS

	public handler getHandler() 
	{
		return myHandler;
	}

	public void setHandler(handler passed_handler) {
		this.myHandler = passed_handler;
	}

	public boolean isActive() 
	{
		return active;
	}
	
}