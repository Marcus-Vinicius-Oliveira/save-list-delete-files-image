package entities;


import enums.MFileAnnotationType;
import interfaces.FolderManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderOrchestrator implements FolderManagement {
    List<String> mListPaths = new ArrayList<>();

    public void createFolders(List<String> mListPaths) {
        mListPaths.forEach(path -> {
            File dirDefaut = new File(path);
            dirDefaut.mkdir();
        });
    }

    public void removeFolders(List<String> mListPaths) {
        int index = mListPaths.get(0).lastIndexOf('\\');
        File folder = new File(mListPaths.get(0).substring(0, index));
        mListPaths.forEach(path -> {
            removeAFolder(path);
            File deleteFolder = new File(path);
            deleteFolder.delete();
        });
        folder.delete();
    }

    //metodos do implements
    @Override
    public void createAFolder(String path) {
        try {
            File directory = new File(path);
            boolean status = directory.exists();
            if (!status) {
                directory.mkdir();
                mListPaths.add(path);
                System.out.println("diretório criado com sucesso");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAFolder(String path) {
        File directory = new File(path);
        boolean status = directory.isDirectory();
        if (status) {
            File[] file = directory.listFiles();
            for (int i = 0; i < file.length; i++) {
                removeAFolder(file[i].getPath());
            }
        } else {
            directory.delete();
        }
    }

    @Override
    public void listAllFoldersCreated(String path) {
        File allFolders = new File(path);
        if (allFolders.isDirectory()) {
            File[] files = allFolders.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(file.getName());
                }
            }
        }
    }
}


