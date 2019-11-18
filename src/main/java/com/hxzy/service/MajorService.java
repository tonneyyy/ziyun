package com.hxzy.service;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.BaseService;
import com.hxzy.entity.Major;

/**
 * @author
 * @date 20191115下午 4:59
 * @comment 请详细描述类的作用
 */

public interface MajorService extends BaseService<Major,Integer>{

    ResponseMessage findAll();

    JSONObject existMajorName(Major major);
}
