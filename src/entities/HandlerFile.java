package entities;


import enums.MFileAnnotationType;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HandlerFile extends FileOrchestrator {
    private String dirRoot;

    public void setDirRoot(String dirRoot) {
        this.dirRoot = dirRoot;
    }

    public String getDirRoot() {
        return dirRoot;
    }

    public void saveListImg(List<MFile> mImageFileList) {
        saveAllListOfImageFiles(mImageFileList);
    }

    public void saveListFiles(List<MFile> mFileList) {
        mFileList.forEach(mFile -> {
            mFile = knowTypePath(mFile);
            createSpecificFolder(mFile);
        });

        saveAllListOfFiles(mFileList);
    }


    // metodos para arquivos(geral)
    public void saveFileByDirectory(MFile mFile) {
        mFile = knowTypePath(mFile);
        createSpecificFolder(mFile);
        saveFile(mFile.getPath(), mFile.getContent(), mFile.getType(), mFile.getNameFile());
    }

    public MFile knowTypePath(MFile mFile) {
        switch (mFile.getType()) {
            case IMPORTANT -> mFile.setPath(dirRoot.concat("important\\"));
            case REMINDER -> mFile.setPath(dirRoot + "reminder\\");
            default -> mFile.setPath(dirRoot + "");
        }
        return mFile;
    }

    public boolean removeFileByDirectory(MFile mFile) {
        mFile = knowTypePath(mFile);
        if (removeFile(mFile.getPath(), mFile.getNameFile(), mFile.getType())) {
            return true;
        }
        return false;
    }

    public boolean searchFile(MFile mFile) {
        File dirFile = new File(dirRoot);
        File[] files = dirFile.listFiles();
        if (recoveryFileByName(files, mFile)) {
            return true;
        }
        return false;
    }

    private boolean recoveryFileByName(File[] files, MFile mFile) {
        for (File file : files) {
            boolean isDir = file.isDirectory();
            if (isDir) {
                recoveryFileByName(file.listFiles(), mFile);
            }
            String fileName = file.getName();
            String mFileName = mFile.getNameFile();

            if (fileName.equalsIgnoreCase(mFileName)) {
                int index = file.getPath().lastIndexOf('\\');
                mFile.setPath(file.toString().substring(0, index).concat("\\"));
                recoveryFile(mFile.getPath(), mFile.getNameFile());
                return true;
            }
        }
        return false;
    }


    //metodos para imagem
    public void saveImgWithDirectory(MFile mFile) {
        if (mFile.getType() == MFileAnnotationType.IMAGE) {
            createAFolder(dirRoot + "\\images");
        }
        saveImageFile(mFile.getPath(), mFile.getContent(), mFile.getNameFile());
    }

    public boolean removeImg(MFile mFile) {
        if (removeImageFile(mFile.getPath(), mFile.getNameFile())) {
            return true;
        }
        return false;
    }

    private void createSpecificFolder(MFile mFile) {
        createAFolder(mFile.getPath());
    }

    //metodos para folder
    public void deleteFolderWithFiles(String path) {
        Scanner sc = new Scanner(System.in);
        File dir = new File(dirRoot + path);
        List<String> StringList = new ArrayList<>();

        if (dir.exists()) {
            File[] files = dir.listFiles();
            List<File> mFilesList = new ArrayList<>(Arrays.stream(files).toList());
            mFilesList.forEach(file -> {
                StringList.add(file.getPath());
                System.out.println(file.getName());
            });

            System.out.println("Voce está tendando excluir uma Pasta com arquivos," + " Continuar?" + "[S/N]");
            String keep = sc.nextLine();
            if (keep.equalsIgnoreCase("S")) {
                removeFolders(StringList);
            }
        }
    }


    //o programa funciona sem esse métodos, pois as pasta tbm são criadar individualmente
    public void createDefautFolder() {
        String img = "images\\";
        String fileImp = "important\\";
        String fileRem = "reminder\\";
        List<String> defautFolder = new ArrayList<>() {{
            add(dirRoot + img);
            add(dirRoot + fileImp);
            add(dirRoot + fileRem);
        }};
        createFolders(defautFolder);
    }
}

