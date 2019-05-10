package com.aim.service;

import com.aim.dao.AdminDao;
import com.aim.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public List<File> findAll() {
        List<File> list = adminDao.selectAll();
        return list;
    }
}
