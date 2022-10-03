package src.main.regex.commons;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHelper {
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

}
