package src.main.regex.bm;

import java.io.IOException;
import java.util.Scanner;

import static src.main.regex.commons.FileHelper.readFileAsString;

public class Main {

    private static String factor;
    private static String text;

    //MAIN
    public static void main(String[] arg) throws IOException {
        if (arg.length > 1) {
            factor = arg[0];
            text = readFileAsString(arg[1]);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("  >> Please enter a factor - regex concatenation : ");
            factor = scanner.next();
        }
        System.out.println("  >> Factor: \"" + factor + "\".");
        System.out.println("  >> Your file path: " + arg[1]);

        BM bm = new BM();

        String[] lines = text.split("\n");

        int lineLen = lines.length;

        for (int i = 0; i < lineLen; i++) {
            if (bm.search(lines[i].toCharArray(), factor.toCharArray())) {
                System.out.println("  >> Line " + (i + 1) + " : " + lines[i]);
            }
        }
        
        System.out.println("  >> ...");

        if (factor.length() < 1) {
            System.err.println("  >> ERROR: empty factor.");
        } else {
        }

        System.out.println("  >> ...");
        System.out.println("  >> Search completed.");
    }
}
