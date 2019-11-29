package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Menu;
import com.hxzy.vo.MenuVO;

import java.util.List;

/**
 * Author: Administrator
 * Date: 2019/11/17 22:21
 * Comment: 注释
 */
public interface MenuMapper extends BaseMapper<Menu,Integer> {


    /**
     * 全查询
     * @return
     */
    List<Menu> findAll();


    /**
     * 根据父级ID查询菜单
     * @param parentId 父级菜单OD
     */
    List<Menu> findByParentId(Integer parentId);


    /**
     * 根据教师ID查询其所拥有菜单
     * @param teacherId 用户ID
     * @return 当前用户的菜单集合
     */
    List<Menu> findTeacherMenu(int teacherId);
}
