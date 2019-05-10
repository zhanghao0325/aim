package com.aim.entity;

import java.io.Serializable;
import java.util.Objects;

public class Admin implements Serializable {
    private String id;
    private String file_name;

    public Admin(String id, String file_name) {
        this.id = id;
        this.file_name = file_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id) &&
                Objects.equals(file_name, admin.file_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, file_name);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", file_name='" + file_name + '\'' +
                '}';
    }

    public Admin() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}