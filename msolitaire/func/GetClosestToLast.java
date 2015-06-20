package ec.app.msolitaire.func;
import ec.*;
import ec.app.msolitaire.*;
import ec.gp.*;
import ec.util.*;

//return the "closest" move to the last move added
//whenever a move is added, global variables xCurrent and yCurrent of the MSol problem
//hold its cross position 
public class GetClosestToLast extends GPNode {

	public String toString() { return "getClosestToLast"; }
	
    public int expectedChildren() {return 0;}

    public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack, final GPIndividual individual, final Problem problem) {
    	
    	MSol p = (MSol)problem; 
    	MSolData d = (MSolData)(input);
    	
    	int [] move = p.getClosestMoveAt(p.xCurrent, p.yCurrent);
    	if (p.addMove(move) > 0) p.actionHistory.add(new String("getClosestToLast"));
        
    	//returns -1
        d.x = -1;
        d.y = -1;
    }
	
}