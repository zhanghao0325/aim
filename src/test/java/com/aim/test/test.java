package com.aim.test;

import com.aim.common.SplitParagraph1;
import com.aim.controller.ReadtTxt;
import com.aim.controller.SplitKeyWords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class test {

    public static void main(String[] args) throws IOException {
        String s = ReadtTxt.readTxt("D:\\workspace\\aimjava\\target\\AimProject\\upload\\《综合布线工程设计规范(含条文说明)》GB50311-2007.pdf.txt");
//        System.out.println(s);

        String split = SplitKeyWords.split(s);
//        System.out.println(split);
        List<String> paragraph = SplitParagraph1.getParagraph(split);
    }

}
