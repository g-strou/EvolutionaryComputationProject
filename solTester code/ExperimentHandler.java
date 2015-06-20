package solitaire;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class ExperimentHandler {
	
	private String[][] functionSets = { {"MSolERC"}, {"MSolERC", "GetClosestToLast"}};
	
	
	private String [] tournamentSize = {"2", "6", "10"};
	private String [] selTermProb = {"0.1", "0.3", "0.5"};
	private String [] maxDepInitTree =  {"4", "7", "10"};
	
	// Implementing Fisher–Yates shuffle code 
	static void shuffleArray(String[][] ar) {

	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      String [] a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	}
	
	public void makeParamFiles(){
		
		int numOfSets = 2*3*3*3;
		int numOfFuncs = functionSets.length;
		
		String [] fs;
		String ts;
		String stp;
		String sntp;
		String maxdit;
		String mindit;
		
		String[] popt = new String[numOfSets];
		for (int j=0; j<numOfSets; j++) popt[j] = "";

		for (int f=0; f<numOfFuncs; f++) {
			
			fs = functionSets[f];
			
			//make base param files
			try {
				
				PrintWriter pw = new PrintWriter("funcSet" + f + ".params");
				
				pw.println("parent.0 = ../../gp/koza/koza.params");
				pw.println("breed.elite.0 = 2");
				
				pw.println("gp.fs.size = 1");
				pw.println("gp.fs.0.name = f0");
				
				pw.println("gp.fs.0.size = " + (fs.length+2));//plus progn2, progn3
				
				pw.println("gp.fs.0.func.0 = ec.app.msolitaire.func.Progn2");
				pw.println("gp.fs.0.func.0.nc = nc2");
				pw.println("gp.fs.0.func.1 = ec.app.msolitaire.func.Progn3");
				pw.println("gp.fs.0.func.1.nc = nc3");
				
				for(int i=0; i<fs.length; i++) {
					pw.println("gp.fs.0.func." + (i+2)  + "= ec.app.msolitaire.func." + fs[i]);
					pw.println("gp.fs.0.func." + (i+2)  + ".nc = nc0");					
				}
				
				pw.println("eval.problem = ec.app.msolitaire.MSol");
				pw.println("eval.problem.data = ec.app.msolitaire.MSolData");
				pw.println("stat = ec.app.msolitaire.MSolStatistics");
				
				pw.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		int counter = 0;
		
		for (int t=1; t<=2; t++) {//two times each
		
			for(int p1=0; p1<3; p1++) {
				
				ts = tournamentSize[p1];
	
				for (int p2=0; p2<3 ; p2++) {
					
					stp = selTermProb[p2];
					sntp = String.valueOf(1 - Float.valueOf(stp));
					
					for(int p3=0; p3<3; p3++) {
						
						maxdit = maxDepInitTree[p3];
						mindit = String.valueOf(Integer.valueOf(maxdit) / 3);
						
						popt[counter] += " -p " + "select.tournament.size=" + ts;
						popt[counter] += " -p " + "gp.koza.ns.terminals=" + stp;
						popt[counter] += " -p " + "gp.koza.ns.nonterminals=" + sntp;
						popt[counter] += " -p " + "gp.koza.half.max-depth=" + maxdit;
						popt[counter] += " -p " + "gp.koza.half.min-depth=" + mindit;
						
						counter++;
	
					}
				
				}
				
			}
		}
		
		String [][]bashEntries = new String[numOfFuncs*numOfSets][3]; 
		int c2 = 0;
		
		for(int i=0; i< numOfFuncs; i++) {
			for(int j=0; j< numOfSets; j++) {
				String statsDir = "d" + i +"/";
				bashEntries[c2][0] = "echo \"RUNNING f" + i + "_" + j + "\"";
				bashEntries[c2][1] = "java ec.Evolve -file funcSet" + i + ".params"+ popt[j] + " -p stat.file=" + statsDir + j + ".stat";
				bashEntries[c2][2] = "cat " + statsDir + j + "global.txt >> global.txt";
				c2++;
			}
		}
		
		//shuffleArray(bashEntries);
		
		//make bash file
		try {
			PrintWriter bw = new PrintWriter("runFile.sh");
			bw.println("#!/bin/bash");
			for(int i=0; i< numOfFuncs*numOfSets; i++) {
				for (String s: bashEntries[i]) {
					bw.println(s);
				}
			}
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		
		
	}
	
	public void makeSecondTest() {
		
		int reps =50;
		
		int numOfFuncs = functionSets.length;
		
		String [][]bashEntries = new String[numOfFuncs*reps][3]; 
		int c2 = 0;
		
		for(int i=0; i< numOfFuncs; i++) {
			for(int j=0; j< reps; j++) {
	
				bashEntries[c2][0] = "echo \"RUNNING f" + i + "_" + j + "\"";
				bashEntries[c2][1] = "java ec.Evolve -file optimFunc" + i + ".params" + " -p stat.file=dopt/" + i + "_" + j + ".stat";
				bashEntries[c2][2] = "cat dopt/" + i + "_" + j + "global.txt >> global.txt";
				c2++;
			}
		}
		
		shuffleArray(bashEntries);
		
		//make bash file
		try {
			PrintWriter bw = new PrintWriter("runOptims.sh");
			bw.println("#!/bin/bash");
			for(int i=0; i< numOfFuncs*reps; i++) {
				for (String s: bashEntries[i]) {
					bw.println(s);
				}
			}
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
}
