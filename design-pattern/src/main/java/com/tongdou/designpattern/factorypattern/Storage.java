package com.tongdou.designpattern.factorypattern;

import java.io.File;

/**
 * 存储
 * Created by shenyuzhu on 2018/12/1.
 */
public interface Storage {
    /**
     * 保存成功返回标识，失败返回null
     *
     * @param file
     * @return
     */
    String save(File file);

}
