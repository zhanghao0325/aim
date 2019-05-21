package com.aim.service;

import com.aim.entity.UplodFile;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface FindService {

    List<UplodFile> find(String desc);
    PageInfo<UplodFile> find1(Integer pageSize, Integer pageNum,String desc);

    Map<String, Object> search(Map searchMap);
}
