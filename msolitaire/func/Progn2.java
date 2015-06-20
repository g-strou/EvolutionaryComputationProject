package ec.app.msolitaire.func;
import ec.app.msolitaire.*;
import ec.*;
import ec.gp.*;
import ec.util.*;

//progn2
public class Progn2 extends GPNode {
    
    public String toString() { return "progn2"; }

    public int expectedChildren() { return 2; }

    public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack, final GPIndividual individual, final Problem problem) { 
         	
    	MSol p = (MSol)problem; 
        MSolData d = (MSolData)(input);
    	
        children[0].eval(state,thread,input,stack,individual,problem);
        
        //only if it is an ERC it has d.x>0 and d.y>0
        if (d.x >0 && d.y >0) {
        	int [] move = p.getClosestMoveAt(d.x, d.y);
        	if (p.addMove(move) > 0) p.actionHistory.add(new String("[" + d.x + "," + d.y + "]"));
        }
        
        children[1].eval(state,thread,input,stack,individual,problem);
    }
}



