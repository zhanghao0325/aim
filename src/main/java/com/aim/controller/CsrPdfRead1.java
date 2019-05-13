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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import javax.servlet.ServletContext;

@Component
public class CsrPdfRead1 {

    /**
     * 读取文本文件解析文本
     *
     * @param args
     * @throws InterruptedException
     * @throws SQLException
     */
    public static void main(String[] args) throws InterruptedException, SQLException {

        String path = "D:/pdf";
        String filepath = "";
        File file = new File(path);
        File[] tempList = file.listFiles();
        if (tempList.length != 0) {
            System.out.println("该目录下对象个数：" + tempList.length);
        }
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                String name = tempList[i].getName();
                filepath = path + "/" +name;
                System.out.print("1");
                System.out.println(path + "/" + filepath);
                if (!filepath.toUpperCase().endsWith(".TXT")) {
                    System.out.println("2");
                    System.out.println(getOrderText(filepath));
                }
            }
            if (tempList[i].isDirectory()) {
                System.out.println("文件夹：" + tempList[i]);
            }
        }


    }

    public static String getOrderText(String filepath) {
        String filetxt = "";
        try {

            String encoding = "GBK";
            String newfilepath = getTextFromPDF(filepath);
            if (newfilepath == null) {
                return "";
            }
            File file = new File(newfilepath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
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
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return filetxt;
    }

    public static String getTextFromPDF(String pdfFilePath) {
        String result = null;
        FileInputStream is = null;
        PDDocument document = null;
        try {
            if (pdfFilePath.toUpperCase().endsWith(".TXT")) {
                return null;
            }
            is = new FileInputStream(pdfFilePath);
            PDFParser parser = new PDFParser(new RandomAccessBuffer(is));
            parser.parse();
            document = parser.getPDDocument();
            System.out.print("3");
            System.out.println(document);
            PDFTextStripper stripper = new PDFTextStripper();
            System.out.print("4");
            System.out.println(pdfFilePath);
            result = stripper.getText(document);
            FileWriter fw = new FileWriter("D:/rs/" + ".txt", false);
            fw.write(result);
            fw.flush();
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return pdfFilePath + ".txt";
    }
}
