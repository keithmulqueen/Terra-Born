package keithmulqueen.cardmyidentity;


public class handler 
{
	
	private game myGame;
	private world myWorld;
	
	public handler(game passed_game)
	{
		this.myGame = passed_game;
	}
	
	public gameCamera getGameCamera()
	{
		return myGame.getGameCamera();
	}
	
	public keyManager getKeyManager()
	{
		return myGame.getKeyManager();
	}
	
	public mouseManager getMouseManager()
	{
		return myGame.getMouseManager();
	}
	
	public int getWidth()
	{
		return myGame.getWidth();
	}
	
	public int getHeight()
	{
		return myGame.getHeight();
	}

	public game getGame() 
	{
		return myGame;
	}

	public void setGame(game passed_game) 
	{
		this.myGame = passed_game;
	}

	public world getWorld() 
	{
		return myWorld;
	}

	public void setWorld(world passed_world) 
	{
		this.myWorld = passed_world;
	}

}
