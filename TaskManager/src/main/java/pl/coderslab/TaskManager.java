package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Phaser;

public class TaskManager {
    static int rows = 0;
    static String[][] tasks = null;
    static final String fileName = "tasks.csv";
    static File file = new File(fileName);
    static final String[] options = new String[]{"add", "remove", "list", "exit"};

    public static void main(String[] args) {
        tasks = downloadData(fileName);
        printOptions(options);
    }

    public static void printOptions(String[] tab) {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        for (String option : tab) {
            System.out.println(ConsoleColors.RESET + option);
        }
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String chosenOption = scan.nextLine();
            switch (chosenOption.toLowerCase()) {
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask();
                    break;
                case "list":
                    listTask();
                    break;
                case "exit":
                    System.out.println(ConsoleColors.RED + "Bye, Bye.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Chosen option do not exist, please type options again: ");
            }
            printOptions(options);
        }

    }

    //------------funkcja pobiera dane z pliku tasks.csv i zapisuje je w tablicy tasks------------
    public static String[][] downloadData(String fileName) {
        Path dir = Paths.get(fileName);
        if (!Files.exists(dir)) {
            System.err.println("File " + fileName + " not exist.");
            System.exit(0);
        }
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                rows++;
                scan.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not exist");
        }
        try (Scanner scan2 = new Scanner(file)) {
            tasks = new String[rows][3];
            for (int i = 0; i < rows; i++) {
                String[] rowNumber = scan2.nextLine().split(",");
                for (int k = 0; k < 3; k++) {
                    tasks[i][k] = rowNumber[k];
                }
            }
        } catch (IOException e) {
            System.err.println("File not exist");
        }
        return tasks;
    }

    //--------------metoda do dodawania zadań-----------------------
    public static void addTask() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please add task description: ");
        String description =scan.nextLine();
        System.out.print("Please add task due date: ");
        String dueDate = scan.nextLine();
        System.out.print("Is your task is important: true/false: ");
        String isImportant = scan.nextLine();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = description;
        tasks[tasks.length - 1][1] = dueDate;
        tasks[tasks.length - 1][2] = isImportant;
        System.out.println(Arrays.deepToString(tasks));

    }

    //-------------metda do wypisania zadan -----------------------
    public static void listTask() {
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + ": ");
            for (int k = 0; k < 3; k++) {
                System.out.print(tasks[i][k] + " ");
            }
            System.out.println();
        }
    }

    //-----------metoda do usówania zadań---------------------
    public static void removeTask() throws ArrayIndexOutOfBoundsException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please select number to remove: ");
        String rowNumber = scan.next();
        int rowNumberNumeric = checkNumeric(rowNumber);

        while (rowNumberNumeric < 0) {
            System.err.print("Incorrect argument passed. Please give number greater or equal 0: ");
            rowNumberNumeric = checkNumeric(scan.next());
        }
        while (rowNumberNumeric >= tasks.length) {
            System.err.println("Element is out of bounds. Please type correct value.");
            rowNumberNumeric = checkNumeric(scan.next());
        }
        tasks = ArrayUtils.remove(tasks, rowNumberNumeric);
        System.out.println("Value was successfully deleted.");
    }

    // ------ metoda sprawdza czy wprowadzana wartosć to cyjra------------------
    public static int checkNumeric(String value) {
        while (!StringUtils.isNumeric(value)) {
            System.err.println("Wprowadzona wartość nie jest cyfra wprowadź poprawną wartość: ");
            Scanner scan = new Scanner(System.in);
            value = scan.next();
        }
        int numericValue = Integer.parseInt(value);
        return numericValue;
    }


}

