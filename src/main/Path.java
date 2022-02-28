package main;

import java.io.File;

public class Path {
    private String path="C:\\";

    public String getPath() {
        return path;
    }
    public void updatePath(String str) {
        this.path = this.path + File.separator + str;
    }
    public void updateByAbsolutePath(String str) {
        this.path = str;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    public String[] fileList() {
        return (new File(getPath())).list();
    }
    public void returnBack() {
        this.path = new File(getPath()).getParent();
    }
}
