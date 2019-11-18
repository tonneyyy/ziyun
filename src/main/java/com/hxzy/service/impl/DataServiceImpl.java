package com.hxzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.impl.BaseServiceImpl;
import com.hxzy.entity.Data;
import com.hxzy.entity.Major;
import com.hxzy.mapper.DataMapper;
import com.hxzy.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @date 20191117下午 3:08
 * @comment 请详细描述类的作用
 */
@Service
public class DataServiceImpl extends BaseServiceImpl<Data,Integer> implements DataService {

    private DataMapper dataMapper;

    @Autowired
    public void setDataMapper(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
        super.setBaseMapper(dataMapper);
    }

    @Override
    public ResponseMessage findAll() {
        List<Data> list = this.dataMapper.findAll();
        return new ResponseMessage(ResponseCodeEnum.SUCCESS,list);
    }

    @Override
    public JSONObject existTypesName(Data data) {
        boolean result=false;
        if(data.getId()==null || data.getId()==0){
            int count=this.dataMapper.existTypesName(data.getName());
            result= (count==0);

        }else{
            //修改
            Data oldValue=this.dataMapper.findOne(data.getId());
            //没有改过
            if(oldValue.getName().equals(data.getName())){
                result =true;
            }else{
                int count=this.dataMapper.existTypesName(data.getName());
                result= (count==0);
            }
        }

        //返回
        JSONObject mp=new JSONObject();
        mp.put("valid",result);
        return mp;
    }
}
