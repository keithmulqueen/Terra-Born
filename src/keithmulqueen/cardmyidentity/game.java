package keithmulqueen.cardmyidentity;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class game implements Runnable 
{
	private display myDisplay;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	public state gameState;
	public state menuState;
	
	//Input
	private keyManager myKeyManager;
	private mouseManager myMouseManager;
	
	//Camera
	private gameCamera myGameCamera;
	
	//Handler
	private handler myHandler;
	
	public game(String passed_title, int passed_width, int passed_height)
	{
		//Preparing game components
		
		this.title = passed_title;
		this.width = passed_width;
		this.height = passed_height;
		
		myKeyManager = new keyManager();
		myMouseManager = new mouseManager();
	}
	
	private void init()
	{
		//Setting up the window and event handlers
		
		myDisplay = new display(title, width, height);
		myDisplay.getFrame().addKeyListener(myKeyManager);
		myDisplay.getFrame().addMouseListener(myMouseManager);
		myDisplay.getFrame().addMouseMotionListener(myMouseManager);
		myDisplay.getCanvas().addMouseListener(myMouseManager);
		myDisplay.getCanvas().addMouseMotionListener(myMouseManager);
		
		//Initialize the screen objects from sheet.png
		
		assets.init();
		
		
		//Setting up handler and initializing the game camera
		
		myHandler = new handler(this);
		myGameCamera = new gameCamera(myHandler, 0, 0);
		
		//Setting up two screen states
		
		gameState = new gameState(myHandler);
		menuState = new menuState(myHandler);
		
		//start on the menu screen
		
		state.setState(menuState);	
	}
	
	private void tick()
	{
		myKeyManager.tick();
		
		if(state.getState() != null)
		{
			state.getState().tick();
		}
	}
	
	private void render()
	{
		bs = myDisplay.getCanvas().getBufferStrategy();
		
		if(bs == null)
		{
			myDisplay.getCanvas().createBufferStrategy(3);
			
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//Clear Screen
		g.clearRect(0, 0, width, height);
		
		//Draw Here!		
		if(state.getState() != null)
		{
			state.getState().render(g);
		}
			
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run()
	{
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			
			//FPS Calculator
			
			if(timer >= 1000000000)
			{
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public keyManager getKeyManager()
	{
		return myKeyManager;
	}
	
	public mouseManager getMouseManager()
	{
		return myMouseManager;
	}
	
	public gameCamera getGameCamera()
	{
		return myGameCamera;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public synchronized void start()
	{
		//If the game is already running then return
		
		if(running)
		{
			return;
		}
			
		//or set the game to running
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		//If the game has already stopped then return
		
		if(!running)
		{
			return;
		}
			
		//or set the game to stop
		
		running = false;
		
		try 
		{
			//join the threads (stop threading)
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
}
