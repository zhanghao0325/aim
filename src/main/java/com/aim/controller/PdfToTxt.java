package com.aim.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PdfToTxt {
        public static void readPdf(String file) throws Exception {

        //判断文件是否存在
        InputStreamReader read = new InputStreamReader(
                new FileInputStream(file), "GBK");//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        int a = 0;
        int b = 0;
        String bb = "";
        while ((lineTxt = bufferedReader.readLine()) != null) {
            //System.out.println(lineTxt);
            if (lineTxt == null || lineTxt.equals("") || lineTxt.trim().length() == 0) {
                continue;
            }
            if (lineTxt.contains("Order ID")) {
                String[] oid = lineTxt.split("\\u0029\\s*P");
                if (oid.length > 0) {
                    bb += oid[0].substring(oid[0].lastIndexOf("(") + 10);
                    b++;
                }
            }

            if (lineTxt.contains("-001")) {
                if (lineTxt.lastIndexOf("-001") - 11 >= 0) {
                    bb += " " + lineTxt.substring(lineTxt.lastIndexOf("-001") - 11, lineTxt.lastIndexOf("-001") + 4);
                    b++;
                }
            }
            if (lineTxt.contains("end of line")) {
                // System.out.println(++a);
                if (b == 2) {
                    System.out.println(bb);
                    //filetxt+=++a+":"+bb+"/r/n";
                    bb = "";
                    b = 0;
                }
            }
        }
        read.close();
    }
    public  static String getTextFromPDF(String pdfFilePath) {
        String result = null;
        FileInputStream is = null;
        PDDocument document = null;
        try {
            if(pdfFilePath.toUpperCase().endsWith(".TXT")){
                return null;
            }
            is = new FileInputStream(pdfFilePath);
            PDFParser parser = new PDFParser(new RandomAccessBuffer(is));
            parser.parse();
            document = parser.getPDDocument();
            System.out.print(document);
            PDFTextStripper stripper = new PDFTextStripper();
            System.out.println(pdfFilePath);
            result = stripper.getText(document);
            FileWriter fw = new FileWriter(pdfFilePath+".txt",false);
            fw.write(result);
            fw.flush();
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pdfFilePath+".txt";
    }
}
