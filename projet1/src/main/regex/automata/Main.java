package src.main.regex.automata;

import java.io.IOException;
import java.util.Scanner;

import static src.main.regex.automata.NDF.toNDFAutomaton;
import static src.main.regex.commons.FileHelper.readFileAsString;

public class Main {
    private static String regEx;
    private static String text;
    private static final RegEx regExComponent = new RegEx();

    //MAIN
    public static void main(String[] arg) throws IOException {
        if (arg.length > 1) {
            regEx = arg[0];
            text = readFileAsString(arg[1]);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("  >> Please enter a regEx: ");
            regEx = scanner.next();
        }
        System.out.println("  >> Parsing regEx: \"" + regEx + "\".");
        System.out.println("  >> Your file path: " + arg[1]);
        System.out.println("  >> ...");

        if (regEx.length() < 1) {
            System.err.println("  >> ERROR: empty regEx.");
        } else {
            System.out.print("  >> ASCII codes: [" + (int) regEx.charAt(0));
            for (int i = 1; i < regEx.length(); i++) System.out.print("," + (int) regEx.charAt(i));
            System.out.println("].");
            try {
                RegExTree ret = regExComponent.parse(regEx);
                System.out.println("  >> Tree result: " + ret.toString() + ".");

                NDFState ndfState = toNDFAutomaton(ret);

                assert ndfState != null;
                System.out.println("  >> NDF result: " + ndfState.toString() + ".");
            } catch (Exception e) {
                System.err.println("  >> ERROR: syntax error for regEx \"" + regEx + "\".");
            }
        }

        System.out.println("  >> ...");
        System.out.println("  >> Parsing completed.");
    }
}
