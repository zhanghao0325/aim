package com.aim.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件类
 * @author       luwenbin006@163.com
 * @createTime   2014-11-22 下午04:56:54
 */
public class File  implements Serializable
{
    private static final long serialVersionUID = -110840196972249172L;

    /**主键ID*/
    private Long id;
    /**文件名*/
    private String fileName;
    /**对应SWF文件web虚拟路径*/
    private String webBasePath;
    /**文件服务器路径*/
    private String distPath;
    /**文件大小(KB)*/
    private Long fileSize;
    /**文件类型*/
    private String fileType;
    /**文件描述*/
    private String description;
    /**上传时间*/
    private Date uploadDate;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getWebBasePath()
    {
        return webBasePath;
    }

    public void setWebBasePath(String webBasePath)
    {
        this.webBasePath = webBasePath;
    }

    public String getDistPath()
    {
        return distPath;
    }

    public void setDistPath(String distPath)
    {
        this.distPath = distPath;
    }

    public Long getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(Long fileSize)
    {
        this.fileSize = fileSize;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getUploadDate()
    {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate)
    {
        this.uploadDate = uploadDate;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }


    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof File))
            return false;
        File other = (File) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }
}
