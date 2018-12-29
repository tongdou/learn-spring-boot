package com.tongdou.tools.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfWatermark2 {

    public static void main(String[] args) throws Exception {
        String classPath = Thread.class.getResource("/").getPath();
        String inputFile = classPath + File.separatorChar + "static/pdf/src.pdf";  // 文件生成路径
        String outputFile = classPath + File.separatorChar + "static/pdf/target.pdf";  // 水印pdf文件生成路径

        PdfReader pdfReader = new PdfReader(inputFile);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(outputFile));
        addWatermark(pdfStamper, "www.360sdn.com", 4, 1);
        pdfStamper.close();
    }

    private static void addWatermark(PdfStamper pdfStamper, String waterMarkName, int totalRow, int totalcol) {
        PdfContentByte content = null;
        BaseFont base = null;
        Rectangle pageRect = null;

        try {
            // 设置字体
            base = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            if (base == null) {
                return;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            PdfGState gs = new PdfGState();
            // 设置透明度为0.4
            gs.setFillOpacity(0.4f);
            gs.setStrokeOpacity(0.4f);
            int toPage = pdfStamper.getReader().getNumberOfPages();
            for (int i = 1; i <= toPage; i++) {
                pageRect = pdfStamper.getReader().getPageSizeWithRotation(i);
                //获得PDF最顶层
                content = pdfStamper.getOverContent(i);
                content.saveState();
                // set Transparency
                content.setGState(gs);
                content.beginText();
                content.setColorFill(BaseColor.GRAY);
                content.setFontAndSize(base, 20);

                // 计算水印X,Y坐标
                float pageWidth = pageRect.getWidth();
                float pageHeight = pageRect.getHeight();

                float wordWidth = pageWidth / totalcol;
                float wordHeigth = pageHeight / totalRow;
                float x = 0, y = 0;


                for (int row = 0; row < totalRow; row++) {
                    for (int col = 0; col < totalcol; col++) {
                        x = wordWidth * col + wordWidth * 1 / 6;
                        y = wordHeigth * row + wordHeigth / 2;

                        // 水印文字成45度角倾斜
                        content.showTextAligned(Element.ALIGN_CENTER, "suny07", x, y, 30);
                        content.showTextAligned(Element.ALIGN_CENTER, "供应商名称", x + wordWidth * 1 / 3, y, 30);
                        content.showTextAligned(Element.ALIGN_CENTER, "供应商登录名", x + wordWidth * 2 / 3, y, 30);
                    }
                }

                content.endText();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            content = null;
            base = null;
            pageRect = null;
        }
    }
}