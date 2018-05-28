package keithmulqueen.cardmyidentity;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class uiManager 
{

	private handler myHandler;
	private ArrayList<uiObject> objects;
	
	public uiManager(handler passed_handler)
	{
		this.myHandler = passed_handler;
		objects = new ArrayList<uiObject>();
	}
	
	public void tick()
	{
		for(uiObject o : objects)
		{
			o.tick();
		}
	}
	
	public void render(Graphics g)
	{
		for(uiObject o : objects)
		{
			o.render(g);
		}
	}
	
	public void onMouseMove(MouseEvent e)
	{
		for(uiObject o : objects)
		{
			o.onMouseMove(e);
		}
	}
	
	public void onMouseRelease(MouseEvent e)
	{
		for(uiObject o : objects)
		{
			o.onMouseRelease(e);
		}
	}
	
	public void addObject(uiObject o)
	{
		objects.add(o);
	}
	
	public void removeObject(uiObject o)
	{
		objects.remove(o);
	}

	public handler getHandler() 
	{
		return myHandler;
	}

	public void setHandler(handler passed_handler) 
	{
		this.myHandler = passed_handler;
	}

	public ArrayList<uiObject> getObjects() 
	{
		return objects;
	}

	public void setObjects(ArrayList<uiObject> objects) 
	{
		this.objects = objects;
	}
	
}