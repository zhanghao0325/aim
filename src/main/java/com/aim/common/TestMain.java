package com.aim.common;


import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain {

    private static final int MIN_THRESHOLD = 50;
    private static final int MAX_THRESHOLD = 80;
    private static List<String> list = new ArrayList<String>();
    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = null;
        try {

            InputStreamReader isr = new InputStreamReader(new FileInputStream(new File("D:\\workspace\\aimjava\\target\\AimProject\\upload\\产品列表.pdf.txt")), "GBK");
            BufferedReader br = new BufferedReader(isr);

            StringBuffer sb = new StringBuffer();

            while ((str=br.readLine())!=null) {
                //String [] substrs = str.split("。|\\？|\\！|\\?|\\.|!");
                /*正则表达式：句子结束符*/
                sb.append(str.trim().replaceAll("\\s*", ""));
                String regEx="[。？！?.!]";
                Pattern p =Pattern.compile(regEx);
                Matcher m = p.matcher(sb);

                /*按照句子结束符分割句子*/
                String[] substrs = p.split(sb);

                /*将句子结束符连接到相应的句子后*/
                if(substrs.length > 0)
                {
                    int count = 0;
                    while(count < substrs.length)
                    {
                        if(m.find())
                        {
                            substrs[count] += m.group();
                        }
                        count++;
                    }
                }
//	              //String [] substrs = str.split("[。？！?.!]");
                for (int i=0;i<substrs.length;i++) {

                    //if (substrs[i].length()<MIN_THRESHOLD) {	//语句小于要求的分割粒度
                        //sb.append(substrs[i]);
                        //sb.append("||");
//                        if (sb.length()>MIN_THRESHOLD) {
                            //System.out.println("A New TU: " + sb.toString());
                            //list.add(sb.toString());
                            //sb.delete(0, sb.length());
//                        }
                    //}
                    //else {	//语句满足要求的分割粒度
//                        if(sb.length()!=0)	//此时如果缓存有内容则应该先将缓存存入再存substrs[i]的内容  以保证原文顺序
//                        {
//                            list.add(sb.toString());
//                            //System.out.println("A New Tu:"+sb.toString());
//                            sb.delete(0, sb.length());
//                        }
                        list.add(substrs[i]);
                        //System.out.println("A New Tu:"+substrs[i]);
                    //}
                }
            }
            br.close();
            isr.close();

            //将分割好的tu放入List中以便传入数据库
            for (Iterator i = list.iterator(); i.hasNext();){
                System.out.print("start");
                System.out.println(i.next());
                System.out.println("end");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}