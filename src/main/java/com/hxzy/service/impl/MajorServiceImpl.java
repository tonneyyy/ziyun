package com.hxzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.BaseService;
import com.hxzy.common.service.impl.BaseServiceImpl;
import com.hxzy.entity.Major;
import com.hxzy.mapper.MajorMapper;
import com.hxzy.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @date 20191115下午 5:00
 * @comment 请详细描述类的作用
 */
@Service
public class MajorServiceImpl extends BaseServiceImpl<Major,Integer> implements MajorService {


    private MajorMapper majorMapper;

    @Autowired
    public void setMajorMapper(MajorMapper majorMapper) {
        this.majorMapper = majorMapper;
        super.setBaseMapper(majorMapper);
    }

    @Override
    public ResponseMessage findAll() {
        List<Major> list = this.majorMapper.findAll();
        return new ResponseMessage(ResponseCodeEnum.SUCCESS,list);
    }

    @Override
    public JSONObject existMajorName(Major major) {
        boolean result=false;
        if(major.getId()==null || major.getId()==0){
            int count=this.majorMapper.existMajorName(major.getName());
            result= (count==0);

        }else{
            //修改
            Major oldValue=this.majorMapper.findOne(major.getId());
            //没有改过
            if(oldValue.getName().equals(major.getName())){
                result =true;
            }else{
                int count=this.majorMapper.existMajorName(major.getName());
                result= (count==0);
            }
        }

        //返回
        JSONObject mp=new JSONObject();
        mp.put("valid",result);
        return mp;
    }
}
