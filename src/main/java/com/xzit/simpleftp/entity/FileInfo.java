package com.xzit.simpleftp.entity;

import java.util.Date;



public class FileInfo {

    private  String id;
    private  long size;
    private  String type;
    private  String originalfilename;
    private  String  createtime;
    private  String filepath;


    public FileInfo() {
    }

    public FileInfo(String id, long size, String type, String originalfilename, String createtime, String filepath) {
        this.id = id;
        this.size = size;
        this.type = type;
        this.originalfilename = originalfilename;
        this.createtime = createtime;
        this.filepath = filepath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginalfilename() {
        return originalfilename;
    }

    public void setOriginalfilename(String originalfilename) {
        this.originalfilename = originalfilename;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }



    @Override
    public String toString() {
        return "FileInfo{" +
                "id='" + id + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", originalfilename='" + originalfilename + '\'' +
                ", createtime=" + createtime +
                ", filepath='" + filepath + '\'' +
                '}';
    }
}
