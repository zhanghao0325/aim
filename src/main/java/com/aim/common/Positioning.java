package com.aim.common;


import com.google.common.collect.Lists;
import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

public class Positioning {

    private static int i = 0;
    private static Rectangle2D.Float boundingRectange = null;

    private static StringBuilder content;

    private static List<Object[]> arrays = Lists.newArrayList();

    private static List<Object[]> getKeyWords(String filePath, final String keyWord) {

        try {
            PdfReader pdfReader = new PdfReader(filePath);
            int pageNum = pdfReader.getNumberOfPages();
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);

            for (i = 1; i < pageNum; i++) {
                content = new StringBuilder();
                boundingRectange = new Rectangle2D.Float();
                pdfReaderContentParser.processContent(i, new RenderListener() {
                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                        String text = textRenderInfo.getText(); // 整页内容
                        content.append(text);

                        boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
                                                /*if (null != text && StringUtils.contains(content, keyWord)) {
                            float[] resu = new float[3];
                            resu[0] = boundingRectange.x;
                            resu[1] = boundingRectange.y;
                            resu[2] = i;
                            arrays.add(resu);
                        }*/
                    }

                    @Override
                    public void renderImage(ImageRenderInfo arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void endTextBlock() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void beginTextBlock() {
                        // TODO Auto-generated method stub

                    }
                });

                if (null != content && StringUtils.contains(content, keyWord)) {
                    Object[] resu = new Object[4];
                    resu[0] = content;
                    resu[1] = boundingRectange.x;
                    resu[2] = boundingRectange.y;
                    resu[3] = i;
                    arrays.add(resu);
                }
//    System.out.println("第"+i+"页，内容："+content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrays;
    }
}
