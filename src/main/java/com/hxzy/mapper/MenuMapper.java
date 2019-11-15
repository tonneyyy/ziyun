package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu,Integer> {


    /**
     * 全查询
     * @return
     */
    List<Menu> findAll();

    /**
     * 加载菜单
     * @param userId
     * @return
     */
    List<Menu> loadUserMenu(int userId);
}