package com.hxzy.service.impl;

import com.hxzy.common.service.impl.BaseServiceImpl;
import com.hxzy.entity.Members;
import com.hxzy.mapper.MembersMapper;
import com.hxzy.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lz
 * @data 2019-11-20
 * @comment 描述
 */
@Service
public class MembersServiceImpl extends BaseServiceImpl<Members,Integer> implements MembersService {

    private MembersMapper membersMapper;

    @Autowired
    public void setMembersMapper(MembersMapper membersMapper) {
        this.membersMapper = membersMapper;
        super.setBaseMapper(membersMapper);
    }
}
