package keithmulqueen.cardmyidentity;

public class highTile extends tile 
{
	
	public highTile(int id) 
	{
		super(assets.ground[id], id);
	}
	
	@Override
	public boolean isSolid()
	{
		return true;
	}
}