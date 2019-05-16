package com.aim.test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;


public class ChangeMethod {
    @Test
    public  void changeMethod() throws Exception {
        ClassPool.getDefault().insertClassPath(
                "E:/aspose-words-18.6-jdk16.jar");
        CtClass c2 = ClassPool.getDefault()
                .getCtClass("com.aspose.words.zzZLX");
        CtMethod[] ms = c2.getDeclaredMethods();
        for (CtMethod c : ms) {
            System.out.println(c.getName());
            CtClass[] ps = c.getParameterTypes();
            for (CtClass cx : ps) {
                System.out.println("\t" + cx.getName());
            }

            if (c.getName().equals("zzZ") && ps.length == 2
                    && ps[0].getName().equals("org.w3c.dom.Node")
                    && ps[1].getName().equals("org.w3c.dom.Node")) {
                System.out.println("find it!");
                c.insertBefore("{return;}");
            }

        }
        c2.writeFile();

    }
}
