package com.hxzy.service;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.BaseService;
import com.hxzy.entity.Role;
import com.hxzy.vo.RoleSearch;

import java.util.List;

public interface RoleService  extends BaseService<Role,Integer> {

    /**
     * 查询角色名称是否存在
     * @param role
     * @return 0不存在，1代表存在
     */
    JSONObject existRoleName(Role role);

    /**
     * 查询权限标识是否存在
     * @param role
     * @return 0不存在，1代表存在
     */
    JSONObject existAuthority(Role role);


    /**
     * 根据某个角色拥有的菜单
     * @param roleId
     * @return
     */
    ResponseMessage searchRoleOwnMenu(int roleId);

    /**
     * 为角色保存权限
     * @param roleId
     * @param perms
     * @return
     */
    ResponseMessage savePermission(int roleId, String perms);

    /**
     * 根据角色id查询它的用户有哪些
     * @param roleSearch
     * @return
     */
    ResponseMessage ownRoleUser(RoleSearch roleSearch);

    /**
     * 根据角色id 查询没有角色id的用户有哪些
     * @param roleSearch
     * @return
     */
    ResponseMessage noOwnRoleUser(RoleSearch roleSearch);

    /**
     * 为角色添加用户
     * @param roleId
     * @param permList
     * @return
     */
    ResponseMessage addUserPermission(int roleId, List<Integer> permList);

    /**
     * 为角色移出用户
     * @param roleId
     * @param permList
     * @return
     */
    ResponseMessage removeUserPermission(int roleId, List<Integer> permList);
}
