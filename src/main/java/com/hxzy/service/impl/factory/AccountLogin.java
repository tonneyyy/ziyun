package com.hxzy.service.impl.factory;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.util.MD5Util;
import com.hxzy.common.util.RedisUtil;
import com.hxzy.entity.Members;
import com.hxzy.mapper.MembersMapper;
import com.hxzy.service.MembersService;
import io.netty.handler.codec.json.JsonObjectDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Log4j2
@Transactional
@Component
public class AccountLogin implements MemberLogin {

    //redis的key
    @Value("${member.loginkey}")
    private String memberLoginKey;

    //过期时间30分钟
    @Value("${member.expire}")
    private Long memberExpire=1800L;

    @Autowired
    private MembersMapper membersMapper;

    @Autowired
    private RedisUtil redisUtil;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseMessage login(Members members) {
        try{
        Members dbMember=this.membersMapper.findByLoginName(members.getLoginName());
        if(dbMember==null){
            return new ResponseMessage(ResponseCodeEnum.USERNAME_PASSWORD_ERROR);
        }
        //验证密码
        String md5Pwd= MD5Util.MD5Encode(members.getLoginPassword(),  dbMember.getSalt());
        if(!md5Pwd.equals(dbMember.getLoginPassword())){
            return new ResponseMessage(ResponseCodeEnum.USERNAME_PASSWORD_ERROR);
        }

        //账户状态
        if(dbMember.getState()==0){
            return new ResponseMessage(ResponseCodeEnum.USERNAME_Locked);
        }

        //登录成功，存session吗?  redis

         String json=JSONObject.toJSONString(dbMember);
         String uuid=UUID.randomUUID().toString();
         boolean result=this.redisUtil.set(this.memberLoginKey+":"+uuid,json,this.memberExpire);
         //redis没有问题
         if(result){
             //更新最后一次登录时间
             Members  updateMember=new Members();
             updateMember.setId(dbMember.getId());
             updateMember.setLastLoginDate(new Date());
             this.membersMapper.updateSelective(updateMember);

             //把值发给前端
             Map<String,String> map=new HashMap<>();
             map.put("token", uuid);
             return new ResponseMessage(ResponseCodeEnum.SUCCESS,map);
         }else{
             //redis写入值失败的
             log.error("登录成功后-redis写入值失败");
             return new ResponseMessage(ResponseCodeEnum.LoginFailed);
         }

        }catch(Exception e){
            log.error(e.getMessage());
            return new ResponseMessage(ResponseCodeEnum.LoginFailed);
        }
    }
}
