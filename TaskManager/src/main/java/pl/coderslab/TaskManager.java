package pl.coderslab;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    static String[][] tasks;

    static final String fileName = "tasks.csv";
    static File file = new File(fileName);
    static final String[] options = new String[]{"add", "remove", "list", "exit"};

    public static void main(String[] args) {
        downloadData(fileName);
        printOptions(options);
    }

    public static void printOptions(String[] tab) {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        for (String option : tab) {
            System.out.println(ConsoleColors.RESET + option);
        }

    }

    public static String[][] downloadData(String fileName) {
        Path dir = Paths.get(fileName);
        if (!Files.exists(dir)) {
            System.err.println("File " + fileName + " not exist.");
            System.exit(0);
        }
        int rows = 0;
        int columns = 0;
        String [][] tab = null;
        String [] wiersze = null;
        StringBuilder sb = new StringBuilder();
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                    sb.append(scan.nextLine()).append(";;");
                    rows = rows + 1;
            }
            String test = String.valueOf(Files.readAllLines(dir));
            System.out.println(test + " test");
            System.out.println(rows);
            wiersze = sb.toString().split(";;");
            System.out.println(Arrays.toString(wiersze));
        } catch (IOException e) {
            System.err.println("błąd");
        }
        return tab;
    }


}

