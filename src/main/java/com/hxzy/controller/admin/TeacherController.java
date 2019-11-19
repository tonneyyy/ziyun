package com.hxzy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.search.PageSearch;
import com.hxzy.common.validator.ValidatorImpl;
import com.hxzy.common.validator.ValidatorResult;
import com.hxzy.entity.Teacher;
import com.hxzy.service.TeacherService;
import com.hxzy.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * describe:
 *
 * @author xxx
 * @date 2019/11/16
 */
@Controller
@RequestMapping(value = "/admin")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ValidatorImpl validator;

    /**
     * 显示用户查询页面
     * @return
     */
    @GetMapping(value = "teacher/search")
    public String search(){

        return "admin/teacher/list";
    }

    /**
     * ajax分页查询请求
     * @param pageSearch
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/teacher/data")
    public ResponseMessage ajaxData(PageSearch pageSearch){
        //分页用的数据
        ResultData rs= this.teacherService.searchPage(pageSearch);
        //再次组装，添加自定义返回码
        ResponseMessage responseMessage=new ResponseMessage(ResponseCodeEnum.SUCCESS,rs);

        return  responseMessage;
    }

    /**
     * 判断名称是否存在
     * @param teacher
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/teacher/exist/name")
    public JSONObject ajaxExistName(Teacher teacher){
        return this.teacherService.existName(teacher);
    }

    /**
     * 新增或修改操作
     * @param teacher
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/teacher/save")
    public ResponseMessage save(Teacher teacher,int[] knowledge){
        //判断值是否有问题
        ValidatorResult validatorResult=validator.validate(teacher);
        //有错
        if (validatorResult.isHasErrors()){
            return  new ResponseMessage(ResponseCodeEnum.ERROR,validatorResult.getErroMsgMap());
        }


        StringBuffer str=new StringBuffer();
        for(int i=0;i<knowledge.length;i++){
            str.append(knowledge[i]);
            if(i!=knowledge.length-1){
                str.append(",");
            }
        }

        teacher.setTeachKnowledge(str.toString());
        boolean result=false;
        //新增
        if (teacher.getId()==null||teacher.getId()==0){
            result=this.teacherService.insert(teacher);
        }else {
            //修该先查询用户
            result=this.teacherService.updateSelective(teacher);
        }
        ResponseMessage responseMessage=null;
        if (result){
            responseMessage=new ResponseMessage(ResponseCodeEnum.SUCCESS);
        }else {
            responseMessage=new ResponseMessage(ResponseCodeEnum.ERROR);
        }
        return responseMessage;
    }

}
