#!/bin/bash
echo "RUNNING f1_24"
java ec.Evolve -file funcSet1.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d1/24.stat
cat d1/24global.txt >> global.txt
echo "RUNNING f2_20"
java ec.Evolve -file funcSet2.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d2/20.stat
cat d2/20global.txt >> global.txt
echo "RUNNING f2_15"
java ec.Evolve -file funcSet2.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d2/15.stat
cat d2/15global.txt >> global.txt
echo "RUNNING f1_13"
java ec.Evolve -file funcSet1.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d1/13.stat
cat d1/13global.txt >> global.txt
echo "RUNNING f0_13"
java ec.Evolve -file funcSet0.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d0/13.stat
cat d0/13global.txt >> global.txt
echo "RUNNING f2_26"
java ec.Evolve -file funcSet2.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d2/26.stat
cat d2/26global.txt >> global.txt
echo "RUNNING f1_7"
java ec.Evolve -file funcSet1.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d1/7.stat
cat d1/7global.txt >> global.txt
echo "RUNNING f1_26"
java ec.Evolve -file funcSet1.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d1/26.stat
cat d1/26global.txt >> global.txt
echo "RUNNING f2_2"
java ec.Evolve -file funcSet2.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d2/2.stat
cat d2/2global.txt >> global.txt
echo "RUNNING f1_8"
java ec.Evolve -file funcSet1.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d1/8.stat
cat d1/8global.txt >> global.txt
echo "RUNNING f2_24"
java ec.Evolve -file funcSet2.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d2/24.stat
cat d2/24global.txt >> global.txt
echo "RUNNING f2_17"
java ec.Evolve -file funcSet2.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d2/17.stat
cat d2/17global.txt >> global.txt
echo "RUNNING f2_10"
java ec.Evolve -file funcSet2.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d2/10.stat
cat d2/10global.txt >> global.txt
echo "RUNNING f2_9"
java ec.Evolve -file funcSet2.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d2/9.stat
cat d2/9global.txt >> global.txt
echo "RUNNING f0_23"
java ec.Evolve -file funcSet0.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d0/23.stat
cat d0/23global.txt >> global.txt
echo "RUNNING f0_3"
java ec.Evolve -file funcSet0.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d0/3.stat
cat d0/3global.txt >> global.txt
echo "RUNNING f2_23"
java ec.Evolve -file funcSet2.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d2/23.stat
cat d2/23global.txt >> global.txt
echo "RUNNING f2_22"
java ec.Evolve -file funcSet2.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d2/22.stat
cat d2/22global.txt >> global.txt
echo "RUNNING f0_7"
java ec.Evolve -file funcSet0.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d0/7.stat
cat d0/7global.txt >> global.txt
echo "RUNNING f2_16"
java ec.Evolve -file funcSet2.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d2/16.stat
cat d2/16global.txt >> global.txt
echo "RUNNING f2_13"
java ec.Evolve -file funcSet2.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d2/13.stat
cat d2/13global.txt >> global.txt
echo "RUNNING f2_12"
java ec.Evolve -file funcSet2.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d2/12.stat
cat d2/12global.txt >> global.txt
echo "RUNNING f1_20"
java ec.Evolve -file funcSet1.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d1/20.stat
cat d1/20global.txt >> global.txt
echo "RUNNING f0_4"
java ec.Evolve -file funcSet0.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d0/4.stat
cat d0/4global.txt >> global.txt
echo "RUNNING f1_16"
java ec.Evolve -file funcSet1.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d1/16.stat
cat d1/16global.txt >> global.txt
echo "RUNNING f0_14"
java ec.Evolve -file funcSet0.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d0/14.stat
cat d0/14global.txt >> global.txt
echo "RUNNING f2_4"
java ec.Evolve -file funcSet2.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d2/4.stat
cat d2/4global.txt >> global.txt
echo "RUNNING f2_8"
java ec.Evolve -file funcSet2.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d2/8.stat
cat d2/8global.txt >> global.txt
echo "RUNNING f1_19"
java ec.Evolve -file funcSet1.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d1/19.stat
cat d1/19global.txt >> global.txt
echo "RUNNING f0_25"
java ec.Evolve -file funcSet0.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d0/25.stat
cat d0/25global.txt >> global.txt
echo "RUNNING f1_0"
java ec.Evolve -file funcSet1.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d1/0.stat
cat d1/0global.txt >> global.txt
echo "RUNNING f1_11"
java ec.Evolve -file funcSet1.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d1/11.stat
cat d1/11global.txt >> global.txt
echo "RUNNING f0_9"
java ec.Evolve -file funcSet0.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d0/9.stat
cat d0/9global.txt >> global.txt
echo "RUNNING f0_20"
java ec.Evolve -file funcSet0.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d0/20.stat
cat d0/20global.txt >> global.txt
echo "RUNNING f1_3"
java ec.Evolve -file funcSet1.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d1/3.stat
cat d1/3global.txt >> global.txt
echo "RUNNING f0_0"
java ec.Evolve -file funcSet0.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d0/0.stat
cat d0/0global.txt >> global.txt
echo "RUNNING f1_9"
java ec.Evolve -file funcSet1.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d1/9.stat
cat d1/9global.txt >> global.txt
echo "RUNNING f2_11"
java ec.Evolve -file funcSet2.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d2/11.stat
cat d2/11global.txt >> global.txt
echo "RUNNING f1_17"
java ec.Evolve -file funcSet1.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d1/17.stat
cat d1/17global.txt >> global.txt
echo "RUNNING f0_22"
java ec.Evolve -file funcSet0.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d0/22.stat
cat d0/22global.txt >> global.txt
echo "RUNNING f1_12"
java ec.Evolve -file funcSet1.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d1/12.stat
cat d1/12global.txt >> global.txt
echo "RUNNING f0_6"
java ec.Evolve -file funcSet0.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d0/6.stat
cat d0/6global.txt >> global.txt
echo "RUNNING f0_19"
java ec.Evolve -file funcSet0.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d0/19.stat
cat d0/19global.txt >> global.txt
echo "RUNNING f1_10"
java ec.Evolve -file funcSet1.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d1/10.stat
cat d1/10global.txt >> global.txt
echo "RUNNING f1_14"
java ec.Evolve -file funcSet1.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d1/14.stat
cat d1/14global.txt >> global.txt
echo "RUNNING f1_23"
java ec.Evolve -file funcSet1.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d1/23.stat
cat d1/23global.txt >> global.txt
echo "RUNNING f2_25"
java ec.Evolve -file funcSet2.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d2/25.stat
cat d2/25global.txt >> global.txt
echo "RUNNING f2_7"
java ec.Evolve -file funcSet2.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d2/7.stat
cat d2/7global.txt >> global.txt
echo "RUNNING f1_1"
java ec.Evolve -file funcSet1.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d1/1.stat
cat d1/1global.txt >> global.txt
echo "RUNNING f2_21"
java ec.Evolve -file funcSet2.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d2/21.stat
cat d2/21global.txt >> global.txt
echo "RUNNING f2_1"
java ec.Evolve -file funcSet2.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d2/1.stat
cat d2/1global.txt >> global.txt
echo "RUNNING f0_18"
java ec.Evolve -file funcSet0.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d0/18.stat
cat d0/18global.txt >> global.txt
echo "RUNNING f2_18"
java ec.Evolve -file funcSet2.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d2/18.stat
cat d2/18global.txt >> global.txt
echo "RUNNING f0_5"
java ec.Evolve -file funcSet0.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d0/5.stat
cat d0/5global.txt >> global.txt
echo "RUNNING f1_21"
java ec.Evolve -file funcSet1.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d1/21.stat
cat d1/21global.txt >> global.txt
echo "RUNNING f0_1"
java ec.Evolve -file funcSet0.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d0/1.stat
cat d0/1global.txt >> global.txt
echo "RUNNING f0_8"
java ec.Evolve -file funcSet0.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d0/8.stat
cat d0/8global.txt >> global.txt
echo "RUNNING f0_16"
java ec.Evolve -file funcSet0.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d0/16.stat
cat d0/16global.txt >> global.txt
echo "RUNNING f0_2"
java ec.Evolve -file funcSet0.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d0/2.stat
cat d0/2global.txt >> global.txt
echo "RUNNING f0_12"
java ec.Evolve -file funcSet0.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d0/12.stat
cat d0/12global.txt >> global.txt
echo "RUNNING f0_10"
java ec.Evolve -file funcSet0.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d0/10.stat
cat d0/10global.txt >> global.txt
echo "RUNNING f1_25"
java ec.Evolve -file funcSet1.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d1/25.stat
cat d1/25global.txt >> global.txt
echo "RUNNING f0_17"
java ec.Evolve -file funcSet0.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d0/17.stat
cat d0/17global.txt >> global.txt
echo "RUNNING f2_5"
java ec.Evolve -file funcSet2.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d2/5.stat
cat d2/5global.txt >> global.txt
echo "RUNNING f0_24"
java ec.Evolve -file funcSet0.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d0/24.stat
cat d0/24global.txt >> global.txt
echo "RUNNING f0_15"
java ec.Evolve -file funcSet0.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d0/15.stat
cat d0/15global.txt >> global.txt
echo "RUNNING f2_14"
java ec.Evolve -file funcSet2.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d2/14.stat
cat d2/14global.txt >> global.txt
echo "RUNNING f2_3"
java ec.Evolve -file funcSet2.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d2/3.stat
cat d2/3global.txt >> global.txt
echo "RUNNING f1_6"
java ec.Evolve -file funcSet1.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d1/6.stat
cat d1/6global.txt >> global.txt
echo "RUNNING f2_6"
java ec.Evolve -file funcSet2.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d2/6.stat
cat d2/6global.txt >> global.txt
echo "RUNNING f1_2"
java ec.Evolve -file funcSet1.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d1/2.stat
cat d1/2global.txt >> global.txt
echo "RUNNING f0_26"
java ec.Evolve -file funcSet0.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d0/26.stat
cat d0/26global.txt >> global.txt
echo "RUNNING f1_18"
java ec.Evolve -file funcSet1.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d1/18.stat
cat d1/18global.txt >> global.txt
echo "RUNNING f1_22"
java ec.Evolve -file funcSet1.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d1/22.stat
cat d1/22global.txt >> global.txt
echo "RUNNING f1_15"
java ec.Evolve -file funcSet1.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.5 -p gp.koza.ns.nonterminals=0.5 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d1/15.stat
cat d1/15global.txt >> global.txt
echo "RUNNING f1_4"
java ec.Evolve -file funcSet1.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d1/4.stat
cat d1/4global.txt >> global.txt
echo "RUNNING f0_21"
java ec.Evolve -file funcSet0.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d0/21.stat
cat d0/21global.txt >> global.txt
echo "RUNNING f1_5"
java ec.Evolve -file funcSet1.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.3 -p gp.koza.ns.nonterminals=0.7 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d1/5.stat
cat d1/5global.txt >> global.txt
echo "RUNNING f2_0"
java ec.Evolve -file funcSet2.params -p select.tournament.size=2 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=4 -p gp.koza.half.min-depth=1 -p stat.file=d2/0.stat
cat d2/0global.txt >> global.txt
echo "RUNNING f0_11"
java ec.Evolve -file funcSet0.params -p select.tournament.size=6 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=10 -p gp.koza.half.min-depth=3 -p stat.file=d0/11.stat
cat d0/11global.txt >> global.txt
echo "RUNNING f2_19"
java ec.Evolve -file funcSet2.params -p select.tournament.size=10 -p gp.koza.ns.terminals=0.1 -p gp.koza.ns.nonterminals=0.9 -p gp.koza.half.max-depth=7 -p gp.koza.half.min-depth=2 -p stat.file=d2/19.stat
cat d2/19global.txt >> global.txt
