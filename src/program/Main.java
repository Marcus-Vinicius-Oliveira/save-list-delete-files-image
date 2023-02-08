package program;


import entities.HandlerFile;
import utilities.Menu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Boolean option = Boolean.TRUE;
        HandlerFile handlerFile = new HandlerFile();
        System.out.println("ENTER THE DIRECTORY ROOT:");
        String dirRoot = sc.nextLine() + "\\";
        handlerFile.setDirRoot(dirRoot);
        handlerFile.createDefautFolder();

        do {
            System.out.println("================================");
            System.out.println("1 - IMAGE MENU");
            System.out.println("2 - FILE MENU");
            System.out.println("3 - DELETE FOLDER");
            System.out.println("4 - LIST ALL FOLDERS");
            System.out.println("0 - EXIT");
            System.out.println("----------------------------");
            System.out.print("==> ");
            Integer optionNumber = sc.nextInt();
            System.out.println("----------------------------");
            sc.skip("((?<!\\R)\\s)*");

            switch (optionNumber) {
                case 1 -> Menu.mainMenuImage(handlerFile);
                case 2 -> Menu.mainMenuFile(handlerFile);
                case 3 -> {
                    handlerFile.listAllFoldersCreated(dirRoot);
                    System.out.println("WHICH FOLDER DO YOU WANT TO DELETE?");
                    String folder = sc.nextLine();
                    handlerFile.deleteFolderWithFiles(folder);
                }
                case 4 -> handlerFile.listAllFoldersCreated(dirRoot);
                case 5 -> option = Boolean.FALSE;
                default -> System.out.println("ENTER A VALID OPTION");
            }
        } while (option);
        sc.close();
    }
}
