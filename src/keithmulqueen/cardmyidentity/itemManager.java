package keithmulqueen.cardmyidentity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class itemManager 
{
	
	private handler myHandler;
	
	//Create array list of items
	private ArrayList<item> items;
	
	public itemManager(handler passed_handler)
	{
		this.myHandler = passed_handler;
		
		items = new ArrayList<item>();
	}
	
	
	
	public void tick()
	{
		Iterator<item> it = items.iterator();
		
		while(it.hasNext())
		{
			item i = it.next();
			i.tick();
			
			if(i.isPickedUp())
			{
				it.remove();
			}
		}
	}
	
	
	public void render(Graphics g)
	{
		for(item i : items)
		{
			i.render(g);
		}	
	}
	
	public void addItem(item i)
	{
		i.setHandler(myHandler);
		items.add(i);
	}
	
	// Getters and Setters

	public handler getHandler() 
	{
		return myHandler;
	}

	public void setHandler(handler passed_handler) 
	{
		this.myHandler = passed_handler;
	}

}