package src.main.regex.commons;

import java.io.*;
import java.util.ArrayList;

public class FileUtils {
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

    public static void deleteFile(String filePath) {
        File f = new File(filePath);
        if (f.delete()) {
            System.out.println(f.getName() + " deleted successfully");
        } else {
            System.out.println(f.getName() + " deleted failed");
        }
    }

    public static ArrayList<String> findAllFilesInFolder(String folderPath) {
        ArrayList<String> listOfFiles = new ArrayList<>();

        File folder = new File(folderPath);

        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                listOfFiles.add(file.getName());
            } else {
                throw new IllegalArgumentException("Not a folder");
            }
        }
        return listOfFiles;
    }

    public static void writeFile(String filePath, String data) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
