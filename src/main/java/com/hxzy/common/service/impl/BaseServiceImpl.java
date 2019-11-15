package com.hxzy.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.common.search.PageSearch;
import com.hxzy.common.service.BaseService;
import com.hxzy.vo.ResultData;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 公共的业务逻辑实现
 * @param <Model>
 * @param <PK>
 */
@Log4j2
@Transactional
public class BaseServiceImpl<Model, PK extends Serializable> implements BaseService<Model, PK> {


    private BaseMapper  baseMapper=null;

    /**
     * 子类设定
     * @param baseMapper
     */
    public void setBaseMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean insert(Model model) {
        if(baseMapper==null){
            log.error("BaseServiceImpl中的insert方法，baseMapper没有初始化");
        }


        /*
        没有使用spring之前的代码

           //1、得到SqlSession
        SqlSession  sqlSession= MyBatisUtil.getSqlSessionFactory(DataSourceEnvironment.shop).openSession();
        //2、得到mysql中的com.hxzy.mapper.UsersMapper 的代理对象(mybatis帮你生成的) src/main/resources/mapper/UsersMapper.xml转换成java类
        UsersMapper  usersMapper=sqlSession.getMapper(UsersMapper.class);
        //3、进行新增操作
        User u=usersMapper.findOne(1);


        //4、关闭sqlSession(还给连接池)
        sqlSession.close();
         */


        return this.baseMapper.insert(model)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean update(Model model) {
        if(baseMapper==null){
            log.error("BaseServiceImpl中的update方法，baseMapper没有初始化");
        }
        return this.baseMapper.update(model)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateSelective(Model model) {
        if(baseMapper==null){
            log.error("BaseServiceImpl中的updateSelective方法，baseMapper没有初始化");
        }
        return this.baseMapper.updateSelective(model)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Model findOne(PK id) {
        if(baseMapper==null){
            log.error("BaseServiceImpl中的updateSelective方法，baseMapper没有初始化");
        }
        return (Model) this.baseMapper.findOne(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResultData searchPage(PageSearch pageSearch) {
        if(baseMapper==null){
            log.error("BaseServiceImpl中的searchPage方法，baseMapper没有初始化");
        }

        //分页查询
        PageHelper.offsetPage(pageSearch.getOffset(),pageSearch.getLimit());

        List rows=this.baseMapper.searchPage(pageSearch);

        //强转
        Page  pg=(Page) rows;

        //返回结果
        ResultData resultData=new ResultData();
        resultData.setRows(rows);
        resultData.setTotal(pg.getTotal());
        return resultData;
    }
}
