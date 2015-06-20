package solitaire;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MSolitaire extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public static int FRAME_WIDTH = 660 + 200;
	public static int FRAME_HEIGHT = 660;
	
	public static String ECJ_FOLDER = "/home/george/evco/ecj";
	public static String PARAMS_FILE = "ec/app/msolitaire/custom.params";
	
	private GridPanel gridPanel;
	private JTextArea textArea;
	
	public MSolitaire() {
		super("MorpionSolitaire");
		
		final Container pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
		
        textArea = new JTextArea(24, 18);
        
        gridPanel = new GridPanel(textArea);
        pane.add(gridPanel, BorderLayout.CENTER);
        gridPanel.startNewGame(21);
        
        
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        
        sidePanel.add(makeControlPanel());
        
        pane.add(sidePanel, BorderLayout.WEST);
        
        this.setSize(FRAME_WIDTH  ,FRAME_HEIGHT);
        this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public JPanel makeControlPanel() {
		
		final JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(FRAME_WIDTH - GridPanel.panelSize, 100));

        Integer [] values = {21,31, 41};
        final JComboBox cmbBox = new JComboBox(values);
        
        final JButton btnStart = new JButton("Start");
        final JButton btnAllMoves = new JButton("All Moves");
        final JButton btnNextMove = new JButton("Next Move");
        btnNextMove.setEnabled(false);
        btnAllMoves.setEnabled(false);
        
        final JLabel lblMouseHandler = new JLabel("MouseListener OFF");
      
        JScrollPane scroll = new JScrollPane(textArea);
        
        btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int d = (Integer)cmbBox.getSelectedItem();
		        gridPanel.startNewGame(d);
		        gridPanel.addMouseHandler();
		        lblMouseHandler.setText("MouseListener ON");
		        btnNextMove.setEnabled(false);
		    	btnAllMoves.setEnabled(false);
			}
        });
        
        cmbBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(ActionListener a: btnStart.getActionListeners()) {
				    a.actionPerformed(arg0);
				}
				
			}
        });
 
        btnNextMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridPanel.nextMove();
				
			}
        });
        
        btnAllMoves.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridPanel.allMoves();
				
			}
        });
          
        JButton btnLoadMoves = new JButton("LoadMoves");

        btnLoadMoves.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("ECJ stat files", "stat", "txt");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(panel);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	File selectedFile = chooser.getSelectedFile();
			    	textArea.setText("");
			    	gridPanel.loadMoves(selectedFile.getAbsolutePath());
			    	gridPanel.removeMouseHandler();
			    	lblMouseHandler.setText("MouseListener OFF");
			    	btnNextMove.setEnabled(true);
			    	btnAllMoves.setEnabled(true);
			     }
				
			}
        });        

        
       JButton btnExperiment = new JButton("Make Experiment");
       btnExperiment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ExperimentHandler eh = new ExperimentHandler();
				//eh.makeParamFiles();
				eh.makeSecondTest();
			}
       });
       btnExperiment.setEnabled(false);
        
        panel.add(cmbBox);
        panel.add(btnStart);
        panel.add(btnLoadMoves);
        
        panel.add(btnNextMove);
        
        panel.add(btnAllMoves);

        panel.add(lblMouseHandler);
        panel.add(scroll);
        
        panel.add(btnExperiment);
        
		return panel;
	}
	
	

	public static void main(String[] args) {
		new MSolitaire();
	}
	
}
