package com.aim.controller;


import com.aim.entity.UplodFile;
import com.aim.service.FindService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("search")
public class FindController {
    @Autowired
    private FindService findService;

    @RequestMapping("find")
    public ModelAndView find(String desc) {
        ModelAndView modelAndView = new ModelAndView();
        List<UplodFile> fileList = findService.find(desc);
        modelAndView.setViewName("list");
        modelAndView.addObject("fileList", fileList);
        return modelAndView;

    }

    @RequestMapping("find1")
    public ModelAndView find1(Integer pageSize, Integer pageNum, String desc) {
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<UplodFile> fileList = findService.find1(pageSize, pageNum, desc);
        modelAndView.setViewName("list1");
        modelAndView.addObject("fileList", fileList);
        return modelAndView;

    }

    @RequestMapping("search1")
    public Map<String, Object> search1(@RequestBody Map searchMap) {
        System.out.println(searchMap);
        String keywords = (String) searchMap.get("keywords");
        if (null != keywords && !"".equals(keywords)) {
            return findService.search(searchMap);
        }
        return new HashMap<>();
    }
}
