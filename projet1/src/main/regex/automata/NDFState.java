package src.main.regex.automata;

import java.util.ArrayList;

class NDFState {
    //IMPLICIT REPRESENTATION HERE: INIT STATE IS ALWAYS 0; FINAL STATE IS ALWAYS transitionTable.length-

    protected int[][] transitionTable; //ASCII transition
    protected ArrayList<Integer>[] epsilonTransitionTable; //epsilon transition list

    public int[][] getTransitionTable() {
        return transitionTable;
    }

    public ArrayList<Integer>[] getEpsilonTransitionTable() {
        return epsilonTransitionTable;
    }

    public NDFState(int[][] transitionTable, ArrayList<Integer>[] epsilonTransitionTable) {
        this.transitionTable = transitionTable;
        this.epsilonTransitionTable = epsilonTransitionTable;
    }

    //PRINT THE AUTOMATON TRANSITION TABLE
    public String toString() {
        String result = "Initial state: 0\nFinal state: " + (transitionTable.length - 1) + "\nTransition list:\n";
        for (int i = 0; i < epsilonTransitionTable.length; i++)
            for (int state : epsilonTransitionTable[i])
                result += "  " + i + " -- epsilon --> " + state + "\n";
        for (int i = 0; i < transitionTable.length; i++)
            for (int col = 0; col < 256; col++)
                if (transitionTable[i][col] != -1)
                    result += "  " + i + " -- " + (char) col + " --> " + transitionTable[i][col] + "\n";
        return result;
    }
}