package utilities;

import entities.HandlerFile;
import entities.MFile;
import enums.MFileAnnotationType;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void mainMenuFile(HandlerFile handlerFile){

        Scanner sc = new Scanner(System.in);
        Boolean option = Boolean.TRUE;
        List<MFile> saveAllFiles;

        do {
            System.out.println("===============================");
            System.out.println("1 - CREATE FILE");
            System.out.println("2 - REMOVE FILE");
            System.out.println("3 - RECOVERY FILE");
            System.out.println("4 - LIST ALL FILE");
            System.out.println("5 - CREATE MULTIPLE FILES"); // feito chekar
            System.out.println("0 - BACK");
            System.out.println("===============================");
            System.out.print("==> ");
            Integer optionNumber = sc.nextInt();
            sc.skip("((?<!\\R)\\s)*");


            MFile mFile = null;
            String nameFile = null;
            String contentFile = null;
            MFileAnnotationType type = null;
            Integer value = null;

            switch (optionNumber) {
                case 1 -> {

                    mFile = new MFile();
                    System.out.println("ENTER THE NAME FILE");
                    nameFile = sc.nextLine().trim() + ".txt";
                    System.out.println("ENTER DE FILE CONTENT");
                    contentFile = sc.nextLine();
                    System.out.println("CHOOSE ONE OF THE OPTIONS BELOW");
                    System.out.println("1 - REMINDER" + " " + "2 - IMPORTANT" + " " + "3 - SIMPLES");
                    System.out.print("==> ");
                    value = sc.nextInt();
                    sc.skip("((?<!\\R)\\s)*");
                    type = MFileAnnotationType.values()[value];
                    if (value > 0 && value < 4) {
                        mFile.setType(type);
                        mFile.setNameFile(nameFile);
                        mFile.setContent(contentFile);
                        handlerFile.saveFileByDirectory(mFile);
                    } else {
                        throw new InvalidParameterException("ENTER A VALID OPTION");
                    }
                    System.out.println("----------------------------");
                }
                case 2 -> {

                    mFile = new MFile();
                    System.out.println("ENTER THE FILE NAME TO DELETE");
                    String fileName = sc.nextLine().trim() + ".txt";
                    System.out.println("CHOOSE A FILE TYPE BELOW: ");
                    System.out.println("1 - REMINDER" + " " + "2 - IMPORTANT" + " " + "3 - SIMPLES");
                    System.out.print("==> ");
                    value = sc.nextInt();
                    sc.skip("((?<!\\R)\\s)*");
                    type = MFileAnnotationType.values()[value];
                    if (value > 0 && value < 4) {
                        mFile.setNameFile(fileName);
                        mFile.setType(type);
                        boolean status = handlerFile.removeFileByDirectory(mFile);
                        if (status) {
                            System.out.println("FILE DELETED SUCCESSFULLY");
                        } else {
                            throw new InvalidParameterException("ERROR. FILE NOT DELETED");
                        }
                    } else {
                        throw new InvalidParameterException("ENTER A VALID OPTION");
                    }
                    System.out.println("----------------------------");
                }
                case 3 -> {

                    mFile = new MFile();
                    System.out.println("NAME OF THE FILE TO SHOW");
                    mFile.setNameFile(sc.nextLine() + ".txt");
                    handlerFile.searchFile(mFile);
                    System.out.println("----------------------------");
                }
                case 4 -> {

                    mFile = new MFile();
                    System.out.println("CHOOSE THE OPTION BELOW TO LIST\n");
                    System.out.println("1 - REMINDER" + " " + "2 - IMPORTANT" + " " + "3 - SIMPLES");
                    System.out.print("==> ");

                    value = sc.nextInt();
                    type = MFileAnnotationType.values()[value];
                    if (value > 0 && value < 4) {
                        mFile.setType(type);
                        handlerFile.knowTypePath(mFile);
                        System.out.println("LIST ALL FILES");
                        handlerFile.listAllFiles(mFile.getPath());
                        System.out.println();
                    } else {
                        throw new InvalidParameterException("ENTER A VALID OPTION");
                    }
                    System.out.println("----------------------------");
                }
                case 5 -> {
                    boolean create = true;
                    saveAllFiles = new ArrayList<>();
                    MFile mFileList;
                    do {
                        mFileList = new MFile();
                        System.out.println("ENTER THE FILE NOME");
                        nameFile = sc.nextLine().trim() + ".txt";
                        System.out.println("ENTER THE FILE CONTENT");
                        contentFile = sc.nextLine();

                        System.out.println("CHOOSE A FILE TYPE BELOW");
                        System.out.println("1 - REMINDER" + " " + "2 - IMPORTANT" + " " + "3 - SIMPLES");
                        System.out.print("==> ");
                        value = sc.nextInt();
                        sc.skip("((?<!\\R)\\s)*");
                        type = MFileAnnotationType.values()[value];
                        if (value > 0 && value < 4) {
                            mFileList.setType(type);
                            mFileList.setNameFile(nameFile);
                            mFileList.setContent(contentFile);
                        } else {
                            throw new InvalidParameterException("ENTER A VALID OPTION");
                        }
                        saveAllFiles.add(mFileList);
                        System.out.println("ADD OTHER FILE? [Y/N]");
                        String add = sc.nextLine();
                        if (!add.equalsIgnoreCase("y")) {
                            create = false;
                        }
                    } while (create);
                    handlerFile.saveListFiles(saveAllFiles);
                    System.out.println("============================");
                }
                case 0 -> option = Boolean.FALSE;
                default -> System.out.println("Invalid option");
            }
        } while (option);
    }

    public static void mainMenuImage(HandlerFile handlerFile){
        Scanner sc = new Scanner(System.in);
        Boolean option = Boolean.TRUE;
        String directory = handlerFile.getDirRoot() + "images\\";
        List<MFile> saveAllImgsList;
        do {
            System.out.println("==============================");
            System.out.println("1 - SAVE IMAGE");
            System.out.println("2 - REMOVE IMAGE");
            System.out.println("3 - LIST ALL IMAGE");
            System.out.println("4 - SAVE ALL IMAGE");
            System.out.println("0 - BACK");
            System.out.println("==============================");
            System.out.print("==> ");
            Integer optionNumber = sc.nextInt();
            sc.skip("((?<!\\R)\\s)*");
            System.out.println("==============================");
            MFile mFile = null;

            switch (optionNumber) {
                case 1 -> {
                    mFile = new MFile();
                    System.out.println("ENTER THE IMAGE URL");
                    String urlImg = sc.nextLine();
                    System.out.println("ENTER THE IMAGE NAME");
                    String nameImg = sc.nextLine().trim() + ".jpg";
                    mFile.setContent(urlImg);
                    mFile.setNameFile(nameImg);
                    mFile.setPath(directory);
                    mFile.setType(MFileAnnotationType.IMAGE);
                    handlerFile.saveImgWithDirectory(mFile);
                    System.out.println("==============================");
                }
                case 2 -> {
                    mFile = new MFile();
                    System.out.println("IMAGE NAME TO REMOVE");
                    String imgRemoveName = sc.nextLine().trim() + ".jpg";
                    mFile.setNameFile(imgRemoveName);
                    mFile.setPath(directory);
                    boolean status = handlerFile.removeImg(mFile);
                    if (status) {
                        System.out.println("REMOVED SUCCESSFULLY");
                    } else {
                        throw new InvalidParameterException("IMAGE NOT FOUND");
                    }
                    System.out.println("==============================");
                }
                case 3 -> {
                    System.out.println("==============================");
                    System.out.println("LIST ALL IMAGES");
                    handlerFile.listAllImageFiles(directory);
                    System.out.println("==============================");
                }
                case 4 -> {
                    boolean create = true;
                    saveAllImgsList = new ArrayList<>();
                    do {
                        MFile mFileList = new MFile();
                        System.out.println("ENTER THE IMAGE URL");
                        String urlImgs = sc.nextLine();
                        System.out.println("ENTER THE IMAGE NAME");
                        String name = sc.nextLine().trim() + ".jpg";
                        mFileList.setContent(urlImgs);
                        mFileList.setNameFile(name);
                        mFileList.setPath(directory);
                        mFileList.setType(MFileAnnotationType.IMAGE);
                        saveAllImgsList.add(mFileList);
                        System.out.println("ADD OTHER IMAGE? [Y/N]");
                        String add = sc.nextLine();
                        if (!add.equalsIgnoreCase("s")) {
                            create = false;
                        }
                    } while (create);
                    handlerFile.saveListImg(saveAllImgsList);
                    System.out.println("==============================");
                }
                case 0 -> option = Boolean.FALSE;
                default -> System.out.println("Invalid option");
            }
        } while (option);
    }

}



