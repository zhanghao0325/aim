package com.aim.common;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
@Component
public class SplitParagraph {

    /**
     *
     * <p>
     * Title: getParagraph
     * </p>
     * <p>
     * Description: 根据给出的文本路径，读取文本内容，并按照段落切分，以段落为单位，存储在List集合中
     * </p>
     *
     * @param filePath
     * @return List<String> 段落集合
     * @throws IOException
     *
     */

    public static List<String> getParagraph(String filePath, String charset) throws IOException {
        List<String> res = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();// 拼接读取的内容
        InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), charset);
        BufferedReader br = new BufferedReader(isr);
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp.trim() + "\n");
        }
        // \s A whitespace character: [ \t\n\x0B\f\r]
        String p[] = sb.toString().split("[。.]");
        for (String string : p) {
            res.add(string.replaceAll("\\s*", ""));
        }
        if (br != null)
            br.close();
        if (isr != null)
            isr.close();
        return res;
    }
}