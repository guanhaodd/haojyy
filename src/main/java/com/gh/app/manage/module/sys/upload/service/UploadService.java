package com.gh.app.manage.module.sys.upload.service;

import java.io.IOException;
import java.util.List;

import com.gh.app.manage.module.sys.upload.bean.TbUploadFile;
import com.gh.app.util.dao.BaseDao;

public interface UploadService extends BaseDao<TbUploadFile>{

	List<TbUploadFile> getFileListbyCatalogId(String catalogId);

	void cleanCache() throws IOException;

}
