package keithmulqueen.cardmyidentity;

//84 50
//193 340

import java.awt.Graphics;
import java.util.Random;

public class world 
{
	private handler myHandler;
	private int width, height;
	private boolean craftingTable;
	private int spawnX, spawnY;
	private boolean aztec = false;
	private int[][] tiles;
	
	//Entities and Items
	private entityManager myEntityManager;
	private itemManager myItemManager;
	
	public world(handler passed_handler, String path)
	{
		this.myHandler = passed_handler;
		
		//Adds new entity manager with new player
		
		myEntityManager = new entityManager(myHandler, new player(myHandler, 100, 100));
		myItemManager = new itemManager(passed_handler);
		craftingTable = false;
		
		//Generate earlyObjects
		renderEarlyObjects();
		
		//Generate rocks
		renderRocks();
		
		//Generate signs
		renderSigns();
		
		//Generate holes
		renderHoles();
		
		//Add Later
		if(aztec)
		{
			TEMP_Render();
		}
		
		
		
		loadWorld(path);
		
		myEntityManager.getPlayer().setX(spawnX);
		myEntityManager.getPlayer().setY(spawnY);
	}
	
	public void tick()
	{
		myItemManager.tick();
		myEntityManager.tick();
	}
	
	public void render(Graphics g)
	{
		int xStart = (int) Math.max(0, myHandler.getGameCamera().getxOffset() / tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (myHandler.getGameCamera().getxOffset() + myHandler.getWidth()) / tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, myHandler.getGameCamera().getyOffset() / tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (myHandler.getGameCamera().getyOffset() + myHandler.getHeight()) / tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++)
		{
			for(int x = xStart;x < xEnd;x++)
			{
				getTile(x, y).render(g, (int) (x * tile.TILEWIDTH - myHandler.getGameCamera().getxOffset()), (int) (y * tile.TILEHEIGHT - myHandler.getGameCamera().getyOffset()));
			}
		}
		
		// Render the Items and Entities
		myItemManager.render(g);
		myEntityManager.render(g);
	}
	
	public tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height)
		{
			return tile.floorTileA;
		}
		
		tile t = tile.tiles[tiles[x][y]];
		
		if(t == null)
		{
			return tile.floorTileB;
		}

		return t;
	}
	
	private void loadWorld(String path)
	{
		//Loading the world text file
		
		String file = utils.loadFileAsString(path);
		
		//Remove all the spaces
		String[] tokens = file.split("\\s+");
		
		//Assigning first 4 characters to specific cases
		width = utils.parseInt(tokens[0]);
		height = utils.parseInt(tokens[1]);
		spawnX = utils.parseInt(tokens[2]);
		spawnY = utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		
		//Start at no.4 and assign every character to the tiles array
		for(int y = 0;y < height;y++)
		{
			for(int x = 0;x < width;x++)
			{
				tiles[x][y] = utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public void CraftingTableOn()
	{
		this.craftingTable = true;
	}
	
	public boolean getCraftingTable()
	{
		return craftingTable;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}

	public entityManager getEntityManager() 
	{
		return myEntityManager;
	}

	public handler getHandler() 
	{
		return myHandler;
	}

	public void setHandler(handler passed_handler) 
	{
		this.myHandler = passed_handler;
	}

	public itemManager getItemManager() 
	{
		return myItemManager;
	}

	public void setItemManager(itemManager passed_itemManager) 
	{
		this.myItemManager = passed_itemManager;
	}
	
	private void renderRocks()
	{
		//Generate random rocks
		Random rand = new Random();
		
		
		//AREA ONE
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*11), (tile.TILEHEIGHT*3), 0));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*16), (tile.TILEHEIGHT*3), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*16), (tile.TILEHEIGHT*4), 0));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*4), rand.nextInt(6)));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*5), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*18), (tile.TILEHEIGHT*6), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*18), (tile.TILEHEIGHT*7), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*1), (tile.TILEHEIGHT*9), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*1), (tile.TILEHEIGHT*10), 0));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*19), (tile.TILEHEIGHT*10), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*1), (tile.TILEHEIGHT*11), 0));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*20), (tile.TILEHEIGHT*11), rand.nextInt(6)));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*1), (tile.TILEHEIGHT*12), 0));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*2), (tile.TILEHEIGHT*12), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*3), (tile.TILEHEIGHT*13), rand.nextInt(6)));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*3), (tile.TILEHEIGHT*14), 0));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*14), 0));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*18), (tile.TILEHEIGHT*14), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*16), (tile.TILEHEIGHT*15), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*15), 0));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*19), (tile.TILEHEIGHT*15), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*16), 0));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*18), (tile.TILEHEIGHT*16), 0));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*25), (tile.TILEHEIGHT*16), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*25), (tile.TILEHEIGHT*17), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*21), (tile.TILEHEIGHT*18), rand.nextInt(6)));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*19), (tile.TILEHEIGHT*19), 0));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*20), (tile.TILEHEIGHT*19), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*18), (tile.TILEHEIGHT*20), 0));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*18), (tile.TILEHEIGHT*21), 0));
		
		//AREA TWO
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*14), (tile.TILEHEIGHT*35), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*15), (tile.TILEHEIGHT*35), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*16), (tile.TILEHEIGHT*35), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*35), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*18), (tile.TILEHEIGHT*35), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*19), (tile.TILEHEIGHT*35), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*20), (tile.TILEHEIGHT*35), 1));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*14), (tile.TILEHEIGHT*36), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*15), (tile.TILEHEIGHT*36), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*16), (tile.TILEHEIGHT*36), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*36), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*18), (tile.TILEHEIGHT*36), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*19), (tile.TILEHEIGHT*36), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*24), (tile.TILEHEIGHT*36), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*25), (tile.TILEHEIGHT*36), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*26), (tile.TILEHEIGHT*36), 1));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*4), (tile.TILEHEIGHT*37), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*16), (tile.TILEHEIGHT*37), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*37), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*18), (tile.TILEHEIGHT*37), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*25), (tile.TILEHEIGHT*37), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*26), (tile.TILEHEIGHT*37), 1));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*2), (tile.TILEHEIGHT*38), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*3), (tile.TILEHEIGHT*38), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*4), (tile.TILEHEIGHT*38), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*5), (tile.TILEHEIGHT*38), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*38), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*18), (tile.TILEHEIGHT*38), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*26), (tile.TILEHEIGHT*38), 1));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*14), (tile.TILEHEIGHT*42), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*22), (tile.TILEHEIGHT*42), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*23), (tile.TILEHEIGHT*42), 1));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*6), (tile.TILEHEIGHT*43), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*7), (tile.TILEHEIGHT*43), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*8), (tile.TILEHEIGHT*43), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*14), (tile.TILEHEIGHT*43), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*20), (tile.TILEHEIGHT*43), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*21), (tile.TILEHEIGHT*43), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*22), (tile.TILEHEIGHT*43), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*23), (tile.TILEHEIGHT*43), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*24), (tile.TILEHEIGHT*43), 1));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*4), (tile.TILEHEIGHT*45), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*5), (tile.TILEHEIGHT*45), 1));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*5), (tile.TILEHEIGHT*44), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*6), (tile.TILEHEIGHT*44), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*21), (tile.TILEHEIGHT*44), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*22), (tile.TILEHEIGHT*44), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*23), (tile.TILEHEIGHT*44), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*24), (tile.TILEHEIGHT*44), 1));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*27), (tile.TILEHEIGHT*46), 1));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*11), (tile.TILEHEIGHT*47), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*21), (tile.TILEHEIGHT*47), 1));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*22), (tile.TILEHEIGHT*47), 1));
		
		
		//AREA THREE
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*69), (tile.TILEHEIGHT*39), 2));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*67), (tile.TILEHEIGHT*40), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*40), 2));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*67), (tile.TILEHEIGHT*41), 2));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*52), (tile.TILEHEIGHT*42), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*53), (tile.TILEHEIGHT*42), 2));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*64), (tile.TILEHEIGHT*43), 2));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*63), (tile.TILEHEIGHT*44), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*44), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*69), (tile.TILEHEIGHT*44), 2));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*63), (tile.TILEHEIGHT*45), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*45), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*69), (tile.TILEHEIGHT*45), 2));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*51), (tile.TILEHEIGHT*46), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*62), (tile.TILEHEIGHT*46), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*46), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*71), (tile.TILEHEIGHT*46), 2));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*53), (tile.TILEHEIGHT*47), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*54), (tile.TILEHEIGHT*47), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*55), (tile.TILEHEIGHT*47), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*72), (tile.TILEHEIGHT*47), 2));
				
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*56), (tile.TILEHEIGHT*48), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*57), (tile.TILEHEIGHT*48), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*58), (tile.TILEHEIGHT*48), 2));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*59), (tile.TILEHEIGHT*48), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*60), (tile.TILEHEIGHT*48), rand.nextInt(6)));
		
		
		//AREA FOUR
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*59), (tile.TILEHEIGHT*3), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*64), (tile.TILEHEIGHT*3), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*65), (tile.TILEHEIGHT*3), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*66), (tile.TILEHEIGHT*3), 3));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*59), (tile.TILEHEIGHT*4), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*63), (tile.TILEHEIGHT*4), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*64), (tile.TILEHEIGHT*4), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*65), (tile.TILEHEIGHT*4), 3));
			
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*63), (tile.TILEHEIGHT*5), 3));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*58), (tile.TILEHEIGHT*7), 3));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*58), (tile.TILEHEIGHT*8), 3));
	
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*40), (tile.TILEHEIGHT*9), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*64), (tile.TILEHEIGHT*9), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*65), (tile.TILEHEIGHT*9), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*66), (tile.TILEHEIGHT*9), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*72), (tile.TILEHEIGHT*9), 3));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*71), (tile.TILEHEIGHT*10), rand.nextInt(6)));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*34), (tile.TILEHEIGHT*11), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*60), (tile.TILEHEIGHT*11), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*71), (tile.TILEHEIGHT*11), 3));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*34), (tile.TILEHEIGHT*12), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*35), (tile.TILEHEIGHT*12), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*61), (tile.TILEHEIGHT*12), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*65), (tile.TILEHEIGHT*12), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*66), (tile.TILEHEIGHT*12), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*72), (tile.TILEHEIGHT*12), 3));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*38), (tile.TILEHEIGHT*13), 3));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*60), (tile.TILEHEIGHT*15), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*61), (tile.TILEHEIGHT*15), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*62), (tile.TILEHEIGHT*15), 3));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*40), (tile.TILEHEIGHT*16), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*49), (tile.TILEHEIGHT*16), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*51), (tile.TILEHEIGHT*16), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*52), (tile.TILEHEIGHT*16), 3));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*61), (tile.TILEHEIGHT*16), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*62), (tile.TILEHEIGHT*16), 3));
		
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*59), (tile.TILEHEIGHT*17), 3));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*61), (tile.TILEHEIGHT*17), rand.nextInt(6)));
		
		//AREA FIVE	
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*30), (tile.TILEHEIGHT*18), 4));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*30), (tile.TILEHEIGHT*19), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*37), (tile.TILEHEIGHT*19), 4));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*29), (tile.TILEHEIGHT*21), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*38), (tile.TILEHEIGHT*21), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*39), (tile.TILEHEIGHT*21), 4));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*27), (tile.TILEHEIGHT*22), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*28), (tile.TILEHEIGHT*22), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*38), (tile.TILEHEIGHT*22), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*39), (tile.TILEHEIGHT*22), 4));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*24), (tile.TILEHEIGHT*23), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*25), (tile.TILEHEIGHT*23), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*26), (tile.TILEHEIGHT*23), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*39), (tile.TILEHEIGHT*23), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*40), (tile.TILEHEIGHT*23), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*41), (tile.TILEHEIGHT*23), 4));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*16), (tile.TILEHEIGHT*26), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*26), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*33), (tile.TILEHEIGHT*26), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*36), (tile.TILEHEIGHT*26), 4));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*27), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*32), (tile.TILEHEIGHT*27), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*33), (tile.TILEHEIGHT*27), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*35), (tile.TILEHEIGHT*27), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*36), (tile.TILEHEIGHT*27), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*37), (tile.TILEHEIGHT*27), 4));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*11), (tile.TILEHEIGHT*28), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*12), (tile.TILEHEIGHT*28), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*33), (tile.TILEHEIGHT*28), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*34), (tile.TILEHEIGHT*28), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*35), (tile.TILEHEIGHT*28), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*36), (tile.TILEHEIGHT*28), 4));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*4), (tile.TILEHEIGHT*31), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*14), (tile.TILEHEIGHT*31), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*15), (tile.TILEHEIGHT*31), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*16), (tile.TILEHEIGHT*31), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*17), (tile.TILEHEIGHT*31), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*41), (tile.TILEHEIGHT*31), 4));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*4), (tile.TILEHEIGHT*32), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*39), (tile.TILEHEIGHT*32), 4));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*32), (tile.TILEHEIGHT*34), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*33), (tile.TILEHEIGHT*34), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*34), (tile.TILEHEIGHT*34), 4));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*35), (tile.TILEHEIGHT*34), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*36), (tile.TILEHEIGHT*34), 4));

		//AREA SIX
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*31), (tile.TILEHEIGHT*16), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*31), (tile.TILEHEIGHT*17), 5));
			
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*18), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*69), (tile.TILEHEIGHT*18), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*18), rand.nextInt(6)));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*19), 5));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*66), (tile.TILEHEIGHT*20), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*54), (tile.TILEHEIGHT*21), 5));	
			
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*54), (tile.TILEHEIGHT*22), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*22), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*69), (tile.TILEHEIGHT*22), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*48), (tile.TILEHEIGHT*23), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*49), (tile.TILEHEIGHT*23), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*50), (tile.TILEHEIGHT*23), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*55), (tile.TILEHEIGHT*23), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*56), (tile.TILEHEIGHT*23), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*57), (tile.TILEHEIGHT*23), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*62), (tile.TILEHEIGHT*23), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*64), (tile.TILEHEIGHT*24), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*66), (tile.TILEHEIGHT*25), 5));
				
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*45), (tile.TILEHEIGHT*26), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*46), (tile.TILEHEIGHT*26), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*47), (tile.TILEHEIGHT*26), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*48), (tile.TILEHEIGHT*26), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*57), (tile.TILEHEIGHT*26), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*58), (tile.TILEHEIGHT*26), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*49), (tile.TILEHEIGHT*27), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*62), (tile.TILEHEIGHT*27), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*63), (tile.TILEHEIGHT*27), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*50), (tile.TILEHEIGHT*28), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*51), (tile.TILEHEIGHT*28), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*52), (tile.TILEHEIGHT*28), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*53), (tile.TILEHEIGHT*28), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*36), (tile.TILEHEIGHT*28), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*50), (tile.TILEHEIGHT*28), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*56), (tile.TILEHEIGHT*28), 5));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*3), (tile.TILEHEIGHT*29), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*4), (tile.TILEHEIGHT*29), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*22), (tile.TILEHEIGHT*29), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*23), (tile.TILEHEIGHT*29), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*24), (tile.TILEHEIGHT*29), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*52), (tile.TILEHEIGHT*29), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*53), (tile.TILEHEIGHT*29), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*54), (tile.TILEHEIGHT*29), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*55), (tile.TILEHEIGHT*29), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*56), (tile.TILEHEIGHT*29), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*3), (tile.TILEHEIGHT*30), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*19), (tile.TILEHEIGHT*30), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*29), (tile.TILEHEIGHT*30), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*51), (tile.TILEHEIGHT*30), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*52), (tile.TILEHEIGHT*30), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*53), (tile.TILEHEIGHT*30), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*54), (tile.TILEHEIGHT*30), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*55), (tile.TILEHEIGHT*30), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*69), (tile.TILEHEIGHT*30), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*30), rand.nextInt(6)));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*66), (tile.TILEHEIGHT*31), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*67), (tile.TILEHEIGHT*31), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*31), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*31), 5));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*56), (tile.TILEHEIGHT*32), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*57), (tile.TILEHEIGHT*32), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*67), (tile.TILEHEIGHT*32), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*32), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*32), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*53), (tile.TILEHEIGHT*33), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*33), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*69), (tile.TILEHEIGHT*33), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*33), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*71), (tile.TILEHEIGHT*33), 5));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*54), (tile.TILEHEIGHT*34), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*55), (tile.TILEHEIGHT*34), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*58), (tile.TILEHEIGHT*34), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*54), (tile.TILEHEIGHT*35), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*55), (tile.TILEHEIGHT*35), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*58), (tile.TILEHEIGHT*35), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*59), (tile.TILEHEIGHT*35), rand.nextInt(6)));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*45), (tile.TILEHEIGHT*36), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*54), (tile.TILEHEIGHT*36), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*65), (tile.TILEHEIGHT*36), rand.nextInt(6)));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*66), (tile.TILEHEIGHT*36), 5));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*54), (tile.TILEHEIGHT*37), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*64), (tile.TILEHEIGHT*37), 5));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*49), (tile.TILEHEIGHT*39), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*50), (tile.TILEHEIGHT*39), 5));
	
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*38), (tile.TILEHEIGHT*41), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*41), (tile.TILEHEIGHT*41), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*39), (tile.TILEHEIGHT*42), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*40), (tile.TILEHEIGHT*42), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*41), (tile.TILEHEIGHT*42), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*47), (tile.TILEHEIGHT*45), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*33), (tile.TILEHEIGHT*46), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*46), (tile.TILEHEIGHT*46), 5));

		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*34), (tile.TILEHEIGHT*47), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*35), (tile.TILEHEIGHT*47), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*36), (tile.TILEHEIGHT*47), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*45), (tile.TILEHEIGHT*47), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*46), (tile.TILEHEIGHT*47), 5));
		
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*39), (tile.TILEHEIGHT*48), 5));
		myEntityManager.addEntity(new rock(myHandler, (tile.TILEWIDTH*40), (tile.TILEHEIGHT*48), rand.nextInt(6)));
	}
	
	private void renderSigns()
	{
		//Generate signs
		//1"Gun", 2"PickAxe", 3"Flower Petals", 4"Coal", 5"Blob Mind", 6"6Shovel", 7"Wheel", 8"Metal", 9"Wood", 10"Rock", 11"Glass", 12"Parcel", 
		//13"RED Mars Seed", 14"BLUE Uranus Seed", 15"GREEN Mercury Seed", 16"WHITE Neptune Seed", 17"PURPLE Saturn Seed", 18"BLACK Venus Seed", 
		//19"Bucket", 20"Bucket of Water", 21"Bucket of Oil", 22"Bucket of Sand", 
		//23"Skalium", 24"Kinglium", 25"Dazzium", 26"Shinium", 27"Shinium", 28"Citrium", 
		//29"Flask", 30"Flask of Acid", 31"Flask of Blob", 32"Bronze Pouch", 33"Silver Pouch", 34"Gold Pouch", 35"Wax", 36"Wax Candle"
		
		int signReqs1[]={0,0,5,10,0,0,0,0,0,3,0,2,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*39), (tile.TILEHEIGHT*4),"Build a HOUSE here!", signReqs1));
		
		int signReqs2[]={0,4,0,0,6,0,0,2,0,0,1,0,3,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*73), (tile.TILEHEIGHT*3),"Build a WELL here!", signReqs2));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*10), (tile.TILEHEIGHT*42),"Build a WELL here!", signReqs2));
		
		int signReqs3[]={0,0,3,1,0,0,0,0,0,0,2,1,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*25), (tile.TILEHEIGHT*11),"Build a CRAFT TABLE here!", signReqs3));
		
		int signReqs4[]={2,0,1,0,0,0,1,0,0,0,0,5,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*32), (tile.TILEHEIGHT*16),"Repair the ROBOT here!", signReqs4));
		
		int signReqs5[]={0,0,2,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*73), (tile.TILEHEIGHT*13),"Grow a GARDEN PATCH here!", signReqs5));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*73), (tile.TILEHEIGHT*23),"Grow a GARDEN PATCH here!", signReqs5));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*73), (tile.TILEHEIGHT*40),"Grow a GARDEN PATCH here!", signReqs5));
		
		int signReqs6[]={0,0,2,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*22), (tile.TILEHEIGHT*35),"Build a ROCK BLENDER here!", signReqs6));
		
		int signReqs7[]={0,0,0,2,1,0,0,1,0,0,0,1,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*39), (tile.TILEHEIGHT*37),"Build a FURNACE here!", signReqs7));
		
		int signReqs8[]={1,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*13), (tile.TILEHEIGHT*23),"Build a LAMP here!", signReqs8));	
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*19), (tile.TILEHEIGHT*23),"Build a LAMP here!", signReqs8));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*12),"Build a LAMP here!", signReqs8));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*22),"Build a LAMP here!", signReqs8));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*38),"Build a LAMP here!", signReqs8));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*67), (tile.TILEHEIGHT*3),"Build a LAMP here!", signReqs8));
		
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*53), (tile.TILEHEIGHT*3),"Build a LAMP here!", signReqs8));	
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*35), (tile.TILEHEIGHT*6),"Build a LAMP here!", signReqs8));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*35), (tile.TILEHEIGHT*22),"Build a LAMP here!", signReqs8));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*8), (tile.TILEHEIGHT*35),"Build a LAMP here!", signReqs8));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*23), (tile.TILEHEIGHT*39),"Build a LAMP here!", signReqs8));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*36), (tile.TILEHEIGHT*40),"Build a LAMP here!", signReqs8));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*12), (tile.TILEHEIGHT*44),"Build a LAMP here!", signReqs8));
				
		int signReqs9[]={0,0,2,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*48), (tile.TILEHEIGHT*3),"Help the SURVIVOR!", signReqs9));
		
		int signReqs10[]={0,0,3,1,0,0,0,0,0,0,2,1,0,0,0,0,0,0,0};	
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*28), (tile.TILEHEIGHT*4),"Dig a HOLE here!", signReqs10));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*29), (tile.TILEHEIGHT*6),"Dig a HOLE here!", signReqs10));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*31), (tile.TILEHEIGHT*2),"Dig a HOLE here!", signReqs10));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*30), (tile.TILEHEIGHT*4),"Dig a HOLE here!", signReqs10));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*33), (tile.TILEHEIGHT*2),"Dig a HOLE here!", signReqs10));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*32), (tile.TILEHEIGHT*4),"Dig a HOLE here!", signReqs10));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*33), (tile.TILEHEIGHT*6),"Dig a HOLE here!", signReqs10));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*35), (tile.TILEHEIGHT*2),"Dig a HOLE here!", signReqs10));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*34), (tile.TILEHEIGHT*4),"Dig a HOLE here!", signReqs10));

		int signReqs11[]={0,0,3,1,0,0,0,0,0,0,2,1,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*64), (tile.TILEHEIGHT*41),"Build a MEDICAL LAB here!", signReqs11));
		
		int signReqs12[]={0,0,3,1,0,0,0,0,0,0,2,1,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*59), (tile.TILEHEIGHT*40),"Build a HOSPITAL here!", signReqs12));
		
		int signReqs13[]={1,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*51), (tile.TILEHEIGHT*42),"Build a HEALTH ZONE here!", signReqs13));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*62), (tile.TILEHEIGHT*37),"Build a HEALTH ZONE here!", signReqs13));
		
		int signReqs14[]={1,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*43), (tile.TILEHEIGHT*16),"Build an OXYGEN ZONE here!", signReqs14));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*45), (tile.TILEHEIGHT*4),"Build an OXYGEN ZONE here!", signReqs14));
		
		int signReqs15[]={1,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*30), (tile.TILEHEIGHT*40),"Build an AMMO ZONE here!", signReqs15));
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*7), (tile.TILEHEIGHT*33),"Build an AMMO ZONE here!", signReqs15));
		
		int signReqs16[]={1,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*5), (tile.TILEHEIGHT*5),"Repair the SPACESHIP here!", signReqs16));
		
		int signReqs17[]={1,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0};
		myEntityManager.addEntity(new purchaseSign(myHandler, (tile.TILEWIDTH*52), (tile.TILEHEIGHT*33),"Build a TERRAFORMING CONTROL here!", signReqs17));
		
	}
	
	private void renderHoles()
	{
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*21), (tile.TILEHEIGHT*34)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*23), (tile.TILEHEIGHT*34)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*18), (tile.TILEHEIGHT*31)));
		
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*34), (tile.TILEHEIGHT*16)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*67), (tile.TILEHEIGHT*19)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*50), (tile.TILEHEIGHT*16)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*44), (tile.TILEHEIGHT*16)));
		
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*3)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*63), (tile.TILEHEIGHT*6)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*67), (tile.TILEHEIGHT*9)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*67), (tile.TILEHEIGHT*12)));
		
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*72), (tile.TILEHEIGHT*23)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*67), (tile.TILEHEIGHT*22)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*59), (tile.TILEHEIGHT*26)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*9), (tile.TILEHEIGHT*43)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*5), (tile.TILEHEIGHT*37)));
		
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*10), (tile.TILEHEIGHT*8)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*60), (tile.TILEHEIGHT*16)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*8)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*21), (tile.TILEHEIGHT*25)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*34), (tile.TILEHEIGHT*26)));
				
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*58), (tile.TILEHEIGHT*29)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*49), (tile.TILEHEIGHT*34)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*21), (tile.TILEHEIGHT*42)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*39), (tile.TILEHEIGHT*41)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*43), (tile.TILEHEIGHT*44)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*57), (tile.TILEHEIGHT*43)));
		
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*31), (tile.TILEHEIGHT*40)));
	}
	
	private void renderEarlyObjects()
	{
		//Generate spaceship
		myEntityManager.addEntity(new spaceship(myHandler, (tile.TILEWIDTH*2), (tile.TILEHEIGHT*2)));
		
		//Generate survivors
		myEntityManager.addEntity(new survivor(myHandler, (tile.TILEWIDTH*46), (tile.TILEHEIGHT*3), 0));
		myEntityManager.addEntity(new survivor(myHandler, (tile.TILEWIDTH*47), (tile.TILEHEIGHT*3), 1));
				
		//Generate robot
		myEntityManager.addEntity(new robot(myHandler, (tile.TILEWIDTH*33), (tile.TILEHEIGHT*16)));
		
		//Dead trees
		myEntityManager.addEntity(new plant(myHandler, (tile.TILEWIDTH*29), (tile.TILEHEIGHT*1), 7));
		myEntityManager.addEntity(new plant(myHandler, (tile.TILEWIDTH*31), (tile.TILEHEIGHT*5), 7));
		
		//scraps
		myEntityManager.addEntity(new scrap(myHandler, (tile.TILEWIDTH*5), (tile.TILEHEIGHT*4)));
		myEntityManager.addEntity(new scrap(myHandler, (tile.TILEWIDTH*7), (tile.TILEHEIGHT*5)));
		
		myEntityManager.addEntity(new scrap(myHandler, (tile.TILEWIDTH*30), (tile.TILEHEIGHT*2)));
		myEntityManager.addEntity(new scrap(myHandler, (tile.TILEWIDTH*36), (tile.TILEHEIGHT*2)));
		myEntityManager.addEntity(new scrap(myHandler, (tile.TILEWIDTH*28), (tile.TILEHEIGHT*3)));
		myEntityManager.addEntity(new scrap(myHandler, (tile.TILEWIDTH*37), (tile.TILEHEIGHT*2)));
		myEntityManager.addEntity(new scrap(myHandler, (tile.TILEWIDTH*40), (tile.TILEHEIGHT*4)));
		
		myEntityManager.addEntity(new scrap(myHandler, (tile.TILEWIDTH*50), (tile.TILEHEIGHT*4)));
		myEntityManager.addEntity(new scrap(myHandler, (tile.TILEWIDTH*51), (tile.TILEHEIGHT*4)));
		myEntityManager.addEntity(new scrap(myHandler, (tile.TILEWIDTH*52), (tile.TILEHEIGHT*3)));
	}
	
	private void TEMP_Render()
	{
		//Add later
		myEntityManager.addEntity(new house(myHandler, (tile.TILEWIDTH*38), (tile.TILEHEIGHT*2)));
		myEntityManager.addEntity(new well(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*6)));
		myEntityManager.addEntity(new well(myHandler, (tile.TILEWIDTH*8), (tile.TILEHEIGHT*46)));
		myEntityManager.addEntity(new craftingTable(myHandler, (tile.TILEWIDTH*23), (tile.TILEHEIGHT*11)));
		myEntityManager.addEntity(new hospital(myHandler, (tile.TILEWIDTH*56), (tile.TILEHEIGHT*40)));
		myEntityManager.addEntity(new blobGenerator(myHandler, (tile.TILEWIDTH*62), (tile.TILEHEIGHT*41)));
		myEntityManager.addEntity(new rockBlender(myHandler, (tile.TILEWIDTH*22), (tile.TILEHEIGHT*34)));
		myEntityManager.addEntity(new furnace(myHandler, (tile.TILEWIDTH*40), (tile.TILEHEIGHT*37)));
		myEntityManager.addEntity(new terraControl(myHandler, (tile.TILEWIDTH*51), (tile.TILEHEIGHT*33)));
		
		//health zone
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*49), (tile.TILEHEIGHT*43), 1));
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*49), (tile.TILEHEIGHT*44), 1));
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*60), (tile.TILEHEIGHT*39), 1));
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*61), (tile.TILEHEIGHT*39), 1));
		
		//oxygen zone
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*41), (tile.TILEHEIGHT*14), 0));
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*42), (tile.TILEHEIGHT*14), 0));
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*44), (tile.TILEHEIGHT*5), 0));
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*44), (tile.TILEHEIGHT*6), 0));
		
		//ammo zone
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*29), (tile.TILEHEIGHT*41), 2));
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*29), (tile.TILEHEIGHT*42), 2));
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*6), (tile.TILEHEIGHT*35), 2));
		myEntityManager.addEntity(new zone(myHandler, (tile.TILEWIDTH*7), (tile.TILEHEIGHT*35), 2));
		
		//garden patch
		myEntityManager.addEntity(new gardenPatch(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*14)));
		myEntityManager.addEntity(new gardenPatch(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*25)));
		myEntityManager.addEntity(new gardenPatch(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*41)));	
		
		//lamps
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*13), (tile.TILEHEIGHT*22)));	
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*19), (tile.TILEHEIGHT*22)));
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*68), (tile.TILEHEIGHT*11)));
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*21)));
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*70), (tile.TILEHEIGHT*38)));
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*67), (tile.TILEHEIGHT*2)));
		
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*53), (tile.TILEHEIGHT*2)));	
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*35), (tile.TILEHEIGHT*5)));
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*35), (tile.TILEHEIGHT*21)));
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*8), (tile.TILEHEIGHT*34)));
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*23), (tile.TILEHEIGHT*38)));
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*36), (tile.TILEHEIGHT*39)));
		myEntityManager.addEntity(new lamp(myHandler, (tile.TILEWIDTH*12), (tile.TILEHEIGHT*43)));
		
		//dig holes
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*28), (tile.TILEHEIGHT*4)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*29), (tile.TILEHEIGHT*6)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*31), (tile.TILEHEIGHT*2)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*30), (tile.TILEHEIGHT*4)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*33), (tile.TILEHEIGHT*2)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*32), (tile.TILEHEIGHT*4)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*33), (tile.TILEHEIGHT*6)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*35), (tile.TILEHEIGHT*2)));
		myEntityManager.addEntity(new hole(myHandler, (tile.TILEWIDTH*34), (tile.TILEHEIGHT*4)));
			
	}
	
	public void setAztec()
	{
		aztec = true;
	}
	
}
