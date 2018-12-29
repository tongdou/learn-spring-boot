package com.tongdou.tools.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;

/**
 * PDF生成
 */
public class PdfWatermark {
    /**
     * 创建PDF文件
     *
     * @return 成功/失败
     */
    public static boolean createPDFFile(String filePath) {
        Rectangle rect = new Rectangle(PageSize.A4);
        Document doc = new Document(rect, 50.0F, 50.0F, 50.0F, 50.0F);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(new File(filePath)));
            doc.open();

            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 使用系统字体

            Font font = new Font(bf);
            font.setStyle(Font.BOLD); // 设置样式

            Paragraph p = new Paragraph("付 款 通 知 书1\r\n", font);
            p.setAlignment(1);
            doc.add(p);
            doc.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void waterMark(String inputFile, String imageFile, String outputFile, String waterMarkName, int permission) {
        try {
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 使用系统字体

            int total = reader.getNumberOfPages() + 1;

            // 图片
            Image image = Image.getInstance(imageFile);
            image.setAbsolutePosition(400, 480);

            PdfContentByte under;
            int j = waterMarkName.length();
            char c = 0;
            int rise = 0;
            for (int i = 1; i < total; i++) {
                rise = 400;
                under = stamper.getUnderContent(i);
                under.beginText();
                under.setFontAndSize(base, 30);

                if (j >= 15) {
                    under.setTextMatrix(200, 120);
                    for (int k = 0; k < j; k++) {
                        under.setTextRise(rise);
                        c = waterMarkName.charAt(k);
                        under.showText(c + "");
                    }
                } else {
                    under.setTextMatrix(240, 100);
                    for (int k = 0; k < j; k++) {
                        under.setTextRise(rise);
                        c = waterMarkName.charAt(k);
                        under.showText(c + "");
                        rise -= 18;

                    }
                }

                // 添加水印文字
                under.endText();

                // 添加水印图片
                under.addImage(image);

                // 画个圈
                under.ellipse(250, 450, 350, 550);
                under.setLineWidth(1f);
                under.stroke();
            }
            stamper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String classPath = Thread.class.getResource("/").getPath();
        String inputFile = classPath + File.separatorChar + "static/pdf/src.pdf";  // 文件生成路径
        String outputFile = classPath + File.separatorChar + "static/pdf/target.pdf";  // 水印pdf文件生成路径
        String imageFilePath = classPath + "static/pdf/logo_2017.png"; // 水印图片路径

        // 生成PDF
        if (createPDFFile(inputFile)) {
            waterMark(inputFile, imageFilePath, outputFile, "正版授权", 16); // 添加水印
        }
    }
}
