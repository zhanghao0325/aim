package com.aim.common;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GetParagraph {
    public static void main(String[] args) throws IOException {
        ArrayList<String> res = new ArrayList<String>();// 段落切分结果
        StringBuilder sb = new StringBuilder();// 拼接读取的内容
        String temp = null;// 临时变量，存储sb去除空格的内容
        InputStreamReader isr =
                new InputStreamReader(new FileInputStream
                        (new File("D:\\workspace\\aimjava\\target\\AimProject\\upload\\产品列表.pdf.txt")), "GBK");
        BufferedReader reader = new BufferedReader(isr);
        int ch = 0;
        while ((ch = reader.read()) != -1) {
            temp = sb.toString().trim().replaceAll("\\s*", "");// 取出前后空格，之后去除中间空格
            if ((char) ch == '\r') {
                // 判断是否是空行
                if (!"".equals(temp)) {
                    // 说明到了段落结尾，将其加入集合，并清空sb
                    res.add(temp);
                }
                sb.delete(0, sb.length());
            } else {
                // 说明没到段落结尾，将结果暂存
                sb.append((char) ch);
            }
        }
        if (reader.read() == -1) {
            System.out.println("哈哈，你读到了末尾嘞！");
        }
        // 最后一段如果非空， 将最后一段加入，否则不处理
        if (!"".equals(temp)) {
            res.add(temp);
        }

        Iterator<String> iterator = res.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println("段落开始：");
            System.out.println(next);
        }
        System.out.println("段落的个数是：" + res.size());
    }
}
