package src.main.regex.automata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DFAState {
    private NDFState ndf;
    private HashMap<Integer, ArrayList<Integer>> transitionsMap = new HashMap<>();
    private ArrayList<Integer>[][] transitionTable;
    private ArrayList<Integer> finals = new ArrayList<>();
    private static int cpt = 1;
}
