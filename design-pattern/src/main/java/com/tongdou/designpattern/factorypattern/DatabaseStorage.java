package com.tongdou.designpattern.factorypattern;

import java.io.File;

/**
 * Created by shenyuzhu on 2018/12/1.
 */
public class DatabaseStorage implements Storage {
    @Override
    public String save(File file) {
        // 保存到数据库，返回对应的数据可ID
        System.out.println("DatabaseStorage 存储");
        return null;
    }
}
