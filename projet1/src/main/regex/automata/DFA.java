package src.main.regex.automata;

import java.util.ArrayList;
import java.util.HashMap;

public class DFA {
    private final NDFState ndfState;
    public final HashMap<Integer, ArrayList<Integer>> mapper = new HashMap<>();
    public ArrayList<Integer>[][] transitionTable;
    public ArrayList<Integer> finalStates = new ArrayList<>();
    private static int counter = 1;

    public DFA(NDFState ndf) {
        this.ndfState = ndf;
        initTransitionTable();
        toDFA();
    }

    private void initTransitionTable() {
        this.transitionTable = new ArrayList[1][this.ndfState.getTransitionTable()[0].length];
        for(ArrayList<Integer>[] subTab: this.transitionTable) {
            for (int j = 0; j < this.transitionTable[0].length; j++) {
                subTab[j] = new ArrayList<>();
            }
        }
    }

    private void expandingTable() {
        int nbLines = this.transitionTable.length*2, nbCols = this.transitionTable[0].length;
        ArrayList<Integer>[][] newTable = new ArrayList[nbLines][nbCols];
        int index = 0;
        for(ArrayList<Integer>[] subTab: this.transitionTable) {
            System.arraycopy(
                    subTab,
                    0,
                    newTable[index++],
                    0,
                    subTab.length
            );
        }
        index = this.transitionTable.length;
        while (index < newTable.length) {
            for (int j = 0; j < newTable[index].length; j++) {
                newTable[index][j] = new ArrayList<>();
            }
            index++;
        }
        this.transitionTable = newTable;
    }

    private void toDFA() {
        initProcess();
        processingDFA();
        finalSet();
    }

    private void initProcess() {
        ArrayList<Integer> epsilons = new ArrayList<>(0);
        epsilons.addAll(getEpsilons(0));
        for (Integer eps : epsilons) {
            int index = 0;
            while(index < this.ndfState.getTransitionTable()[eps].length) {
                processing(eps, index);
                index++;
            }
        }
        this.mapper.put(0, epsilons);
    }

    private void processing(Integer eps, int i) {
        if (this.ndfState.getTransitionTable()[eps][i] != -1) {
            addElements(0, i, eps);
        }
        boolean flag = false;
        for (ArrayList<Integer> value : this.mapper.values()) {
            ArrayList<Integer> tmp = getArrayList(value, this.transitionTable[0][i]);
            if (value.containsAll(this.transitionTable[0][i]) && tmp.size() == 0) {
                flag = true;
                break;
            }
        }

        if (!this.transitionTable[0][i].isEmpty() && !flag) {
            this.mapper.put(counter, this.transitionTable[0][i]);
            counter++;
        }
    }

    private void addElements(int x, int i, Integer eps) {
        this.transitionTable[x][i].add(this.ndfState.getTransitionTable()[eps][i]);
        this.transitionTable[x][i].addAll(getEpsilons(this.ndfState.getTransitionTable()[eps][i]));
    }

    private void processingDFA() {
        int tmpCounter = 1;
        while (tmpCounter < counter) {
            ArrayList<Integer> epsilons = new ArrayList<>();
            for (int n : this.mapper.get(tmpCounter)) {
                processingEpsilons(epsilons, n);
            }

            if (this.transitionTable.length < tmpCounter + 1)
                expandingTable();

            for (Integer eps : epsilons) {
                for (int i = 0; i < this.ndfState.getTransitionTable()[eps].length; i++) {
                    setStateMap(tmpCounter, eps, i);
                }
            }
            tmpCounter++;
        }

    }

    private void processingEpsilons(ArrayList<Integer> epsilons, int n) {
        if (!epsilons.contains(n))
            epsilons.add(n);
        ArrayList<Integer> lstEps = getEpsilons(n);
        epsilons.removeAll(lstEps);
        epsilons.addAll(lstEps);
    }

    private void setStateMap(int tmpCounter, Integer eps, int i) {
        if (this.ndfState.getTransitionTable()[eps][i] != -1) {
            addElements(tmpCounter, i, eps);
        }

        boolean flag = false;
        for (ArrayList<Integer> value : this.mapper.values()) {
            ArrayList<Integer> removed = getArrayList(value, this.transitionTable[tmpCounter][i]);
            if (value.containsAll(this.transitionTable[tmpCounter][i]) && removed.size() == 0) {
                flag = true;
                break;
            }
        }

        if (!this.transitionTable[tmpCounter][i].isEmpty()
                && !this.mapper.containsValue(this.transitionTable[tmpCounter][i]) && !flag) {
            this.mapper.put(counter, this.transitionTable[tmpCounter][i]);
            counter++;
        }
    }

    private void finalSet() {
        int i = 0;
        for (ArrayList<Integer> transition : this.mapper.values()) {
            i = setFinalStates(i, transition);
        }
    }

    private int setFinalStates(int i, ArrayList<Integer> transition) {
        if (transition.contains(this.ndfState.getTransitionTable().length - 1))
            this.finalStates.add(i, 1);
        else
            this.finalStates.add(i, 0);
        return ++i;
    }

    public int getKey(ArrayList<Integer> values) {
        int result = -1;
        for (Integer key : this.mapper.keySet()) {
            ArrayList<Integer> tmp = getArrayList(this.mapper.get(key), values);
            if (this.mapper.get(key).containsAll(values) && tmp.isEmpty()) {
                result = key;
                break;
            }
        }
        return result;
    }

    private ArrayList<Integer> getArrayList(ArrayList<Integer> mapper, ArrayList<Integer> values) {
        ArrayList<Integer> tmp = new ArrayList<>(mapper);
        tmp.removeAll(values);
        return tmp;
    }

    private ArrayList<Integer> getEpsilons(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> resultTmp = new ArrayList<>();
        if (this.ndfState.getEpsilonTransitionTable()[n].size() != 0) {
            addEpsilons(n, result, resultTmp);
        }
        result.addAll(resultTmp);
        return result;
    }

    private void addEpsilons(int n, ArrayList<Integer> result, ArrayList<Integer> resultTmp) {
        for (Integer eps : this.ndfState.getEpsilonTransitionTable()[n]) {
            if (!result.contains(eps))
                result.add(eps);
        }
        for (int e : result)
            resultTmp.addAll(getEpsilons(e));
    }

    public String toString() {
        StringBuilder str = new StringBuilder("\n");
        for (int i = 0; i < this.transitionTable.length; i++) {
            for (int j = 0; j < this.transitionTable[i].length; j++) {
                if (!this.transitionTable[i][j].isEmpty())
                    str.append(i).append(" -- ").append((char) j).append(" --> ").append(getKey(this.transitionTable[i][j])).append("\n");
            }
        }
        return str.toString();
    }
}