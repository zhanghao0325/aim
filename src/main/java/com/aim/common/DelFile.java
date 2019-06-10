package com.aim.common;

import java.io.File;

public class DelFile {
    public static void del(String path){
        File file = new File(path);
        if (file.isFile()&&file.exists()){
            file.delete();
        }
    }
}
