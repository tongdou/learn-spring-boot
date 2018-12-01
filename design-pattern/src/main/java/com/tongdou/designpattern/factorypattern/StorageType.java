package com.tongdou.designpattern.factorypattern;

/**
 * Created by shenyuzhu on 2018/12/1.
 */
public enum StorageType {
    FILE_STORAGE(1, "文件存储"),
    DATABASE_STORAGE(2, "文件存储"),
    FTP_STORAGE(3, "文件存储"),
    CLOUD_STORAGE(4, "文件存储");

    private int type;
    private String desc;

    StorageType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
