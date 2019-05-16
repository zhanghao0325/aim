package com.aim.common;

import com.aim.common.SplitParagraph;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Start {
    @Autowired
    private SplitParagraph splitParagraph;
    public static void main(String[] args) {
        try {
            List<String> res = SplitParagraph.getParagraph("D:\\workspace\\aimjava\\target\\AimProject\\upload\\产品列表.pdf.txt", "GBK");
            for (Iterator i = res.iterator(); i.hasNext();){
                System.out.print("start");
                System.out.println(i.next());
                System.out.println("end");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
