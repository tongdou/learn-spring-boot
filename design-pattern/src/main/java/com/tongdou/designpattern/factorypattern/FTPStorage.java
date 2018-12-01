package com.tongdou.designpattern.factorypattern;

import java.io.File;

/**
 * Created by shenyuzhu on 2018/12/1.
 */
public class FTPStorage implements Storage {
    @Override
    public String save(File file) {
        // 保存到ftp，返回对应路径
        System.out.println("FTPStorage 存储");

        return null;
    }
}
