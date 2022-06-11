package pl.coderslab;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    static String[][] tasks = null;
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
        String[][] tab2 = null;
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                rows++;
                System.out.println(rows + " " + scan.nextLine());
            }

        } catch (IOException e) {
            System.err.println("błąd");
        }
        try (Scanner scan2 = new Scanner(file)) {
            for (int i = 0; i < rows; i++) {
                String [] rowNumber = scan2.nextLine().split(",");
                System.out.println(rowNumber[0]);
                for (int k = 0; k < rowNumber.length; k++) {
                    System.out.println("dupa");
                    System.out.println(i);
                    System.out.println(k);
                    tasks[i][k] = rowNumber[k];
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd");
        }
        System.out.println(Arrays.deepToString(tasks));
        return tab2;
    }


}

