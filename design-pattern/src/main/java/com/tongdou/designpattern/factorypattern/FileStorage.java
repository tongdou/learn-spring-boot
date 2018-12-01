package com.tongdou.designpattern.factorypattern;

import java.io.File;

/**
 * Created by shenyuzhu on 2018/12/1.
 */
public class FileStorage implements Storage {
    @Override
    public String save(File file) {
        // 存储文件，返回对应的文件路径
        System.out.println("FileStorage 存储");

        return null;
    }
}
