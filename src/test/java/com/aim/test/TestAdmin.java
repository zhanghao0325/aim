package com.aim.test;

import com.aim.dao.AdminDao;
import com.aim.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring.xml")
public class TestAdmin {
    @Autowired
    private AdminDao adminDao;

    @Test
    public void test1() {
        List<Admin> all = adminDao.selectAll();
        System.out.println(all);
    }
}

