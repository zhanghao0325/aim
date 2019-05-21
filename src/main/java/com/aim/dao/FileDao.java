package com.aim.dao;

import com.aim.entity.File;

public interface FileDao {
    int insertSelective(File file);
}
