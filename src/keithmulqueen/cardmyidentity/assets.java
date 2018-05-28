package keithmulqueen.cardmyidentity;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class assets 
{	
	private static final int width = 32, height = 32;
	
	public static Font font28;
	
	//Changeable, Tiles, Seeds, Pouches & Pearls
	public static BufferedImage[] ground, seed, pouch, pearl, bucket, robot, rock, wax, survivor, beaker, zone, plant;

	//Environment Objects
	public static BufferedImage tree, spaceship, lamp, bush, long_bush, house, hole, well, scrap, sign, craft_table;
	public static BufferedImage hospital, furnace, patch, regen, mixer, terra_control;
	
	//Collectible
	public static BufferedImage shovel, wood, wheel, metal, stone, petals, berry, coal, pickaxe, glass, delivery, blob_mind, gun;

	//Animation arrays
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage player_down_still, player_up_still, player_left_still, player_right_still;

	public static BufferedImage[] blob_down, blob_up, blob_left, blob_right;

	public static BufferedImage inventoryScreen;
	
	public static BufferedImage signScreen;

	public static void init()
	{
		
		//Assign text and textures
		font28 = fontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		
		spriteSheet sheet = new spriteSheet(imageLoader.loadImage("/textures/sheet.png"));
		
		inventoryScreen = imageLoader.loadImage("/textures/inventoryScreen.png");
		signScreen = imageLoader.loadImage("/textures/signScreen.png");

		//Ground images
		ground = new BufferedImage[15];

		ground[0] = sheet.crop(width, 0, width, height);						//ground1
		ground[1] = sheet.crop(width * 2, 0, width, height);					//ground2
		ground[2] = sheet.crop(width * 3, height * 3, width, height);			//pavement
		ground[3] = sheet.crop(width * 3, height * 9, width, height);			//higher
		ground[4] = sheet.crop(width * 3, 0, width, height);					//higher bottom

		ground[5] = sheet.crop(width * 7, height * 9, width, height);			//sea
		ground[6] = sheet.crop(width * 7, height * 8, width, height);			//sea north
		ground[7] = sheet.crop(width * 7, height * 10, width, height);			//sea south
		ground[8] = sheet.crop(width * 6, height * 9, width, height);			//sea west
		ground[9] = sheet.crop(width * 8, height * 9, width, height);			//sea east
		
		ground[10] = sheet.crop(width * 6, height * 8, width, height);			//sea northwest
		ground[11] = sheet.crop(width * 8, height * 8, width, height);			//sea northeast
		ground[12] = sheet.crop(width * 6, height * 10, width, height);			//sea southwest
		ground[13] = sheet.crop(width * 8, height * 10, width, height);			//sea southeast
		
		ground[14] = sheet.crop(width * 3, height * 7, width, height);			//puddle
		
		
		//Seed images
		seed = new BufferedImage[6];
		
		seed[0] = sheet.crop(width * 4, height * 7, width, height);				//red seed
		seed[1] = sheet.crop(width * 0, height * 10, width, height);			//blue seed		
		seed[2] = sheet.crop(width * 0, height * 8, width, height);				//green seed
		seed[3] = sheet.crop(width * 1, height * 8, width, height);				//white seed
		seed[4] = sheet.crop(width * 5, height * 7, width, height);				//purple seed
		seed[5] = sheet.crop(width * 4, height * 10, width, height);			//black seed
		
		
		//Wax Candle images
		wax = new BufferedImage[2];
		
		wax[0] = sheet.crop(width * 11, height * 5, width, height);				//wax
		wax[1] = sheet.crop(width * 12, height * 5, width, height);				//candle
		
		
		//Bucket images
		bucket = new BufferedImage[4];
		
		bucket[0] = sheet.crop(width * 5, height * 9, width, height);			//empty bucket
		bucket[1] = sheet.crop(width * 5, height * 10, width, height);			//water bucket
		bucket[2] = sheet.crop(width * 4, height * 9, width, height);			//oil bucket
		bucket[3] = sheet.crop(width * 13, height * 1, width, height);			//sand bucket
		
		
		//Science beaker images
		beaker = new BufferedImage[3];
		
		beaker[0] = sheet.crop(width * 12, height * 8, width, height);			//empty beaker
		beaker[1] = sheet.crop(width * 11, height * 8, width, height);			//acid beaker
		beaker[2] = sheet.crop(width * 13, height * 8, width, height);			//blob beaker
		
		
		//Robot images
		robot = new BufferedImage[2];
		
		robot[0] = sheet.crop(width * 1, height * 9, width, height);			//broken robot
		robot[1] = sheet.crop(width * 8, height * 4, width, height);			//robot
		
		
		//Pearl images
		pearl = new BufferedImage[6];

		pearl[0] = sheet.crop(width * 3, height * 4, width, height);			//cyan
		pearl[1] = sheet.crop(width * 4, height * 4, width, height);			//purple
		pearl[2] = sheet.crop(width * 5, height * 4, width, height);			//white
		pearl[3] = sheet.crop(width * 3, height * 5, width, height);			//gold
		pearl[4] = sheet.crop(width * 4, height * 5, width, height);			//red
		pearl[5] = sheet.crop(width * 5, height * 5, width, height);			//lime
		
		
		//Pearl images
		pouch = new BufferedImage[6];

		pouch[0] = sheet.crop(width * 0, height * 7, width, height);			//bronze
		pouch[1] = sheet.crop(width * 1, height * 7, width, height);			//silver
		pouch[2] = sheet.crop(width * 2, height * 7, width, height);			//gold
		
		
		//Zone images
		zone = new BufferedImage[3];

		zone[0] = sheet.crop(width * 12, height * 9, width, height);			//hospital
		zone[1] = sheet.crop(width * 12, height * 10, width, height);			//heat
		zone[2] = sheet.crop(width * 13, height * 10, width, height);			//ammo
		
		
		//Survivor images
		survivor = new BufferedImage[3];

		survivor[0] = sheet.crop(width * 11, height * 7, width, height);			//dead
		survivor[1] = sheet.crop(width * 13, height * 7, width, height);			//sick
		survivor[2] = sheet.crop(width * 13, height * 6, width, height);			//healthy
		
		//Plant images
		//Rock images
		plant = new BufferedImage[8];

		plant[0] = sheet.crop(width * 0, height * 0, width * 1, height * 2);				//Evergreen tree
		plant[1] = sheet.crop(width * 12, height * 6, width * 1, height * 2);				//Deciduous tree
		plant[2] = sheet.crop(width * 11, height * 6, width, height);						//Venus Flytrap
		plant[3] = sheet.crop(width * 10, height * 6, width, height);						//Sunflower
		plant[4] = sheet.crop(width * 9, height * 6, width, height);						//Wax Cabbage
		plant[5] = sheet.crop(width * 8, height * 3, width, height);						//Short Bush
		plant[6] = sheet.crop(width * 2, height * 8, width * 3, height);					//Long Bush
		plant[7] = sheet.crop(width * 6,  height * 4, width * 1, height * 2);				//Dead tree
		
		
		//Rock images
		rock = new BufferedImage[6];

		rock[0] = sheet.crop(width * 2, height, width, height);				//cyan
		rock[1] = sheet.crop(width * 3, height, width, height);				//purple
		rock[2] = sheet.crop(width * 3, height * 2, width, height);			//white
		rock[3] = sheet.crop(width * 2, height * 2, width, height);			//gold
		rock[4] = sheet.crop(width, height * 2, width, height);				//red
		rock[5] = sheet.crop(0, height * 2, width, height);					//lime
				
		
		//Collecteables
		shovel  = sheet.crop(width * 8, height * 0, width, height);	
		pickaxe = sheet.crop(width * 9, height * 4, width, height);
		wood = sheet.crop(width * 1, height * 1, width, height);	
		wheel = sheet.crop(width * 2, height * 9, width, height);	
		metal = sheet.crop(width * 8, height * 5, width, height);
		stone = sheet.crop(width * 3, height * 6, width, height);
		coal = sheet.crop(width * 12, height * 4, width, height);
		glass = sheet.crop(width * 13, height * 9, width, height);
		blob_mind = sheet.crop(width * 13, height * 4, width, height);
		gun = sheet.crop(width * 13, height * 3, width, height);
		petals = sheet.crop(width * 13, height * 5, width, height);
		berry = sheet.crop(width * 7, height * 4, width, height);
		delivery = sheet.crop(width * 13, height * 0, width, height);
		
		//World Objects
		tree = sheet.crop(0, 0, width, height * 2);
		spaceship = sheet.crop(0, height * 3, width * 3, height * 4);
		lamp = sheet.crop(width * 8, height, width, height * 2);
		bush = sheet.crop(width * 8, height * 3, width, height);
		long_bush = sheet.crop(width * 2, height * 8, width * 3, height);
		house = sheet.crop(width * 6, height * 6, width * 3, height * 2);
		hole = sheet.crop(width * 5, height * 8, width, height);
		well = sheet.crop(width * 2, height * 10, width * 2, height);
		scrap = sheet.crop(0, height * 9, width, height);
		sign = sheet.crop(width, height * 10, width, height);
		craft_table = sheet.crop(width * 4, height * 6, width * 2, height);
		hospital = sheet.crop(width * 9, height * 9, width * 3, height * 2);
		furnace = sheet.crop(width * 10, height * 4, width * 2, height);
		patch = sheet.crop(width * 9, height * 7, width * 2, height * 2);
		regen = sheet.crop(width * 9, height * 5, width * 2, height);
		mixer = sheet.crop(width * 13, height * 2, width, height);
		terra_control = sheet.crop(width * 7, height * 5, width, height);
				
		
		//Arrays for PLAYER animation
		player_down = new BufferedImage[4];
		player_up = new BufferedImage[4];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];
		
		player_down[0] = sheet.crop(width * 4, height * 2, width, height);
		player_down[1] = sheet.crop(width * 5, height * 2, width, height);
		player_down[2] = sheet.crop(width * 6, height * 2, width, height);
		player_down[3] = sheet.crop(width * 7, height * 2, width, height);
		
		player_up[0] = sheet.crop(width * 4, 0, width, height);
		player_up[1] = sheet.crop(width * 5, 0, width, height);
		player_up[2] = sheet.crop(width * 6, 0, width, height);
		player_up[3] = sheet.crop(width * 7, 0, width, height);
		
		player_right[0] = sheet.crop(width * 4, height * 3, width, height);
		player_right[1] = sheet.crop(width * 5, height * 3, width, height);
		player_right[2] = sheet.crop(width * 6, height * 3, width, height);
		player_right[3] = sheet.crop(width * 7, height * 3, width, height);
			
		player_left[0] = sheet.crop(width * 4, height, width, height);
		player_left[1] = sheet.crop(width * 5, height, width, height);
		player_left[2] = sheet.crop(width * 6, height, width, height);
		player_left[3] = sheet.crop(width * 7, height, width, height);
		
		//Image crops for non animation
		player_down_still = sheet.crop(width * 4, height * 2, width, height);
		player_up_still = sheet.crop(width * 4, 0, width, height);
		player_left_still = sheet.crop(width * 4, height, width, height);
		player_right_still = sheet.crop(width * 4, height * 3, width, height);
		
		
		//Arrays for BLOB animation
		
		blob_down = new BufferedImage[4];
		blob_up = new BufferedImage[4];
		blob_left = new BufferedImage[4];
		blob_right = new BufferedImage[4];
		
		blob_down[0] = sheet.crop(width * 9, height * 0, width, height);
		blob_down[1] = sheet.crop(width * 10, height * 0, width, height);
		blob_down[2] = sheet.crop(width * 11, height * 0, width, height);
		blob_down[3] = sheet.crop(width * 12, height * 0, width, height);
		
		blob_up[0] = sheet.crop(width * 9, height * 3, width, height);
		blob_up[1] = sheet.crop(width * 10, height * 3, width, height);
		blob_up[2] = sheet.crop(width * 11, height * 3, width, height);
		blob_up[3] = sheet.crop(width * 12, height * 3, width, height);
		
		blob_right[0] = sheet.crop(width * 9, height * 2, width, height);
		blob_right[1] = sheet.crop(width * 10, height * 2, width, height);
		blob_right[2] = sheet.crop(width * 11, height * 2, width, height);
		blob_right[3] = sheet.crop(width * 12, height * 2, width, height);
		
		blob_left[0] = sheet.crop(width * 9, height * 1, width, height);
		blob_left[1] = sheet.crop(width * 10, height * 1, width, height);
		blob_left[2] = sheet.crop(width * 11, height * 1, width, height);
		blob_left[3] = sheet.crop(width * 12, height * 1, width, height);
	}
		
}