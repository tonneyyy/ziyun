package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role,Integer> {

    /**
     * 查询角色名称是否存在
     * @param value
     * @return 0不存在，1代表存在
     */
    int  existRoleName(String value);

    /**
     * 查询权限标识是否存在
     * @param value
     * @return 0不存在，1代表存在
     */
    int  existAuthority(String value);

    /**
     * 根据角色ID查询它所拥有菜单  sys_role_menu表
     * @param roleId
     * @return
     */
    List<Integer> roleOwnMenuId(int roleId);

    /**
     * 删除权限sys_role_menu
     * @param roleId
     * @return
     */
    int deleteRoleMenu(int roleId);

    /**
     * 保存权限 sys_role_menu
     * @param roleId
     * @param permsList
     * @return
     */
    int savePermission(@Param("roleId") int roleId, @Param("list") List<Integer> permsList);




    /**
     * 为角色添加用户
     * @param roleId
     * @param permList
     * @return
     */
    int addUserPermission(@Param("roleId") int roleId, @Param("list") List<Integer> permList);

    /**
     * 为角色移出用户
     * @param roleId
     * @param permList
     * @return
     */
    int removeUserPermission(@Param("roleId") int roleId, @Param("list") List<Integer> permList);
}