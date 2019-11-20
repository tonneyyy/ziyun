package com.hxzy.service;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.BaseService;
import com.hxzy.entity.Data;

import java.util.List;

/**
 * @author
 * @date 20191115下午 4:59
 * @comment 请详细描述类的作用
 */

public interface DataService extends BaseService<Data,Integer>{

    ResponseMessage findAll();

    JSONObject existTypesName(Data data);

    List<Data> findData(int id);
}
