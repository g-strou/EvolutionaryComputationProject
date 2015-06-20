package ec.app.msolitaire.func;
import ec.*;
import ec.app.msolitaire.*;
import ec.gp.*;
import ec.util.*;
import java.util.Arrays;
import java.util.ArrayList;

//this terminal adds the move which creates the most moves at the next step
//to find it, it uses many functions of the GPProblem class MSol slightly modified
public class GetBest extends GPNode {

	public String toString() { return "getBest"; }
	
	public int futureMoves; 
	
    public int expectedChildren() {return 0;}

    public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack, final GPIndividual individual, final Problem problem) {
    	
    	MSol p = (MSol)problem; 
    	MSolData d = (MSolData)(input);
    	
    	int [][][] map = new int[MSol.NOD][MSol.NOD][6];
		for(int i=0; i< MSol.NOD; i++) 
			for(int j=0; j< MSol.NOD; j++)
				for(int k=0; k<6; k++)
					map[i][j][k] = p.map[i][j][k];
		
       	int [] move = getBestMove(map, p.validMoves);
       	
    	if (p.addMove(move) > 0) p.actionHistory.add(new String("getBest (" + futureMoves + ")"));
        
    	//return -1
        d.x = -1;
        d.y = -1;
    }
    
	public int [] getBestMove(int [][][]map, final ArrayList<int []> currentMoves) {
		
		int max = 0;
		int [] bestMove = null;
		
		int [][][] tempMap = new int[MSol.NOD][MSol.NOD][6];

		for (int[] move: currentMoves) {
			
			for(int i=0; i< MSol.NOD; i++) 
				for(int j=0; j< MSol.NOD; j++)
					for(int k=0; k<6; k++)
						tempMap[i][j][k] = map[i][j][k];
			
			if (addMove(tempMap, move)<0) continue;
			int numOfNewMoves = updateMoves(tempMap).size();
			if (numOfNewMoves > max) {
				max = numOfNewMoves;
				bestMove = move;
			}
		}
		
		futureMoves = max;
		
		return bestMove;
	}
	
	public ArrayList<int[]> updateMoves(int [][][] grid) {
	
		int x, y;
		
		ArrayList<int[]> gridMoves = new ArrayList<int[]>();
		
		//horizontal - direction 1, EAST
		for (y=0; y<MSol.NOD; y++) {
			for(x=0; x<= MSol.NOD - 5; x++) {
				int []cnf = checkNextFive(grid, x, y, MSol.HORIZONTAL);
				if (cnf != null) gridMoves.add(cnf);
			}
		}	
		
		//diagonal positive slope - direction 2, NORTH-EAST
		for (x=0; x < MSol.NOD - 5; x++) { 
			for (y=0; y< MSol.NOD - 5; y++) {
				if (x>=0 && x < MSol.NOD && y >= 0 && y < MSol.NOD) {
					int []cnf = checkNextFive(grid, x, y, MSol.DIAG_PLUS);
					if (cnf != null) gridMoves.add(cnf);					
				}
			}
		}	
		
		//vertical direction 3 NORTH
		for (x=0; x<MSol.NOD; x++) { 
			for (y=0; y<= MSol.NOD - 5; y++) {
				int []cnf = checkNextFive(grid, x, y, MSol.VERTICAL);
				if (cnf != null) gridMoves.add(cnf);
			}
		}
		
		//diagonal negative slope - direction 4, NORTH-WEST
		for (x=4; x < MSol.NOD ; x++) { 
			for (y=0; y< MSol.NOD - 5; y++) {
				if (x>=0 && x < MSol.NOD && y >= 0 && y < MSol.NOD) {
					int []cnf = checkNextFive(grid, x, y, MSol.DIAG_MINUS);
					if (cnf != null) gridMoves.add(cnf);					
				}
			}
		}	
		
		return gridMoves;
	}
	
	public int[] checkNextFive(int [][][] grid, int x, int y, int d) {//x,y, d direction 1..4
		
		if (d < 1 || d > 4) return null;
		
		int ticks = 0;
		int lines = 0;
		int cind = 0;
		int []a = {};
		
		for(int k=0; k<5; k++) {
			
			switch (d) {
				case MSol.HORIZONTAL:	a = grid[x + k][y];
									break;
				case MSol.DIAG_PLUS: 	a = grid[x + k][y + k];
									break;
				case MSol.VERTICAL: 		a = grid[x][y + k];
									break;
				case MSol.DIAG_MINUS:	a = grid[x - k][y + k];
									break;
			}
			if (a[d] == 1 && k < 4) lines++;
			if (a[MSol.CROSS] == 1) ticks++; else cind = k;
		}
		
		if (lines == 0 && ticks == 4) {
			
			int cx, cy, ex, ey;
			cx = cy = ex = ey = -1;
			
			switch (d) {
				case MSol.HORIZONTAL:	cx = x + cind; cy = y; ex = x + 4; ey = y;
										break;
				case MSol.DIAG_PLUS: 		cx = x + cind; cy = y + cind ; ex = x + 4; ey = y + 4;
										break;
				case MSol.VERTICAL: 			cx = x; cy = y + cind; ex = x ; ey = y + 4;
										break;
				case MSol.DIAG_MINUS:		cx = x - cind; cy = y + cind ; ex = x - 4; ey = y + 4;
										break;
			}
			
			return new int[]{x, y, ex, ey, cx, cy, d};
			
		}
		
		return null;
		
	}

	public int addMove(int [][][] grid, int [] move) {
		
		if (move == null) return -1;
		
		ArrayList<int[]> availableMoves = updateMoves(grid);
		
		if (availableMoves.isEmpty()) return -1;
		
		//check to see if it is a valid move
		boolean isValid = false;
		
		for (int vm[] : availableMoves) 
			if (Arrays.equals(vm, move)) {isValid = true;break;}
		
		if (!isValid) {System.out.println("Wrong move!!");return -1;}
		
		int sx, sy, ex, ey, cx, cy, d;
		sx = move[0]; //the start point x coordinate
		sy = move[1]; //the start point y coordinate
		ex = move[2]; //the end point x coordinate
		ey = move[3]; //the end point y coordinate
		cx = move[4]; //the cross x coordinate
		cy = move[5]; //the cross y coordinate
		d = move[6]; // the direction of the line (1..4)

		grid[cx][cy][MSol.CROSS] = 1; 		//updates the MSol.CROSS field of the node where the cross was added
		 	//stores the number of the move which is the updated score

		int step_x = (ex - sx) /4;		//sets the step for the loop
		int step_y = (ey - sy) /4;
		
		for(int k=0; k<4; k++) {  		//updates the proper direction field (d = 1..4) of all the nodes of the line added
			grid[sx + k*step_x][sy + k * step_y][d] = 1;
		}

		return 1;
	}
	

}