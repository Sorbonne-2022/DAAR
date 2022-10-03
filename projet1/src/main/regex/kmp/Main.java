package src.main.regex.kmp;

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

        long start = System.currentTimeMillis();

        KMP kmp = new KMP(factor);

        String[] lines = text.split("\n");

        int lineLen = lines.length;

        for (int i = 0; i < lineLen; i++) {
            kmp.setText(lines[i]);
            if (kmp.searchStringInText()) {
                System.out.println("  >> Line " + (i + 1) + " : " + lines[i]);
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("  >> Time complexity " + (end - start));


        System.out.println("  >> ...");

        if (factor.length() < 1) {
            System.err.println("  >> ERROR: empty factor.");
        } else {
        }

        System.out.println("  >> ...");
        System.out.println("  >> Search completed.");
    }
}
