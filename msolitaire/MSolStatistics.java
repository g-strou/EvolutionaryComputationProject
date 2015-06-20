package ec.app.msolitaire;
import ec.*;
import ec.simple.*;
import ec.gp.*;
import ec.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//our statistics class extends SimpleStatistics and creates 3 extra files
public class MSolStatistics extends SimpleStatistics {
	
	long startTime;
	String statFileName;
	
    public int dotLog = 0;  //prints the genotype tree of the best if the run in a dot file
    public int bestLog = 0; //prints the best of the run in various forms
    public int globalLog = 0; //used to print only score and run time in a single line
	
	 public void setup(final EvolutionState state, final Parameter base)
     {
		 super.setup(state,base);
		 
		 //keep a record of the start time, we will print the total run time at the end
		 startTime = System.currentTimeMillis();
		 
		 File statisticsFile = state.parameters.getFile(base.push(P_STATISTICS_FILE),null);
		 
		 try {
			DateFormat dateFormat = new SimpleDateFormat("_ddHHmm");
			String timeString = dateFormat.format(Calendar.getInstance().getTime());
			statFileName = statisticsFile.getCanonicalPath(); //the .params file name
			
			timeString = "";
			
			String dotFileName = statFileName.replaceAll(".stat", timeString + ".dot");
			String bestFileName = statFileName.replaceAll(".stat", timeString + "_best.txt");
			String globalFileName = statFileName.replaceAll(".stat", "global.txt");
			
			File dotFile = new File(dotFileName);
			File bestFile = new File(bestFileName );
			File globalFile = new File(globalFileName);
			
			dotLog = state.output.addLog(dotFile, true, false);
			bestLog = state.output.addLog(bestFile, true, false);
			globalLog = state.output.addLog(globalFile, true, false);
			
			//read parameter values from the params file and write them to bestLog
			int popSize = state.parameters.getInt(new Parameter("pop.subpop.0.size"), null); 
			int generations = state.parameters.getInt(new Parameter("generations"), null); 
			double selTerm = state.parameters.getDouble(new Parameter("gp.koza.ns.terminals"), null); 
			double selNonTerm = state.parameters.getDouble(new Parameter("gp.koza.ns.nonterminals"), null); 
			int initMinDepth = state.parameters.getInt(new Parameter("gp.koza.half.min-depth"), null);
			int initMaxDepth = state.parameters.getInt(new Parameter("gp.koza.half.max-depth"), null);
			int tournamentSize = state.parameters.getInt(new Parameter("select.tournament.size"), null); 

			state.output.println("popSize= " + popSize, bestLog);
			state.output.println("generations= " + generations, bestLog);
			state.output.println("initMaxDepth= " + initMaxDepth , bestLog);
			state.output.println("initMinDepth= " + initMinDepth , bestLog);
			state.output.println("selTerm= " + selTerm, bestLog);
			state.output.println("selNonTerm=" + selNonTerm, bestLog);
			state.output.println("tournamentSize= " + tournamentSize, bestLog);
			state.output.println("\n\n", bestLog);
		 }
		 catch (IOException i) {
			 state.output.fatal("An IOException occurred while trying to create the dotLog file:\n" + i);
         }
     }

	
    /** Logs the best individual of the run. */
    public void finalStatistics(final EvolutionState state, final int result)
        {
        super.finalStatistics(state,result);
 
        GPIndividual bestInd = (GPIndividual)best_of_run[0].clone();
        GPTree tree = (GPTree)bestInd.trees[0].clone();
        
        //this is for the dot file
        tree.printStyle = GPTree.PRINT_STYLE_DOT;
        tree.printTreeForHumans(state,dotLog);
        
        long runTime = System.currentTimeMillis() - startTime;
        
        if (state.evaluator.p_problem instanceof SimpleProblemForm) {
        	
        	SimpleProblemForm p =  ((SimpleProblemForm)(state.evaluator.p_problem.clone()));
        	p.describe(state, best_of_run[0], 0, 0, bestLog);  
        	
        	String[] fields = statFileName.split("/");
        	String entry = fields[fields.length -2] + "\t" + fields[fields.length -1].replace(".txt", "");
        	
            state.output.println(entry + "\trunTime= " + runTime + "\tscore=" + ((MSol)p).score, globalLog);

        }
        
        
    }
}