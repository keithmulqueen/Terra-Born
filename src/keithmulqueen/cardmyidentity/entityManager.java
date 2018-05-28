package keithmulqueen.cardmyidentity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class entityManager 
{	
	private handler myHandler;
	private player myPlayer;
	private ArrayList<entity> entities;
	
	private Comparator<entity> renderSorter = new Comparator<entity>()
	{
		@Override
		public int compare(entity a, entity b) 
		{
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
			{
				return -1;
			}
			
			return 1;
		}
	};
	
	public entityManager(handler passed_handler, player passed_player)
	{
		this.myHandler = passed_handler;
		this.myPlayer = passed_player;
		entities = new ArrayList<entity>();
		
		addEntity(myPlayer);
	}
	
	public void tick()
	{
		Iterator<entity> it = entities.iterator();
		
		while(it.hasNext())
		{
			entity e = it.next();
			e.tick();
			if(!e.isActive())
			{
				it.remove();
			}			
		}
		
		entities.sort(renderSorter);
	}
	
	public void render(Graphics g)
	{
		for(entity e : entities)
		{
			e.render(g);
		}
		
		myPlayer.postRender(g);
	}
	
	public void addEntity(entity e)
	{
		entities.add(e);
	}
	

	public handler getHandler() 
	{
		return myHandler;
	}

	public void setHandler(handler passed_handler)
	{
		this.myHandler = passed_handler;
	}

	public player getPlayer() 
	{
		return myPlayer;
	}

	public void setPlayer(player passed_player) 
	{
		this.myPlayer = passed_player;
	}

	public ArrayList<entity> getEntities() 
	{
		return entities;
	}

	public void setEntities(ArrayList<entity> entities) 
	{
		this.entities = entities;
	}

}
