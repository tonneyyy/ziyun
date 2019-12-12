package com.hxzy.controller.api;

import com.hxzy.common.bean.LoginTypeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.entity.Members;
import com.hxzy.service.MembersService;
import com.hxzy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 前端登录
 */
@Controller
@RequestMapping(value = "/api")
public class ApiLoginController {

    @Autowired
    private MembersService membersService;

    /**
     * 会员登录
     * @param members
     * @param loginType
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseMessage login(Members members,@RequestParam(value="loginType",defaultValue = "1") Integer loginType){
        LoginTypeEnum  loginTypeEnum=loginType==1? LoginTypeEnum.USE_PASSWORD : LoginTypeEnum.USE_MOBILE;

        return this.membersService.login(members,loginTypeEnum);
    }


}
