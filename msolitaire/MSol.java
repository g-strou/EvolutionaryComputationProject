package ec.app.msolitaire;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import ec.util.*;
import ec.*;
import ec.gp.*;
import ec.gp.koza.*;
import ec.simple.*;


public class MSol extends GPProblem implements SimpleProblemForm {

    //each node is determined by its x and y coordinates in the map array 
    //and has 6 attributes whose indices in the third dimension of the map array are given
    //below along with their description
    public static final int CROSS = 0; //map[x][y][CROSS] is 1 if the node has a cross, 0 otherwise
    public static final int HORIZONTAL = 1; //map[x][y][HORIZONTAL]  is 1 if the node has a line to map[x+1][y], 0 otherwise
    public static final int DIAG_PLUS = 2; //map[x][y][DIAG_PLUS]  is 1 if the node has a line to map[x+1][y+1], 0 otherwise
    public static final int VERTICAL = 3; //map[x][y][VERTICAL] is 1 if the node has a line to map[x][y+1], 0 otherwise
    public static final int DIAG_MINUS = 4; //map[x][y][DIAG_MINUS]  is 1 if the node has a line to map[x-1][y+1], 0 otherwise
    public static final int MOVE = 5; //if the node acquired a cross when a move was added, this field stores the score
    							//at this moment, 0 default
	
    //constant values we use
	public static final int NOD = 41; //number of nodes per side 0 to 40
    public static final int X_REF = 16; // point (16,17) is our anchor point. It is the left bottom vertice
    public static final int Y_REF = 17; // of the minimum square that includes the Greek cross. 
    public static final int RND_BAND = 13; //RND_BAND is used by MSolERC. Determines a central area of the grid
    										//where the ERC vectors will get values from
	
    //the map contains all the information of the grid. Two dimensions are for the x and y coordinates
    //of each node and the third one contains the node's attributes described above
	public int[][][] map;
	
	//in every step this arraylist contains all valid moves
	//it is updated when a new move is added
	public ArrayList<int[]> validMoves;
	
	//keeps the history of all the moves from the beginning of a game
	public ArrayList<int[]> moveHistory; 
	
	//keeps a record of the actions (GP nodes description) taken that produce the moveHistory
	public ArrayList<String> actionHistory;

    //the score is equal to the length of the moveHistory arraylist, but we also keep it in a separate variable
   	public int score;
  
    //the position of the last movement cross
    public int xCurrent; 
    public int yCurrent;
	
    public String movesForExecutable;
    
    //this returns the distance between two nodes as the maximum of the absolute difference of x and y coordinates
    //that is, the distance between two nodes is their vertical or horizontal distance, the bigger one
	public static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
	}
    
    //the evolutionary-code that follows was based in the implementation of the lawn mower problem from the ECJ app folder
    
    //this is to make a deep copy of the problem, copies the arrays and the arraylists
    public Object clone() {
        
    	MSol myobj = (MSol) (super.clone());

    	myobj.map = new int[NOD][NOD][6];
        for(int i=0;i<NOD;i++)
        	for(int j=0;j<NOD;j++)
        		for(int k=0;k<6;k++) {
        			myobj.map[i][j][k] = map[i][j][k];
        		}
        
        myobj.moveHistory = new ArrayList<int []>();
        for (int[] move : moveHistory) {
        	myobj.moveHistory.add(Arrays.copyOf(move, move.length));
        }
        
        myobj.actionHistory = new ArrayList<String>();
        for (String action : actionHistory) {
        	myobj.actionHistory.add(new String(action));
        }
        
        myobj.validMoves = new ArrayList<int []>();        
        for (int[] move : validMoves) {
        	myobj.validMoves.add(Arrays.copyOf(move, move.length));
        }
        	
        return myobj;
    }
    
    
    //the setup function of the problem
    public void setup(final EvolutionState state, final Parameter base) {
        
        super.setup(state,base); 

        // verify our input is the right class (or subclasses from it)
        if (!(input instanceof MSolData))
            state.output.fatal("GPData class must subclass from " + MSolData.class, base.push(P_DATA), null);
        state.output.exitIfErrors();
        
        //initialize
        map = new int[NOD][NOD][6];
        validMoves = new ArrayList<int []>();
		moveHistory = new ArrayList<int []>();
		actionHistory = new ArrayList<String>();
        
        clear();
    }
    
    //clears the map and the arraylists and sets everything to its default value
    public void clear() {
    	
		for(int i = 0; i < NOD; i++) 
			for(int j=0; j < NOD; j++)
				for(int k=0; k<6;k++)
					map[i][j][k] = 0;
		
		moveHistory.clear();
		validMoves.clear();
		actionHistory.clear();
		
		score = xCurrent = yCurrent = 0;
		
		//draws the Greek cross at the position given in the figure1 of the assessment description 
		drawCross(X_REF, Y_REF);
		
		update();
    }
	
    //draws the Greek cross relative to the given point. Particularly, sets its nodes' CROSS fields to 1
	public void drawCross(int xstart, int ystart) {
		for (int x = xstart; x < xstart + 4; x++) {
			map[x][ystart + 3][CROSS] = 1;
			map[x][ystart + 6][CROSS] = 1;
			map[x + 6][ystart + 3][CROSS] = 1;
			map[x + 6][ystart + 6][CROSS] = 1;
		}
		
		for (int y = ystart; y < ystart + 3; y++) {
			map[xstart + 3][y][CROSS] = 1;
			map[xstart + 6][y][CROSS] = 1;
			map[xstart + 3][y + 7][CROSS] = 1;
			map[xstart + 6][y + 7][CROSS] = 1;
		}
		
		for(int k=1; k<=2; k++) {
			map[xstart][ystart + 3 + k][CROSS] = 1;
			map[xstart + 9][ystart + 3 + k][CROSS] = 1;
			map[xstart + 3 + k][ystart][CROSS] = 1;
			map[xstart + 3 + k][ystart + 9][CROSS] = 1;
		}
	}
	
	//the update function searches the four directions of a grid and updates the valid moves arraylist
	//it calls the checkNextFive function that follows
	public void update() {
	
		int x, y;
		
		validMoves.clear();
		
		//horizontal - direction 1, EAST
		for (y=0; y<NOD; y++) {
			for(x=0; x<= NOD - 5; x++) {
				int []cnf = checkNextFive(x, y, HORIZONTAL);
				if (cnf != null) validMoves.add(cnf);
			}
		}	
		
		//diagonal positive slope - direction 2, NORTH-EAST
		for (x=0; x < NOD - 5; x++) { 
			for (y=0; y< NOD - 5; y++) {
				if (x>=0 && x < NOD && y >= 0 && y < NOD) {
					int []cnf = checkNextFive(x, y, DIAG_PLUS);
					if (cnf != null) validMoves.add(cnf);					
				}
			}
		}	
		
		//vertical direction 3 NORTH
		for (x=0; x<NOD; x++) { 
			for (y=0; y<= NOD - 5; y++) {
				int []cnf = checkNextFive(x, y, VERTICAL);
				if (cnf != null) validMoves.add(cnf);
			}
		}
		
		//diagonal negative slope - direction 4, NORTH-WEST
		for (x=4; x < NOD ; x++) { 
			for (y=0; y< NOD - 5; y++) {
				if (x>=0 && x < NOD && y >= 0 && y < NOD) {
					int []cnf = checkNextFive(x, y, DIAG_MINUS);
					if (cnf != null) validMoves.add(cnf);					
				}
			}
		}	

	}
	
	//to be a legal movement must have exactly four crosses and no lines at all
	//returns a move as an array integer
	public int[] checkNextFive(int x, int y, int d) {// d direction 1..4
		
		if (d < 1 || d > 4) return null;
		
		int ticks = 0;
		int lines = 0;
		int cind = 0;
		int []a = {};
		
		for(int k=0; k<5; k++) {
			
			switch (d) {
				case HORIZONTAL:	a = map[x + k][y];
									break;
				case DIAG_PLUS: 	a = map[x + k][y + k];
									break;
				case VERTICAL: 		a = map[x][y + k];
									break;
				case DIAG_MINUS:	a = map[x - k][y + k];
									break;
			}
			if (a[d] == 1 && k < 4) lines++;
			if (a[CROSS] == 1) ticks++; else cind = k;
		}
		
		if (lines == 0 && ticks == 4) {
			
			int cx, cy, ex, ey;
			cx = cy = ex = ey = -1;
			
			switch (d) {
				case HORIZONTAL:	cx = x + cind; cy = y; ex = x + 4; ey = y;
									break;
				case DIAG_PLUS: 	cx = x + cind; cy = y + cind ; ex = x + 4; ey = y + 4;
									break;
				case VERTICAL: 		cx = x; cy = y + cind; ex = x ; ey = y + 4;
									break;
				case DIAG_MINUS:	cx = x - cind; cy = y + cind ; ex = x - 4; ey = y + 4;
									break;
			}
			
			return new int[]{x, y, ex, ey, cx, cy, d};
			
		}
		
		return null;
		
	}
	
	// this function adds a move. A move is an integer array of size 7 
	// individual fields are described inside the function
	// the function first searches the valid moves to confirm that the given array corresponds to a valid move
	// we may want purposely to give null value in which case the function does nothing
	// giving a not-null non-valid array indicates unwanted behaviour and prints a message
	public int addMove(int [] move) {
		
		if (move == null || validMoves.isEmpty()) return -1;

		//check to see if it is a valid move
		boolean isValid = false;
		
		for (int vm[] : validMoves) 
			if (Arrays.equals(vm, move)) {isValid = true;break;}
		
		if (!isValid) {System.out.println("Wrong move!!");return -2;}
		
		int sx, sy, ex, ey, cx, cy, d;
		sx = move[0]; //the start point x coordinate
		sy = move[1]; //the start point y coordinate
		ex = move[2]; //the end point x coordinate
		ey = move[3]; //the end point y coordinate
		cx = move[4]; //the cross x coordinate
		cy = move[5]; //the cross y coordinate
		d = move[6]; // the direction of the line (1..4)

		score++;
		map[cx][cy][CROSS] = 1; 		//updates the CROSS field of the node where the cross was added
		map[cx][cy][MOVE] = score;		//stores the number of the move which is the updated score 	

		xCurrent = cx;  				//updates the global variables xCurrent and yCurrent
		yCurrent = cy;
		
		int step_x = (ex - sx) /4;		//sets the step for the loop
		int step_y = (ey - sy) /4;
		
		for(int k=0; k<4; k++) {  		//updates the proper direction field (d = 1..4) of all the nodes of the line added
			map[sx + k*step_x][sy + k * step_y][d] = 1;
		}
		
		moveHistory.add(move); 	//adds the move to the move history
		
		update(); //updates the valid moves
		
		return 1;
	}
	
	//returns the closest move(its cross) to a point in the map
	//in case of equivalence returns deterministically the one with the "smallest" direction
	public int [] getClosestMoveAt(int x, int y) {
		
		if (x<=0 || y<=0) return null;
		
		int minDistance=100; 
		ArrayList<int []> eqMoves = new ArrayList<int []>(); 
		
		for (int[] move : validMoves) {
			int cx = move[4];
			int cy = move[5];
			
			int dist = getDistance(x, y, cx, cy);

			if (dist<minDistance) {
				minDistance = dist;
				eqMoves.clear();
				eqMoves.add(move);
			}
			else if(dist == minDistance) {
				eqMoves.add(move);
			}
		}
		
		if (eqMoves.size() == 0) return null;
		
		if (eqMoves.size() == 1) return eqMoves.get(0);
		
		int [] minDirMove = null;
		int minDir = 10;
		for (int[] move : eqMoves) {
			if (move[6] < minDir) {
				minDir = move[6];
				minDirMove = move;
			}
		}

		return minDirMove;
	}
    
	//evaluate the individual
    public void evaluate(final EvolutionState state, final Individual ind, final int subpopulation, final int threadnum) {
            
        if (ind.evaluated) return; // if it is evaluated return
        
        MSolData input = (MSolData)(this.input);

        clear();
        
        // evaluate the individual
        ((GPIndividual)ind).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)ind),this);

        // we use KozaFitness as is the default of ECJ
        KozaFitness f = ((KozaFitness)ind.fitness);
       
        //our standardized fitness : 200 - score(moveHistory.size())
        f.setStandardizedFitness(state,(float)(200 - moveHistory.size())); 
        f.hits = score; 
        ind.evaluated = true;

    }
    
    //this is used by the statistics class, MSolStatistics, to print the best of the run
    //it prints all the moves in two formats: 
    // 1) as a single string that can be given as input to the executable given to us
    // 2) as many strings in seperate lines that can be read as input by our program 
    public void describe(final EvolutionState state, final Individual ind, final int subpopulation, final int threadnum, final int log) {
  
        state.output.println("\nBest Individual's Map\n=====================", log);
        
        clear();
        moveHistory.clear();
        
        // evaluate the individual
        ((GPIndividual)ind).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)ind),this);
        
        //this is for the executable given, prints all the moves in a single string
        movesForExecutable = "";
        for (int[] m : moveHistory) {
            state.output.print(m[0] + " " + m[1] + " " + m[2] + " " + m[3] + " ", log);
            movesForExecutable += m[0] + " " + m[1] + " " + m[2] + " " + m[3] + " ";
        }
        
        state.output.println("", log);
        state.output.println("\n\nScore=" + moveHistory.size(), log);
        state.output.println("", log);
        state.output.println("", log);
        
        state.output.println("\n\n=====================\n", log);
        
        //the following are to be read by our program
        state.output.println("TO_BE_USED_AS_INPUT", log);
        
        int i=0;
        
        for (int[] m : moveHistory) {
            state.output.println(m[0] + " " + m[1] + " " + m[2] + " " + m[3] + " " + m[4] + " " + m[5] + " " + m[6] + "\taction\t" + actionHistory.get(i++), log);
        }
    }
 }
