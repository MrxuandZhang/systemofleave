package com.hongzan.service;

import com.hongzan.base.BaseService;
import com.hongzan.entity.Upload;

import java.util.List;

public interface UploadService extends BaseService<Upload> {
    List<Upload> query(String leaNo);
}
