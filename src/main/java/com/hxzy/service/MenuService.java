package com.hxzy.service;

import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.BaseService;
import com.hxzy.entity.Menu;
import com.hxzy.vo.MenuVO;

import java.util.List;

public interface MenuService  extends BaseService<Menu,Integer> {
    /**
     * 全查询
     * @return
     */
   ResponseMessage findAll();

    /**
     * 加载菜单
     * @param teacherId
     * @return
     */
    List<MenuVO> findTeacherMenu(int teacherId);

    List<Menu> findByParentId(Integer parentId);

    ResponseMessage saveMenu(Menu menu);
}
