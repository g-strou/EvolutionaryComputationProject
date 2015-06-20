package ec.app.msolitaire.func;
import ec.*;
import ec.app.msolitaire.*;
import ec.gp.*;
import ec.util.*;
import java.io.*;

//this is our ERC - it is a copy from ECJ lawnmower problem ERC with slight modifications
//comments in this file are from the original file
public class MSolERC extends ERC {
	
    public int x;
    public int y;
    
    //takes a value in the central region of the grid, defined by MSol.X_REF & MSol.Y_REF (16,17) and MSol.RND_BAND(13)
    public void resetNode(final EvolutionState state, final int thread) {
    	x = MSol.X_REF - 1 + state.random[thread].nextInt(MSol.RND_BAND);
    	y = MSol.Y_REF - 1 + state.random[thread].nextInt(MSol.RND_BAND);
    }
    
    public int nodeHashCode() { return this.getClass().hashCode() + 40*x + y;}
    
    public boolean nodeEquals(final GPNode node) {
	    if (this.getClass() != node.getClass()) return false;
	    // now check to see if the ERCs hold the same value
	    MSolERC n = (MSolERC)node;
	    return (n.x==x && n.y==y);
    }
    
    public String encode() { return Code.encode(x) + Code.encode(y); }

    public boolean decode(DecodeReturn dret) {
    
	    // store the position and the string in case they
	    // get modified by Code.java
	    int pos = dret.pos;
	    String data = dret.data;
	
	    // decode
	    Code.decode(dret);
	
	    if (dret.type != DecodeReturn.T_INT) // uh oh!
	        {
	        // restore the position and the string; it was an error
	        dret.data = data;
	        dret.pos = pos;
	        return false;
	        }
	
	    // store the data
	    x = (int)(dret.l);
	
	    // decode
	    Code.decode(dret);
	
	    if (dret.type != DecodeReturn.T_INT) // uh oh!
	        {
	        // restore the position and the string; it was an error
	        dret.data = data;
	        dret.pos = pos;
	        return false;
	        }
	
	    // store the data
	    y = (int)(dret.l);
	
	    return true;
    }
    
    public String toStringForHumans() { return "[" + x + "," + y + "]"; }
    
    public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack, final GPIndividual individual, final Problem problem) {
    	
        MSolData rd = (MSolData)(input);
        
        rd.x = x;
        rd.y = y;

    }
	
}