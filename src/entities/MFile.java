package entities;

import enums.MFileAnnotationType;

public class MFile {
    private String nameFile;
    private String path;
    private String content;
    private MFileAnnotationType type;

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MFileAnnotationType getType() {
        return type;
    }

    public void setType(MFileAnnotationType type) {
        this.type = type;
    }
}

