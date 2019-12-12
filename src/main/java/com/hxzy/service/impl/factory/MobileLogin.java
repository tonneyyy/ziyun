package com.hxzy.service.impl.factory;

import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.entity.Members;
import com.hxzy.mapper.MembersMapper;
import com.hxzy.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MobileLogin implements MemberLogin {


    @Autowired
    private MembersMapper membersMapper;


    @Override
    public ResponseMessage login(Members members) {
        return null;
    }
}
