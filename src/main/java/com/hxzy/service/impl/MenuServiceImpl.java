package com.hxzy.service.impl;

import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.impl.BaseServiceImpl;
import com.hxzy.entity.Menu;
import com.hxzy.mapper.MenuMapper;
import com.hxzy.service.MenuService;
import com.hxzy.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,Integer> implements MenuService {

    private MenuMapper  menuMapper;

    @Autowired
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
        super.setBaseMapper(menuMapper);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage findAll() {
        List<Menu> list= this.menuMapper.findAll();
        return new ResponseMessage(ResponseCodeEnum.SUCCESS,list);
    }

    //要把数据库值转换成二级目录
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<MenuVO> loadUserMenu(int userId) {
        //1、查询用户拥有的菜单
        List<Menu>  ownMenuList=this.menuMapper.loadUserMenu(userId);

        //2、最终要完成的就要把数据库值，变为我们想要的二级节点
        List<MenuVO>  newList=new ArrayList<>();

        //1、读取parentId=0
        /*  for(Menu  p : ownMenuList){
            if(p.getParentid()==0){

            }
        }*/
        ownMenuList.stream().filter(p->p.getParentId()==0).forEach(p->{
             //自己组装值
            MenuVO one=new MenuVO();
            one.setId(p.getId());
            one.setActionname(p.getActionName());
            one.setAuthority(p.getAuthority());
            one.setMenuname(p.getMenuName());
            //做第二级 遍历所有的数据库 .parentId=14( p.getId())，装到 one对象的 List<MenuVO> childrens
            loadRecursion(one,ownMenuList);


            newList.add(one);

        });

        return newList;
    }

    //加载第二级(递归加载)
    private void loadRecursion(MenuVO one, List<Menu> ownMenuList) {
        List<MenuVO>  childrenList=new ArrayList<>();

        ownMenuList.stream().filter(p->p.getParentId()==one.getId()).forEach(p->{
            //自己组装值
            MenuVO child=new MenuVO();
            child.setId(p.getId());
            child.setActionname(p.getActionName());
            child.setAuthority(p.getAuthority());
            child.setMenuname(p.getMenuName());

            //没有第三级节点 遍历所有的数据库 .parentId=14( p.getId())，装到 one对象的 List<MenuVO> childrens
            // loadSecondMenu(child,ownMenuList);

            childrenList.add(child);
        });

        //判断它否有子节点
        if(childrenList.size()>0){
            one.setChildrens(childrenList);
        }

    }


}
