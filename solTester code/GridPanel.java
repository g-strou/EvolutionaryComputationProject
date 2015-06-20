package solitaire;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {
	
	public static int panelSize = 650;
	public static int margin = 5;
	
	public static final float DOT_RADIUS = (float) 0.2;
	public static final int LINE_WIDTH = 2;
	
	private MouseListener mouseHandler;
	private JTextArea textArea;
	
	private int numOfDots; //per edge
	
    private Point2D scale;
    private Point2D translation;
    private AffineTransform transformWorldToScreen = new AffineTransform();
    private AffineTransform transformScreenToWorld;
    private ArrayList<int[]> alternativeMoves = new ArrayList<int[]>();
    
    private ArrayList<int[]> loadedMoves;
    private ArrayList<String> loadedActions;

   	private int mode;
    
    private Grid grid;
	
	public GridPanel(JTextArea ta) {
		
		this.textArea = ta;
		this.setPreferredSize(new Dimension(panelSize, panelSize));

		mouseHandler = new MouseHandler();
		addMouseListener(mouseHandler);
	}
	
	public void addMouseHandler() {
		addMouseListener(mouseHandler);
	}
	
	public void removeMouseHandler() {
		removeMouseListener(mouseHandler);
	}
	
	public void startNewGame(int nod) {
			
		numOfDots = nod;
		grid = new Grid(numOfDots);
		makeTransforms();		
		mode = 1;
		
		repaint();
	}
	
	public void loadMoves(String filename) {
		
		grid = new Grid(numOfDots);
		
		int maxDim = -1;
		int minDim = 100;
		
		BufferedReader br;
		loadedMoves = new ArrayList<int[]>();
		loadedActions = new ArrayList<String>();
		
		File file = new File(filename);
		
		int dimShift;
		switch (numOfDots) {
			case 21: dimShift = 10;
				break;
			case 31: dimShift = 5;
				break;
			default: dimShift = 0;
		}

		try {
			String line;
			br = new BufferedReader(new FileReader(file));
			
			while ((line = br.readLine()) != null && !(line.trim().equals("TO_BE_USED_AS_INPUT"))); //search for TO_BE_USED_AS_INPUT string
			
			while ((line = br.readLine()) != null) {
				
				String [] twoParts = line.split("action");
				
				String [] fields = twoParts[0].split("\\s+");

				int [] m = new int[fields.length];
				
				for (int i = 0; i<6; i++) {
					m[i] = Integer.valueOf(fields[i]) - dimShift;
					
					if (m[i] > maxDim) maxDim = m[i];
					if (m[i] < minDim) minDim = m[i];
				}
				m[6] = Integer.valueOf(fields[6]);
				
				if (twoParts.length >1) {
					loadedActions.add(twoParts[1].trim());
				}
				
				
				loadedMoves.add(m);
				
				
				repaint();

	
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not find " + file.getAbsolutePath());
		}

		if (maxDim>= numOfDots-1 || minDim <=0) {
			String message = "Not enough space. Must resize the grid";
			JOptionPane.showMessageDialog(this, message);
		}
		
	}
	

	public void nextMove() {
		if (loadedMoves != null) {
			if (loadedMoves.size() > 0) {
				int [] move = loadedMoves.get(0);
				loadedMoves.remove(0);
				
				if (!loadedActions.isEmpty()) {
					String action = loadedActions.get(0);
					loadedActions.remove(0);
					textArea.append(action +"\n");
				}

				grid.addMove(move);
				repaint();
			}
		}
			
	}
	
	public void allMoves() {
		
		if(loadedMoves==null) return;
		
		for (int [] move: loadedMoves) {
			if (grid.addMove(move) < 0) return;
		}
		
		repaint();
		
	}
	
	public void makeTransforms() {
		
		scale = new Point2D.Double((double)((panelSize - 2.0*margin)/(numOfDots-1)), (double)((panelSize - 2.0*margin)/(numOfDots-1)));
		translation = new Point2D.Double(0.0, 0.0);
		
		
        AffineTransform transformWorldToScreenUninverted = new AffineTransform(scale.getX(), 0, 0, scale.getY(), translation.getX() + margin, translation.getY()+ margin);
        transformWorldToScreen.setTransform(1, 0, 0, -1, 0, panelSize);
        //transformWorldToScreenUninverted.setTransform
        transformWorldToScreen.concatenate(transformWorldToScreenUninverted);
        try {
            transformScreenToWorld = transformWorldToScreen.createInverse();
	       }
	    catch(Exception ex) {
	        System.out.println("Could not create inverse transformation matrix");
	        ex.printStackTrace();
	    }
	}
	
	public void paintComponent(Graphics gr) {
		
		Graphics2D g = (Graphics2D) gr;
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		paintGrid(g);
		paintNodes(g);
		
	}

	 private void paintGrid(Graphics2D g) {
		 
		 g.setColor(new Color(255, 0, 0));
		 
		 for (int i = 0; i < numOfDots; i++) {
			 Line2D verticalLine = new Line2D.Double(i, 0, i, numOfDots-1);
			 Line2D horizontalLine = new Line2D.Double(0, i, numOfDots-1, i);
			 Shape convertedVerticalLine = transformWorldToScreen.createTransformedShape(verticalLine);
			 Shape convertedHorizontalLine = transformWorldToScreen.createTransformedShape(horizontalLine);
			 
			 g.draw(convertedHorizontalLine);
			 g.draw(convertedVerticalLine);
		 
		 }

	 }
	 
	 private void paintNodes(Graphics2D g) {
		 
		 g.setColor(Color.WHITE);
		 g.fillRect(0, 0, 100, 30);
		 
		 //drawAnchorPoint
		 g.setColor(Color.YELLOW);
		 Shape zeroCircle = new Ellipse2D.Float(grid.xZero-2*DOT_RADIUS, grid.yZero-2* DOT_RADIUS, 4*DOT_RADIUS, 4*DOT_RADIUS);
		 g.fill(transformWorldToScreen.createTransformedShape(zeroCircle));
		 
		 g.setColor(Color.BLACK);
		 
		 g.drawString("Score: " + grid.score, 20, 20);
		 
		 g.setStroke(new BasicStroke(LINE_WIDTH));
		 
		 g.setFont(new Font(null, Font.PLAIN, 8));
		 
		 int [][][] map = grid.map;
		 
		 for (int x =0; x < numOfDots; x++) {
			 for (int y =0; y < numOfDots; y++) {
				 if (map[x][y][Grid.CROSS] == 1) {
					 Shape circle = new Ellipse2D.Float(x-DOT_RADIUS, y-DOT_RADIUS, 2*DOT_RADIUS, 2*DOT_RADIUS);
					 g.fill(transformWorldToScreen.createTransformedShape(circle));
				 }
				 if (map[x][y][Grid.HORIZONTAL] == 1) {
					 Shape line = new Line2D.Float(x, y, x + 1 , y);
					 g.draw(transformWorldToScreen.createTransformedShape(line));
				 }
				 if (map[x][y][Grid.DIAG_PLUS] == 1) {
					 Shape line = new Line2D.Float(x, y, x + 1 , y + 1);
					 g.draw(transformWorldToScreen.createTransformedShape(line));
				 }
				 if (map[x][y][Grid.VERTICAL] == 1) {
					 Shape line = new Line2D.Float(x, y, x, y + 1);
					 g.draw(transformWorldToScreen.createTransformedShape(line));
				 }
				 if (map[x][y][Grid.DIAG_MINUS] == 1) {
					 Shape line = new Line2D.Float(x, y, x - 1 , y + 1);
					 g.draw(transformWorldToScreen.createTransformedShape(line));
				 }
			 }
		 }
		 
		 g.setColor(Color.GREEN);
		 for (int x =0; x < numOfDots; x++) {
			 for (int y =0; y < numOfDots; y++) {
				 
				 if(map[x][y][Grid.MOVE] >0) {
					 Point pw = new Point(x,y);
					 Point2D ps = transformWorldToScreen.transform(pw, null);
				
					 g.drawString(String.valueOf(map[x][y][Grid.MOVE]), (int)(ps.getX() - 6), (int)(ps.getY()+3));
				 }
			 }
		 }
		 g.setColor(Color.BLACK);
		 
		//hints
		 
		 
		 if (mode ==1) {
			 
			 g.setColor(Color.GREEN);
			 
			 ArrayList<int []> moves = grid.validMoves;
			 
			 for (int[] m : moves) {
	
				 Shape circle = new Ellipse2D.Float(m[4]-DOT_RADIUS, m[5]-DOT_RADIUS, 2*DOT_RADIUS, 2*DOT_RADIUS);
				 g.fill(transformWorldToScreen.createTransformedShape(circle));
	
			 }
		 }
		 else { //mode = 2
			 
			 for (int [] m : alternativeMoves) {
				 
				 g.setColor(Color.GREEN);
				 
				 Shape circle1 = new Ellipse2D.Float(m[4]-DOT_RADIUS, m[5]-DOT_RADIUS, 2*DOT_RADIUS, 2*DOT_RADIUS);
				 g.fill(transformWorldToScreen.createTransformedShape(circle1));
				 
				 g.setColor(Color.MAGENTA);
				 
				 Shape circle2 = new Ellipse2D.Float(m[0]-DOT_RADIUS, m[1]-DOT_RADIUS, 2*DOT_RADIUS, 2*DOT_RADIUS);
				 g.fill(transformWorldToScreen.createTransformedShape(circle2));
				 
			 }
			 
		 }
		 

		 
	 }
	 
	 class MouseHandler implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
			
            int x0Screen = e.getX();
            int y0Screen = e.getY();
            
            Point2D.Double worldVec = (Point2D.Double)transformScreenToWorld.transform(new Point2D.Double(x0Screen, y0Screen), null);

            if (mode == 1) {
            	
            	alternativeMoves.clear();
            	
	            for(int []m : grid.validMoves) {
	            	if (Math.pow(worldVec.getX() - m[4], 2) + Math.pow(worldVec.getY() - m[5], 2) < DOT_RADIUS * DOT_RADIUS) {	
	            		alternativeMoves.add(m);
	            	}
	            }

        		if (alternativeMoves.size() == 1) 
        			grid.addMove(alternativeMoves.get(0));
        		else if (alternativeMoves.size() > 1) 
        			mode = 2;
        		
        		repaint();
            }
            else { //mode =2
            	
            	for(int []m : alternativeMoves) {
	            	if (Math.pow(worldVec.getX() - m[0], 2) + Math.pow(worldVec.getY() - m[1], 2) < DOT_RADIUS * DOT_RADIUS) {
	            		
	            		grid.addMove(m);
	            		mode = 1;
	            		repaint();
	            	}
	            }
	            		
            }

		}
		 
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	 
	 }



}
