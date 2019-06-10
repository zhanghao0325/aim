package com.aim.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitKeyWords {
    public static String split(String txtFile) {
        String replace1 = txtFile.replaceAll("\\s*", "");
        String replace = replace1.replaceAll("．", ".");
//        int i = replace.indexOf("1总则");
//
//        int i1 = replace.indexOf("总则1");
//        int i2 = replace.indexOf("1范围");
//        int i3 = replace.indexOf("三、条文、条文说明及解释");
        String s = SplitKeyWords1.split1(replace, "1总则");
        if (!replace.equals(s)){
            return s;
        }
        String s1 = SplitKeyWords1.split1(replace, "总则1");
        if (!replace.equals(s1)){
            return s1;
        }
        String s2 = SplitKeyWords1.split1(replace, "1范围");
        if (!replace.equals(s2)){
            return s2;
        }
        String s3 = SplitKeyWords1.split1(replace, "三、条文、条文说明及解释");
        if (!replace.equals(s3)){
            return s3;
        }
//        if (-1 != i && !"".equals(i)) {
//            String substring = replace.substring(i);
//            //System.out.println(substring);
//            return substring;
//        }else if (-1!=i1&&!"".equals(i1)){
//            String substring = replace.substring(i1);
//            //System.out.println(substring);
//            return substring;
//        }else if (-1!=i2&&!"".equals(i2)){
//            String substring = replace.substring(i2);
//            //System.out.println(substring);
//            return substring;
//        }else if (-1!=i3&&!"".equals(i3)){
//            String substring = replace.substring(i2);
//            //System.out.println(substring);
//            return substring;
//        }
        Matcher matcher = Pattern.compile("范围").matcher(replace);
        if (matcher.find()){
            int start = matcher.start();
            String substring = replace.substring(start);
            return substring;
        }
        return replace;
    }
}
