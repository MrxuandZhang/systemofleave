package com.hongzan.service.impl;

import com.hongzan.base.Page;
import com.hongzan.dao.UploadDao;
import com.hongzan.entity.Upload;
import com.hongzan.service.UploadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {
    @Resource
    private UploadDao uploadDao;
    @Override
    public void add(Upload upload) {
      uploadDao.add(upload);
    }

    @Override
    public void update(Upload upload) {
      uploadDao.update(upload);
    }

    @Override
    public void del(Serializable sid) {
        uploadDao.del(sid);
    }

    @Override
    public Upload get(Serializable sid) {
        return uploadDao.get(sid);
    }

    @Override
    public Page<Upload> query(Upload upload, String currentPage, String pageNum) {
        int start=(Integer.parseInt(currentPage)-1)*Integer.parseInt(pageNum);
        return new Page<Upload>(currentPage,pageNum,uploadDao.getTotal(upload),uploadDao.queryLimit(upload,start,Integer.parseInt(pageNum)));

    }

    @Override
    public List<Upload> query(String leaNo) {
        return uploadDao.query(leaNo);
    }
}
