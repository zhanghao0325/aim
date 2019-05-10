package com.aim.controller;

import com.aim.entity.File;
import com.aim.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/findAll")
    public List<File> findAll() {
        List<File> all = adminService.findAll();
        return all;
    }
}
