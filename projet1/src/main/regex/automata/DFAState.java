package src.main.regex.automata;

import java.util.ArrayList;
import java.util.HashMap;

public class DFAState {
    private NDFState ndf;
    private HashMap<Integer, ArrayList<Integer>> transitionsMap = new HashMap<>();
    private ArrayList<Integer>[][] transitionTable;
    private ArrayList<Integer> finals = new ArrayList<>();
    private static int cpt = 1;

    public ArrayList<Integer>[][] getTransitionTable() {
        return transitionTable;
    }

    public ArrayList<Integer> getFinals() {
        return finals;
    }

    public DFAState(NDFState ndf) {
        this.ndf = ndf;
        this.transitionTable = new ArrayList[1][this.ndf.getTransitionTable()[0].length];
        for (int i = 0; i < this.transitionTable.length; i++) {
            for (int j = 0; j < this.transitionTable[0].length; j++) {
                this.transitionTable[i][j] = new ArrayList<>();
            }
        }
    }

    public int getKeyFromValues(ArrayList<Integer> values) {
        for (Integer key : this.transitionsMap.keySet()) {
            ArrayList<Integer> removed = new ArrayList<>();
            removed.addAll(this.transitionsMap.get(key));
            removed.removeAll(values);
            if (this.transitionsMap.get(key).containsAll(values) && removed.isEmpty())
                return key;
        }
        return -1;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("{\n  0 : " + this.transitionsMap.get(0) + " : S");
        if (this.finals.get(0) == 1)
            str.append(" | F");
        for (Integer key : this.transitionsMap.keySet()) {
            if (key == 0)
                continue;
            str.append("\n  ").append(key.toString()).append(" : ").append(this.transitionsMap.get(key));
            if (this.finals.get(key) == 1)
                str.append(" : F");
        }

        str.append("\n}\n\nTransition list:\n");
        for (int i = 0; i < this.transitionTable.length; i++) {
            for (int j = 0; j < this.transitionTable[i].length; j++) {
                if (!this.transitionTable[i][j].isEmpty())
                    str.append(i).append(" -- ").append((char) j).append(" --> ").append(getKeyFromValues(this.transitionTable[i][j])).append("\n");
            }
        }
        return str.toString();
    }
}
