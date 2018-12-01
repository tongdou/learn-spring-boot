package com.tongdou.designpattern.factorypattern;

import java.io.File;

/**
 * Created by shenyuzhu on 2018/12/1.
 */
public class CloudStorage implements Storage {

    @Override
    public String save(File file) {
        // 存到云盘，返回对应的地址标志
        System.out.println("CloudStorage 存储");
        return null;
    }
}
