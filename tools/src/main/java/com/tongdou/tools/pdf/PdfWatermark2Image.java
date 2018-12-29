package com.tongdou.tools.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * pdf--->加水印--->转图片---->base64码
 */
public class PdfWatermark2Image {

    public static void main(String[] args) throws Exception {
        String classPath = Thread.class.getResource("/").getPath();
        String inputFile = classPath + File.separatorChar + "static/pdf/src.pdf";  // 文件生成路径
        String outDir = classPath + File.separatorChar + "static/pdf/src";  // 文件生成路径
        File dir = new File(outDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 添加水印
        byte[] pdfByte = addWatermark(inputFile, new String[]{"供应商名称", "sdfs", "sdf"}, 4, 3);

        pdf2Image(pdfByte, outDir, 100);

    }

    /***
     * PDF文件转PNG图片，全部页数
     *
     dpi越大转换后越清晰，相对转换速度越慢
     * @return
     */
    public static void pdf2Image(byte[] pdfByte, String dstImgFolder, int dpi) throws IOException {
        PDDocument pdDocument = null;
        PdfReader reader = null;
        try {
            pdDocument = PDDocument.load(pdfByte);
            PDFRenderer renderer = new PDFRenderer(pdDocument);

            reader = new PdfReader(pdfByte);
            int pages = reader.getNumberOfPages();
            for (int i = 0; i < pages; i++) {
                File dstFile = new File(dstImgFolder + File.separatorChar + String.format("%04d", i + 1) + ".png");
                BufferedImage image = renderer.renderImageWithDPI(i, dpi);
                ImageIO.write(image, "png", dstFile);
            }
            System.out.println("PDF文档转PNG图片成功！");
        } finally {
            if (pdDocument != null) {
                pdDocument.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }


    private static byte[] addWatermark(String inputFile, String[] waterMarkTexts, int totalRow, int totalcol) throws Exception {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

        PdfReader pdfReader = null;
        PdfStamper pdfStamper = null;
        try {
            pdfReader = new PdfReader(inputFile);
            pdfStamper = new PdfStamper(pdfReader, byteOut);
            // 设置字体
            BaseFont base = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            PdfGState gs = new PdfGState();
            // 设置透明度为0.4
            gs.setFillOpacity(0.4f);
            gs.setStrokeOpacity(0.4f);
            int toPage = pdfStamper.getReader().getNumberOfPages();
            for (int i = 1; i <= toPage; i++) {
                Rectangle pageRect = pdfStamper.getReader().getPageSizeWithRotation(i);
                //获得PDF最顶层
                PdfContentByte content = pdfStamper.getOverContent(i);
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
                        x = wordWidth * col + wordWidth / 2 / waterMarkTexts.length;
                        y = wordHeigth * row + wordHeigth / 2;

                        // 水印文字成45度角倾斜
                        for (int len = 0; len < waterMarkTexts.length; len++) {
                            content.showTextAligned(Element.ALIGN_CENTER, waterMarkTexts[len], x + wordWidth * len / waterMarkTexts.length, y, 30);
                        }
//                        content.showTextAligned(Element.ALIGN_CENTER, "suny07", x, y, 30);
//                        content.showTextAligned(Element.ALIGN_CENTER, "供应商名称", x + wordWidth * 1 / 3, y, 30);
//                        content.showTextAligned(Element.ALIGN_CENTER, "供应商登录名", x + wordWidth * 2 / 3, y, 30);
                    }
                }
                content.endText();
            }
            pdfStamper.flush();
        } finally {
            if (pdfStamper != null) {
                pdfStamper.close();
            }
            if (pdfReader != null) {
                pdfReader.close();
            }
        }

        return byteOut.toByteArray();
    }
}