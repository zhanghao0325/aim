package com.aim.controller;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitKeyWords1 {
    public static String split1(String txtFile,String i){

        Matcher matcher = Pattern.compile(i).matcher(txtFile);
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (matcher.find()){
            int start = matcher.start();
            arrayList.add(start);
        }
        if (arrayList.size()>0){

            String substring = txtFile.substring(arrayList.get(arrayList.size()-1));
            return substring;
        }
            return txtFile;
    }
}
