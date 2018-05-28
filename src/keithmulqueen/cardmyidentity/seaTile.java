package keithmulqueen.cardmyidentity;

public class seaTile extends tile 
{
	
	public seaTile(int id) 
	{
		super(assets.ground[id], id);
	}
	
	@Override
	public boolean isSolid()
	{
		return true;
	}
}