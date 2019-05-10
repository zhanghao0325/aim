package com.aim.dao;

import com.aim.entity.File;

import java.util.List;

public interface AdminDao {
    List<File> selectAll();
}
