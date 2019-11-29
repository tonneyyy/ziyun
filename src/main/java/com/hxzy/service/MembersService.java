package com.hxzy.service;

import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.BaseService;
import com.hxzy.entity.Members;

import java.util.Set;

/**
 * @author lz
 * @data 2019-11-20
 * @comment 描述
 */
public interface MembersService extends BaseService<Members,Integer> {

    ResponseMessage findAll();

}
