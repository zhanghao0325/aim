package com.aim.controller;

import com.aim.entity.File;
import com.aim.service.SaveSqlservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SaveSqlController {
    @Autowired
    private SaveSqlservice saveSqlservice;
    public  void save(File file){
        saveSqlservice.save(file);
    }
}
