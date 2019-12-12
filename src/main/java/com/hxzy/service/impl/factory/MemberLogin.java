package com.hxzy.service.impl.factory;

import com.hxzy.common.bean.LoginTypeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.entity.Members;

/**
 * 会员登录接口
 */
public interface MemberLogin {
    /**
     * 登录
     * @param members
     * @return
     */
    ResponseMessage login(Members members);


}
