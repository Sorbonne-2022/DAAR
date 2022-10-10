package src.main.regex.kmp;

import src.main.regex.commons.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static src.main.regex.commons.FileUtils.readFileAsString;

public class Main {
    private static String factor;
    private static String text;

    private static String outFilePath = "";
    private static List<String> inputFilePaths = new ArrayList<>();

    //MAIN
    public static void main(String[] arg) throws IOException {
        if (arg.length > 2) {
            factor = arg[0];
            inputFilePaths = FileUtils.findAllFilesInFolder(arg[1]);

            FileUtils.deleteFile(arg[2]);

            for (String path : inputFilePaths) {
                text = readFileAsString(arg[1] + "/" + path);

                System.out.println("  >> Factor: \"" + factor + "\".");
                System.out.println("  >> Your file path: " + arg[1] + "/" + path);

                KMP kmp = new KMP(factor);

                String[] lines = text.split("\n");

                int lineLen = lines.length;

                if (arg[2] == null) {
                    outFilePath = "./data/kmp/txt";
                }

                long start = System.currentTimeMillis();

                for (int i = 0; i < lineLen; i++) {
                    kmp.setText(lines[i]);
                    if (kmp.searchStringInText()) {
                        System.out.println("  >> Line " + (i + 1) + " : " + lines[i]);
                    }
                }

                long end = System.currentTimeMillis();

                FileUtils.writeFile(arg[2], text.length() + " " + (end - start) + "\n");

                System.out.println("  >> ...");

                if (factor.length() < 1) {
                    System.err.println("  >> ERROR: empty factor.");
                }

                System.out.println("  >> ...");
                System.out.println("  >> Search completed.");
            }
        }
    }
}
