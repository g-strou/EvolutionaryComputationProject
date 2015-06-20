package solitaire;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
	
    public static final int CROSS = 0;
    public static final int HORIZONTAL = 1;
    public static final int DIAG_PLUS = 2;
    public static final int VERTICAL = 3;
    public static final int DIAG_MINUS = 4;
    public static final int MOVE = 5;
	
    //public static final String [] DIR_STRING= {"", "HORIZONTAL", "DIAG_PLUS", "VERTICAL", "DIAG_MINUS"  };
    
	private int mapSize; //number of nodes per side
	public int[][][] map;
	
    public int xZero;
    public int yZero;

    public int yCurrent;
    public int xCurrent;
    
	public ArrayList<int[]> validMoves;
 
	public int score;
	public ArrayList<int []> moveHistory; 

	
	public Grid(int n) {
		
		mapSize = n;
		map = new int[mapSize][mapSize][6];
		
		for(int i = 0; i < mapSize; i++) 
			for(int j=0; j < mapSize; j++)
				for(int k=0; k<6;k++)
					map[i][j][k] = 0;
		
		moveHistory = new ArrayList<int []>();
		score = 0;
		
		yCurrent = xCurrent = 0;
		

		switch (mapSize) {
			case 21:	xZero = 6; yZero = 7;
						break;
			case 31:	xZero = 11; yZero = 12;
						break;
			case 41:	xZero = 16; yZero = 17;
						break;
			default: 	xZero = yZero = (mapSize-10)/2;
						break;
		}
		
		drawCross(xZero, yZero);
		
		update();
		
	}
	
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

	public void update() {
		
		int x, y;
		
		validMoves = new ArrayList<int []>();
		
		//horizontal - direction 1, EAST
		for (y=0; y<mapSize; y++) {
			for(x=0; x<= mapSize - 5; x++) {
				int []cnf = checkNextFive(x, y, HORIZONTAL);
				if (cnf != null) validMoves.add(cnf);
			}
		}	
		
		//diagonal positive slope - direction 2, NORTH-EAST
		for (x=0; x < mapSize - 5; x++) { 
			for (y=0; y< mapSize - 5; y++) {
				if (x>=0 && x < mapSize && y >= 0 && y < mapSize) {
					int []cnf = checkNextFive(x, y, DIAG_PLUS);
					if (cnf != null) validMoves.add(cnf);					
				}
			}
		}	
		
		//vertical direction 3 NORTH
		for (x=0; x<mapSize; x++) { 
			for (y=0; y<= mapSize - 5; y++) {
				int []cnf = checkNextFive(x, y, VERTICAL);
				if (cnf != null) validMoves.add(cnf);
			}
		}
		
		//diagonal negative slope - direction 4, NORTH-WEST
		for (x=4; x < mapSize ; x++) { 
			for (y=0; y< mapSize - 5; y++) {
				if (x>=0 && x < mapSize && y >= 0 && y < mapSize) {
					int []cnf = checkNextFive(x, y, DIAG_MINUS);
					if (cnf != null) validMoves.add(cnf);					
				}
			}
		}	
		
		
		//System.out.println("update: " + validMoves.size());
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
		
		update(); //updates
		
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
    
	
	public static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
	}

}
