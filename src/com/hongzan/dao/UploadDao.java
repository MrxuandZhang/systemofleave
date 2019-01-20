package com.hongzan.dao;

import com.hongzan.base.BaseDao;
import com.hongzan.entity.Upload;

import java.util.List;

public interface UploadDao extends BaseDao<Upload> {
    List<Upload> query(String leaNo);
}
