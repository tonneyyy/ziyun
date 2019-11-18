package com.hxzy.service;

import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.BaseService;
import com.hxzy.entity.Classes;
import com.hxzy.vo.ClassesSearch;

import java.util.List;

public interface ClassesService extends BaseService<Classes,Integer> {

    ResponseMessage findMajor();

    ResponseMessage findMarjorByid(Integer id);

    ResponseMessage ownTeacher(ClassesSearch classesSearch);

    ResponseMessage noOwnTeacher(ClassesSearch classesSearch);

    ResponseMessage saveRemove(int classesId,List<Integer> permsList);

    ResponseMessage saveAdd(int roleId,List<Integer> permsList);

}
