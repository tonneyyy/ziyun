package com.hxzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.impl.BaseServiceImpl;
import com.hxzy.entity.Menu;
import com.hxzy.entity.Role;
import com.hxzy.mapper.MenuMapper;
import com.hxzy.mapper.RoleMapper;
import com.hxzy.service.RoleService;
import com.hxzy.vo.ResultData;
import com.hxzy.vo.RoleSearch;
import com.hxzy.vo.ZTreeVO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Integer> implements RoleService {


    private RoleMapper  roleMapper;

    @Autowired
    private MenuMapper menuMapper;


    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
        super.setBaseMapper(roleMapper);
    }

    @Override
    public JSONObject existRoleName(Role role) {
        boolean result=false;  //不存在
        //判断新增还是修改
        if(role.getId()==null || role.getId()==0){
             int count=this.roleMapper.existRoleName(role.getRolename());
             result= (count==0);

        }else{
           //修改
            Role oldValue=this.roleMapper.findOne(role.getId());
            //没有改过
            if(oldValue.getRolename().equals(role.getRolename())){
                result =true;
            }else{
                int count=this.roleMapper.existRoleName(role.getRolename());
                result= (count==0);
            }
        }

        //返回
        JSONObject mp=new JSONObject();
        mp.put("valid",result);
        return mp;
    }

    @Override
    public JSONObject existAuthority(Role role) {
        boolean result=false;  //不存在
        //判断新增还是修改
        if(role.getId()==null || role.getId()==0){
            int count=this.roleMapper.existAuthority(role.getAuthority());
            result= (count==0);

        }else{
            //修改
            Role oldValue=this.roleMapper.findOne(role.getId());
            //没有改过
            if(oldValue.getAuthority().equals(role.getAuthority())){
                result =true;
            }else{
                int count=this.roleMapper.existAuthority(role.getAuthority());
                result= (count==0);
            }
        }

        //返回
        JSONObject mp=new JSONObject();
        mp.put("valid",result);
        return mp;
    }

    //根据角色ID读取它拥有的权限
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage searchRoleOwnMenu(int roleId) {
        List<ZTreeVO>  ztreeList=new ArrayList<>();

        //读取所有菜单
        List<Menu> allMenuList=this.menuMapper.findAll();

        //读取该角色拥有的菜单
        List<Integer> roleOwnMenu=this.roleMapper.roleOwnMenuId(roleId);

       //遍历  lambda
        allMenuList.stream().forEach(m->{

            int menuId=m.getId();

            ZTreeVO vo=new ZTreeVO();
            vo.setId(m.getId());
            vo.setPId(m.getParentId());
            vo.setName(m.getMenuName());

            //有权限的
            if(roleOwnMenu.contains(menuId)){
                vo.setChecked(true);
            }
            //要不要展开
            vo.setOpen(true);
            ztreeList.add(vo);

        });

        return new ResponseMessage(ResponseCodeEnum.SUCCESS,ztreeList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseMessage savePermission(int roleId, String perms) {
        ResponseMessage responseMessage=null;

        try{
            //1、先删除原有的权限
            this.roleMapper.deleteRoleMenu(roleId);


            if(StringUtils.isNotBlank(perms)){
                //2、再批量新增权限
                List<Integer>  permsList=new ArrayList<>();

                String[] arr=perms.split("-");

                for(String s : arr){
                    permsList.add(Integer.parseInt(s));
                }

               this.roleMapper.savePermission(roleId, permsList);
            }

            //返回成功
            responseMessage=new ResponseMessage(ResponseCodeEnum.SUCCESS);

        }catch (Exception ex){
            responseMessage=new ResponseMessage(ResponseCodeEnum.ERROR,ex.getMessage());
            log.error(ex.getMessage());
        }

        return responseMessage;
    }

    @Override
    public ResponseMessage ownRoleUser(RoleSearch roleSearch) {
        return null;
    }

    @Override
    public ResponseMessage noOwnRoleUser(RoleSearch roleSearch) {
        return null;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseMessage addUserPermission(int roleId, List<Integer> permList) {
        int count= this.roleMapper.addUserPermission(roleId,permList);
        if (count > 0) {
            return new ResponseMessage(ResponseCodeEnum.SUCCESS);
        }else{
            return new ResponseMessage(ResponseCodeEnum.ERROR);
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseMessage removeUserPermission(int roleId, List<Integer> permList) {
        int count= this.roleMapper.removeUserPermission(roleId,permList);
        if (count > 0) {
            return new ResponseMessage(ResponseCodeEnum.SUCCESS);
        }else{
            return new ResponseMessage(ResponseCodeEnum.ERROR);
        }
    }
}
