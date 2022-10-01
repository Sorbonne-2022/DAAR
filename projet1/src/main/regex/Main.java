package src.main.regex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static src.main.regex.NDF.toNDFAutomaton;

public class Main {
    private static String regEx;
    private static String text;

    private static RegEx regExComponent = new RegEx();

    public static String readFileAsString(String filePath) {
        StringBuilder fileData = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(filePath));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData.toString();
    }

    //MAIN
    public static void main(String arg[]) throws IOException {
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

                System.out.println("  >> NDF result: " + ndfState.toString() + ".");
            } catch (Exception e) {
                System.err.println("  >> ERROR: syntax error for regEx \"" + regEx + "\".");
            }
        }

        System.out.println("  >> ...");
        System.out.println("  >> Parsing completed.");
    }
}
