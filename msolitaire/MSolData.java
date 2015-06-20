package ec.app.msolitaire;
import ec.gp.*;

//our GPData object 
public class MSolData extends GPData
{
    public int x;
    public int y;
	
	public void copyTo(final GPData gpd) {
		MSolData d = (MSolData)gpd;
	    d.x = x;
	    d.y = y;
	}
}