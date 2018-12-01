package com.tongdou.designpattern.factorypattern;

/**
 * Created by shenyuzhu on 2018/12/1.
 */
public class StorageFactory {

    public Storage getStorage(StorageType storageType) {
        if (StorageType.FILE_STORAGE == storageType) {
            return new FileStorage();
        } else if (StorageType.FTP_STORAGE == storageType) {
            return new FTPStorage();
        } else if (StorageType.DATABASE_STORAGE == storageType) {
            return new DatabaseStorage();
        } else if (StorageType.CLOUD_STORAGE == storageType) {
            return new CloudStorage();
        } else {
            throw new IllegalArgumentException("参数错误");
        }

    }

    public static void main(String[] args) {
        // 创建工厂
        StorageFactory storageFactory = new StorageFactory();

        storageFactory.getStorage(StorageType.FILE_STORAGE).save(null);
        storageFactory.getStorage(StorageType.FTP_STORAGE).save(null);
        storageFactory.getStorage(StorageType.DATABASE_STORAGE).save(null);
        storageFactory.getStorage(StorageType.CLOUD_STORAGE).save(null);


    }

}
