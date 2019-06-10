package com.aim.common;

import jdk.nashorn.internal.runtime.Source;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.apache.zookeeper.data.Id;
import org.springframework.stereotype.Component;

import javax.xml.ws.soap.Addressing;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SplitParagraph1 {

    /**
     * <p>
     * Title: getParagraph
     * </p>
     * <p>
     * Description: 根据给出的文本路径，读取文本内容，并按照段落切分，以段落为单位，存储在List集合中
     * </p>
     *
     * @return List<String> 段落集合
     * @throws IOException
     */

    public static List<String> getParagraph(String sf) throws IOException {

        ArrayList<String> list = new ArrayList<>();
        // 取出前后空格，之后去除中间空格
//        String replace = sf.replace("\\s*", "");
        Matcher matcher2 = Pattern.compile("(\\d+\\d+\\.\\d+\\.\\d+\\.\\d+)").matcher(sf);
        if (matcher2.find()) {
            int start3 = matcher2.start();
            String substring = sf.substring(0, start3);
            ArrayList<String> strings = meticulousSplit(substring);//没有xx.x.x.x
            list.addAll(strings);
            String substring1 = sf.substring(start3);
            ArrayList<String> strings1 = meticulousSplit1(substring1);//有xx.x.x.x
            list.addAll(strings1);
        } else {
            ArrayList<String> strings = meticulousSplit(sf);//没有xx.x.x.x
            list.addAll(strings);
        }
        return list;
    }

    public static ArrayList<String> meticulousSplit(String substring) {
        ArrayList<String> list = new ArrayList<>();
        Matcher matcher2 = Pattern.compile("(\\d+\\d+\\.\\d+\\.\\d+)").matcher(substring);
        if (matcher2.find()) {
            int start = matcher2.start();
            String substring1 = substring.substring(0, start);
            ArrayList<String> strings = meticulousSplitxxx(substring1);//no xx.x.x 没有xx.x.x.x
            list.addAll(strings);
            String substring2 = substring.substring(start);
            ArrayList<String> strings1 = meticulousSplitxxx1(substring2);//have xx.x.x 没有xx.x.x.x
            list.addAll(strings1);
        } else {
            ArrayList<String> strings = meticulousSplitxxx(substring);//no xx.x.x
            list.addAll(strings);
        }

        return list;
    }

    public static ArrayList<String> meticulousSplit1(String substring) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        Matcher matcher2 = Pattern.compile("(\\d+\\d+\\.\\d+\\.\\d+)").matcher(substring);
        while (matcher2.find()) {
            integers.add(matcher2.start());
        }
        if (integers.size() > 1) {
            String substring1 = substring.substring(0, integers.get(1));//have xx.x.x.x  no xx.x.x
            ArrayList<String> strings = meticulousSplitxxxx(substring1);
            list.addAll(strings);
            String substring2 = substring.substring(integers.get(1));//have xx.x.x.x and xx.x.x
            ArrayList<String> strings1 = meticulousSplitxxxx1(substring2);
            list.addAll(strings1);
        } else {

            ArrayList<String> strings = meticulousSplitxxxx(substring);
            list.addAll(strings);
        }
        return list;
    }

    public static ArrayList<String> meticulousSplitxxxx(String substring) {//have xx.x.x.x  no xx.x.x
        ArrayList<String> list = new ArrayList<>();
        String[] p = substring.split("(?=(\\d+\\d+\\.\\d+\\.\\d+\\.\\d+))");
        //String[] p = sf.split("(?=( [1-9]\\d*\\.\\d*\\.\\d*))");
        if (p.length > 0) {
            for (String s : p) {
                Matcher matcher = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)").matcher(s);
                if (matcher.find()) {
                    ArrayList<Integer> integers = new ArrayList<>();
                    while (matcher.find()) {
                        integers.add(matcher.start());
                    }
                    if (integers.size() > 0) {
                        String substring1 = s.substring(0, integers.get(0));
                        list.add(substring1);
                        System.out.println(substring1);
                        String substring2 = s.substring(integers.get(0));
                        String[] split = substring2.split("(?=(\\d+\\.\\d+\\.\\d+\\.\\d+))");
                        for (String s1 : split) {
                            Matcher matcher1 = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(s1);
                            if (matcher1.find()) {
                                ArrayList<Integer> integers1 = new ArrayList<>();
                                while (matcher1.find()) {
                                    integers1.add(matcher1.start());
                                }
                                if (integers1.size() > 0) {
                                    String substring3 = s1.substring(0, integers1.get(0));
                                    list.add(substring3);
                                    System.out.println(substring3);
                                    String substring4 = s1.substring(integers1.get(0));
                                    String[] split1 = substring4.split("(?=(\\d+\\.\\d+\\.\\d+))");
                                    for (String s2 : split1) {
                                        if (s2.length() > 3) {
                                            list.add(s2);
                                            System.out.println(s2);
                                        }
                                    }
                                } else {
                                    list.add(s1);
                                    System.out.println(s1);
                                }
                            }

                        }
                    } else {
                        ArrayList<Integer> integers1 = new ArrayList<>();
                        Matcher matcher1 = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(s);
                        while (matcher1.find()) {
                            integers1.add(matcher1.start());
                        }
                        if (integers1.size() > 1) {
                            String substring1 = s.substring(0, integers1.get(1));
                            list.add(substring1);
                            System.out.println(substring1);
                            String substring2 = s.substring(integers1.get(1));
                            String[] split = substring2.split("(?=(\\d+\\.\\d+\\.\\d+))");
                            for (String s1 : split) {
                                list.add(s1);
                                System.out.println(s1);
                            }
                        } else {
                            list.add(s);
                            System.out.println(s);
                        }
                    }
                }
            }
        }
        return list;
    }

    public static ArrayList<String> meticulousSplitxxxx1(String substring) {//have xx.x.x.x and xx.x.x
        ArrayList<String> list = new ArrayList<>();
        String[] p = substring.split("(?=(\\d+\\d+\\.\\d+\\.\\d+\\.\\d+))");
        //String[] p = sf.split("(?=( [1-9]\\d*\\.\\d*\\.\\d*))");
        if (p.length > 0) {
            for (String s : p) {
                Matcher matcher = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)").matcher(s);
                if (matcher.find()) {
                    ArrayList<Integer> integers = new ArrayList<>();
                    while (matcher.find()) {
                        integers.add(matcher.start());
                    }
                    if (integers.size() > 0) {
                        ArrayList<Integer> integers3 = new ArrayList<>();
                        String substring1 = s.substring(0, integers.get(0));
                        Matcher matcher3 = Pattern.compile("(\\d+\\d+\\.\\d+\\.\\d+)").matcher(substring1);
                        while (matcher3.find()){
                            integers3.add(matcher3.start());
                        }
                        if (integers3.size()>0){
                            String substring2 = substring1.substring(0, integers3.get(0));
                            list.add(substring2);
                            System.out.println(substring2);
                            String substring3 = substring1.substring(integers3.get(0));
                            String[] split4 = substring3.split("(?=(\\d+\\d+\\.\\d+\\.\\d+))");
                            for (String s5 : split4) {
                                ArrayList<Integer> integers2 = new ArrayList<>();
                                Matcher matcher2 = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(s5);
                                while (matcher2.find()){
                                    integers2.add(matcher2.start());
                                }
                                if (integers2.size()>0){
                                    String substring4 = s5.substring(0, integers2.get(0));
                                    list.add(substring4);
                                    System.out.println(substring4);
                                    String substring5 = s5.substring(integers2.get(0));
                                    String[] split3= substring5.split("(?=(\\d+\\.\\d+\\.\\d+))");
                                    for (String s1 : split3) {
                                        list.add(s1);
                                        System.out.println(s1);
                                    }
                                }else {
                                    list.add(s5);
                                    System.out.println(s5);
                                }
                            }
                        }else {
                            ArrayList<Integer> integers2 = new ArrayList<>();
                            Matcher matcher2 = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(substring1);
                            while (matcher2.find()) {
                                integers2.add(matcher2.start());
                            }
                            if (integers2.size() > 0) {
                                String s1 = s.substring(0, integers2.get(0));
                                list.add(s1);
                                System.out.println(s1);
                                String substring2 = s.substring(integers2.get(0));
                                String[] split = substring2.split("(?=(\\d+\\.\\d+\\.\\d+))");
                                for (String s2 : split) {
                                    list.add(s2);
                                    System.out.println(s2);
                                }
                            } else {
                                list.add(substring1);
                                System.out.println(substring1);
                            }
                        }

                        String substring2 = s.substring(integers.get(0));
                        String[] split = substring2.split("(?=(\\d+\\.\\d+\\.\\d+\\.\\d+))");
                        for (String s1 : split) {

                            String[] split2 = s1.split("(?=(\\d+\\d+\\.\\d+\\.\\d+))");
                            for (String s2 : split2) {
                                Matcher matcher1 = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(s2);
                                if (matcher1.find()) {
                                    ArrayList<Integer> integers1 = new ArrayList<>();
                                    while (matcher1.find()) {
                                        integers1.add(matcher1.start());
                                    }
                                    if (integers1.size() > 0) {
                                        String substring3 = s2.substring(0, integers1.get(0));
                                        list.add(substring3);
                                        System.out.println(substring3);
                                        String substring4 = s2.substring(integers1.get(0));
                                        String[] split1 = substring4.split("(?=(\\d+\\.\\d+\\.\\d+))");
                                        for (String s3 : split1) {
                                            if (s3.length() > 3) {
                                                list.add(s3);
                                                System.out.println(s3);
                                            }
                                        }
                                    } else {
                                        list.add(s2);
                                        System.out.println(s2);
                                    }
                                } else {
                                    list.add(s2);
                                    System.out.println(s2);
                                }
                            }
                        }
                    } else {
                        ArrayList<Integer> integers1 = new ArrayList<>();
                        Matcher matcher1 = Pattern.compile("(\\d+\\d+\\.\\d+\\.\\d+)").matcher(s);
                        while (matcher1.find()) {
                            integers1.add(matcher1.start());
                        }
                        if (integers1.size() > 1) {
                            String substring1 = s.substring(0, integers1.get(1));
                            list.add(substring1);
                            System.out.println(substring1);
                            String substring2 = s.substring(integers1.get(1));
                            String[] split = substring2.split("(?=(\\d+\\d+\\.\\d+\\.\\d+))");
                            for (String s1 : split) {
                                //list.add(s1);
                                //System.out.println(s1);
                                ArrayList<Integer> integers2 = new ArrayList<>();
                                Matcher matcher2 = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(s1);
                                while (matcher2.find()) {
                                    integers2.add(matcher2.start());
                                }
                                if (integers2.size() > 1) {
                                    String substring3 = s1.substring(0, integers2.get(1));
                                    list.add(substring3);
                                    System.out.println(substring3);
                                    String substring4 = s1.substring(integers2.get(1));
                                    String[] split1 = substring4.split("(?=(\\d+\\.\\d+\\.\\d+))");
                                    for (String s2 : split1) {
                                        list.add(s2);
                                        System.out.println(s2);
                                    }
                                } else {
                                    list.add(s1);
                                    System.out.println(s1);
                                }
                            }
                        } else {
                            ArrayList<Integer> integers2 = new ArrayList<>();
                            Matcher matcher2 = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(s);
                            while (matcher2.find()) {
                                integers2.add(matcher2.start());
                            }
                            if (integers2.size() > 1) {
                                String substring1 = s.substring(0, integers2.get(1));
                                list.add(substring1);
                                System.out.println(substring1);
                                String substring2 = s.substring(integers2.get(1));
                                String[] split = substring2.split("(?=(\\d+\\.\\d+\\.\\d+))");
                                for (String s1 : split) {
                                    list.add(s1);
                                    System.out.println(s1);
                                }
                            } else {
                                list.add(s);
                                System.out.println(s);
                            }
                        }
                    }
                } else {
                    ArrayList<Integer> integers1 = new ArrayList<>();
                    Matcher matcher1 = Pattern.compile("(\\d+\\d+\\.\\d+\\.\\d+)").matcher(s);
                    while (matcher1.find()) {
                        integers1.add(matcher1.start());
                    }
                    if (integers1.size() > 1) {
                        String substring1 = s.substring(0, integers1.get(1));
                        list.add(substring1);
                        System.out.println(substring1);
                        String substring2 = s.substring(integers1.get(1));
                        String[] split = substring2.split("(?=(\\d+\\d+\\.\\d+\\.\\d+))");
                        for (String s1 : split) {
                            //list.add(s1);
                            //System.out.println(s1);
                            ArrayList<Integer> integers2 = new ArrayList<>();
                            Matcher matcher2 = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(s1);
                            while (matcher2.find()) {
                                integers2.add(matcher2.start());
                            }
                            if (integers2.size() > 1) {
                                String substring3 = s1.substring(0, integers2.get(1));
                                list.add(substring3);
                                System.out.println(substring3);
                                String substring4 = s1.substring(integers2.get(1));
                                String[] split1 = substring4.split("(?=(\\d+\\.\\d+\\.\\d+))");
                                for (String s2 : split1) {
                                    list.add(s2);
                                    System.out.println(s2);
                                }
                            } else {
                                list.add(s1);
                                System.out.println(s1);
                            }
                        }
                    } else {
                        ArrayList<Integer> integers2 = new ArrayList<>();
                        Matcher matcher2 = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(s);
                        while (matcher2.find()) {
                            integers2.add(matcher2.start());
                        }
                        if (integers2.size() > 1) {
                            String substring1 = s.substring(0, integers2.get(1));
                            list.add(substring1);
                            System.out.println(substring1);
                            String substring2 = s.substring(integers2.get(1));
                            String[] split = substring2.split("(?=(\\d+\\.\\d+\\.\\d+))");
                            for (String s1 : split) {
                                list.add(s1);
                                System.out.println(s1);
                            }
                        } else {
                            list.add(s);
                            System.out.println(s);
                        }
                    }
                }
            }
        }
        return list;
    }


    public static ArrayList<String> meticulousSplitxxx(String substring) {//no xx.x.x 没有xx.x.x.x
        ArrayList<String> list = new ArrayList<>();
        String[] p = substring.split("(?=(\\d+\\.\\d+\\.\\d+\\.\\d+))");
        //String[] p = sf.split("(?=( [1-9]\\d*\\.\\d*\\.\\d*))");
        if (p.length > 0) {
            for (String s : p) {
                Matcher matcher = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(s);
                if (matcher.find()) {
                    int start = matcher.start();
                    if (0 == start) {
                        Matcher matcher1 = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)").matcher(s);
                        if (matcher1.find()) {
                            int start1 = matcher1.start();
                            if (0 == start1) {
                                ArrayList<Integer> arrayList = new ArrayList<>();
                                while (matcher.find()) {
                                    int start2 = matcher.start();
                                    arrayList.add(start2);
                                }
                                if (arrayList.size() > 1) {
                                    String substringq = s.substring(0, arrayList.get(0));
                                    list.add(substringq);
                                    System.out.println(substringq);
                                    String substring1 = s.substring(arrayList.get(0));
                                    String[] split = substring1.split("(?=(\\d+\\.\\d+\\.\\d+))");
                                    if (split.length > 0) {
                                        for (String s1 : split) {
                                            if (s1.length() > 3) {
                                                list.add(s1);
                                                System.out.println(s1);
                                            }
                                        }
                                    }
                                } else {
                                    list.add(s);
                                    System.out.println(s);
                                }
                            }
                        } else {
                            String[] split = s.split("(?=(\\d+\\.\\d+\\.\\d+))");
                            if (split.length > 0) {
                                for (String s1 : split) {
                                    if (s1.length() > 3) {
                                        list.add(s1);
                                        System.out.println(s1);
                                    }
                                }
                            }
                        }
                    } else {
                        String substringq = s.substring(0, start);
                        list.add(substringq);
                        System.out.println(substringq);
                        String substring1 = s.substring(start);
                        String[] split1 = substring1.split("(?=(\\d+\\.\\d+\\.\\d+))");
                        for (String s1 : split1) {
                            if (s1.length() > 3) {
                                list.add(s1);
                                System.out.println(s1);
                            }
                        }
                    }
                } else {
                    list.add(s);
                    System.out.println(s);
                }
            }
        }
        return list;
    }

    public static ArrayList<String> meticulousSplitxxx1(String substring) {//have xx.x.x 没有xx.x.x.x
        ArrayList<String> list = new ArrayList<>();
        String[] p = substring.split("(?=(\\d+\\.\\d+\\.\\d+\\.\\d+))");
        //String[] p = sf.split("(?=( [1-9]\\d*\\.\\d*\\.\\d*))");
        if (p.length > 0) {
            for (String s : p) {
                String[] split2 = s.split("(?=(\\d+\\d+\\.\\d+\\.\\d+))");
                for (String s1 : split2) {
                    Matcher matcher = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(s1);
                    if (matcher.find()) {
                        ArrayList<Integer> integers = new ArrayList<>();
                        while (matcher.find()) {
                            integers.add(matcher.start());
                        }
                        if (integers.size() > 0) {
                            String substring1 = s1.substring(0, integers.get(0));
                            list.add(substring1);
                            System.out.println(substring1);
                            String substring2 = s1.substring(integers.get(0));
                            String[] split = substring2.split("(?=(\\d+\\.\\d+\\.\\d+))");
                            for (String s2 : split) {
                                if (s2.length() > 3) {
                                    list.add(s2);
                                    System.out.println(s2);
                                }
                            }
                        } else {
                            list.add(s1);
                            System.out.println(s1);
                        }
                    }

                }
//                Matcher matcher = Pattern.compile("(\\d+\\.\\d+\\.\\d+)").matcher(s);
//                if (matcher.find()) {
//                    int start = matcher.start();
//                    if (0 == start) {
//                        Matcher matcher1 = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)").matcher(s);
//                        if (matcher1.find()) {
//                            int start1 = matcher1.start();
//                            if (0 == start1) {
//                                ArrayList<Integer> arrayList = new ArrayList<>();
//                                while (matcher.find()) {
//                                    int start2 = matcher.start();
//                                    arrayList.add(start2);
//                                }
//                                if (arrayList.size() > 1) {
//                                    String substringq = s.substring(0, arrayList.get(0));
//                                    list.add(substringq);
//                                    System.out.println(substringq);
//                                    String substring1 = s.substring(arrayList.get(0));
//                                    String[] split = substring1.split("(?=(\\d+\\.\\d+\\.\\d+))");
//                                    if (split.length > 0) {
//                                        for (String s1 : split) {
//                                            if (s1.length() > 3) {
//                                                list.add(s1);
//                                                System.out.println(s1);
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    list.add(s);
//                                    System.out.println(s);
//                                }
//                            }
//                        } else {
//                            String[] split = s.split("(?=(\\d+\\.\\d+\\.\\d+))");
//                            if (split.length > 0) {
//                                for (String s1 : split) {
//                                    if (s1.length() > 3) {
//                                        list.add(s1);
//                                        System.out.println(s1);
//                                    }
//                                }
//                            }
//                        }
//                    } else {
//                        String substringq = s.substring(0, start);
//                        list.add(substringq);
//                        System.out.println(substringq);
//                        String substring1 = s.substring(start);
//                        String[] split1 = substring1.split("(?=(\\d+\\.\\d+\\.\\d+))");
//                        for (String s1 : split1) {
//                            if (s1.length() > 3) {
//                                list.add(s1);
//                                System.out.println(s1);
//                            }
//                        }
//                    }
//                } else {
//                    list.add(s);
//                    System.out.println(s);
//                }
//            }
//        }
            }
        }
        return list;
    }
}