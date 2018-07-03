package org.ko.prototype.core.bean;

public class FileInfo {

    private String name;

    private String path;

    public FileInfo() {
    }

    public FileInfo(String path) {
        this.path = path;
    }

    public FileInfo(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
