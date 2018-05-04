# EvolutionaryComputationProject
Evolutionary Computation Project


This was a project I did for the module Evolutionary Computation at the University of York.
The aim of the project was to create an artificial player for the game Morpion Solitaire
using an evolutionary algorithm. More about the game can be found here:

http://www.morpionsolitaire.com/  

The Java-based Evolutionary Computation framework ECJ was used:

https://cs.gmu.edu/~eclab/projects/ecj/

I made the Java application solTester to perform an elaborate study of the solutions found. 
The program works in two modes: 

1) As an implementation of the game itself. Green dots indicate valid moves and the user can
click on them. When there is disambiguation about the intended move, the alternative choices are
shown as purple dots. The Start button starts a new game with the grid dimension selected at the 
combo box. If no more moves are available, the game is over. 

2) It accepts a solution found by the main program (several classes working with the ECJ framework)
and reproduces the solution either, step by step (Next Move button) or all at once (All Moves button).
If the solution required more space, the user is prompted to set a larger dimension. The folder
ResultsAndOther contains 3 loadable files: 110.txt, 118.txt and 124.txt. These files contain
the most successful solutions found by my program.

The big yellow dot has not any functional role, only visualizes a point of reference that is used
internally.

The info file is the one submitted with the project describing where the ECJ code must be set to run in the ECJ folders

-----------------------------------------------------------------

The folder contains all the files submitted along with the assessment report (pdf).

The folder msolitaire contains the code used by the ECJ framework.
linuxMsolitaire.exe is an executable implementing the game that was given with the assessment 
to be used by the program.

The folder solTesterCode the code of the executable solTester. 
