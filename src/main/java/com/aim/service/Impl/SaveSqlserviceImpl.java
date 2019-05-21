package com.aim.service.Impl;

import com.aim.dao.FileDao;
import com.aim.entity.File;
import com.aim.service.SaveSqlservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveSqlserviceImpl implements SaveSqlservice {
    @Autowired
    private FileDao fileDao;
    @Override
    public void save(File file) {
        fileDao.insertSelective(file);
    }
}
