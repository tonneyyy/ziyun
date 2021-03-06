package com.hxzy.service.impl;

import com.hxzy.common.bean.LoginTypeEnum;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.impl.BaseServiceImpl;
import com.hxzy.entity.Members;
import com.hxzy.mapper.MembersMapper;
import com.hxzy.service.MembersService;
import com.hxzy.service.impl.factory.AccountLogin;
import com.hxzy.service.impl.factory.MemberLogin;
import com.hxzy.service.impl.factory.MobileLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author lz
 * @data 2019-11-20
 * @comment 描述
 */
@Service
public  class MembersServiceImpl extends BaseServiceImpl<Members,Integer> implements MembersService {

    private MembersMapper membersMapper;

    @Autowired
    private MobileLogin mobileLogin;

    @Autowired
    private AccountLogin accountLogin;


    @Autowired
    public void setMembersMapper(MembersMapper membersMapper) {
        this.membersMapper = membersMapper;
        super.setBaseMapper(membersMapper);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage findAll() {
//        调用mapper中的方法
        List<Members> list = this.membersMapper.findAll();

        return new ResponseMessage(ResponseCodeEnum.SUCCESS,list);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage login(Members members, LoginTypeEnum loginType) {
        MemberLogin  memberLogin=null;
        if(loginType==LoginTypeEnum.USE_MOBILE){
            memberLogin=mobileLogin;
        }else{
            memberLogin=accountLogin;
        }
        return  memberLogin.login(members);
    }



}
