package com.tongdou.tools.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 作者：yu_kang
 * 来源：CSDN
 * 原文：https://blog.csdn.net/yu_kang/article/details/78317889
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class Image2Pdf {
    /**
     * @param imageFolderPath 图片文件夹地址
     * @param pdfPath         PDF文件保存地址
     */
    public static void toPdf(String imageFolderPath, String pdfPath) {
        try {

            // 图片地址
            String imagePath = null;
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfPath);
            // 创建文档
            Document doc = new Document(null, 0, 0, 0, 0);
            //doc.open();
            // 写入PDF文档
            PdfWriter.getInstance(doc, fos);
            // 读取图片流
            BufferedImage img = null;
            // 实例化图片
            Image image = null;
            // 获取图片文件夹对象
            File folder = new File(imageFolderPath);
            File[] files = folder.listFiles();
            File imageFile = null;
            // 循环获取图片文件夹内的图片
            for (int page = 0; page < files.length; page++) {
                imageFile = files[page];
                if (imageFile.getName().endsWith(".png")
                        || imageFile.getName().endsWith(".jpg")
                        || imageFile.getName().endsWith(".gif")
                        || imageFile.getName().endsWith(".jpeg")
                        || imageFile.getName().endsWith(".tif")) {
                    // 读取图片流
                    img = ImageIO.read(imageFile);
                    doc.newPage();
                    // 根据图片大小设置文档大小
                    doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
                    // 实例化图片
                    image = Image.getInstance(imageFile.getPath());
                    // 添加图片到文档
                    doc.open();
                    doc.add(image);
                }
            }
            // 关闭文档
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String classPath = Thread.class.getResource("/").getPath();
        String imageFolder = classPath + File.separatorChar + "static/pdf/src/";  // 文件生成路径
        String outputFile = classPath + File.separatorChar + "static/pdf/target.pdf";  // 水印pdf文件生成路径

        toPdf(imageFolder, outputFile);

    }

}
