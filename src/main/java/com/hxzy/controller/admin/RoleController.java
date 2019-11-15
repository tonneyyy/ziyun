package com.hxzy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.search.PageSearch;
import com.hxzy.common.validator.ValidatorImpl;
import com.hxzy.common.validator.ValidatorResult;
import com.hxzy.entity.Role;
import com.hxzy.service.RoleService;
import com.hxzy.vo.ResultData;
import com.hxzy.vo.RoleSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ValidatorImpl validatorImpl;

    /**
     * 显示角色查询的页面
     * @return
     */
    @GetMapping(value = "/role/search")
    public String  search(){

        return "admin/role/list";
    }


    /**
     * ajax分页查询请求
     * @param pageSearch
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/role/data")
    public ResponseMessage ajaxData(PageSearch pageSearch){
        //分页用的数据
        ResultData rs= this.roleService.searchPage(pageSearch);
        //再次组装，添加自定义返回码
        ResponseMessage responseMessage=new ResponseMessage(ResponseCodeEnum.SUCCESS,rs);

        return  responseMessage;
    }

    /**
     * 判断名称是否存在
     * @param role
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/role/exist/rolename")
    public JSONObject ajaxExistRoleName(Role role){

        return  this.roleService.existRoleName(role);
    }

    /**
     * 判断权限值是否存在
     * @param role
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/role/exist/authority")
    public JSONObject ajaxExistAuthority(Role role){

        return  this.roleService.existAuthority(role);
    }

    /**
     * 新增或修改保存操作
     * @param role
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/role/save")
    //public ResponseMessage save(@Validated  Role role, BindingResult bindingResult)
    public ResponseMessage save(Role role){

        //判断值是否有问题
        ValidatorResult validatorResult=validatorImpl.validate(role);
        //有错误
       if(validatorResult.isHasErrors()){
           return new ResponseMessage(ResponseCodeEnum.PARAMETER_ERROR, validatorResult.getErroMsgMap());
       }


        //角色后台还没有验证的

        boolean result=false;
        //新增
        if(role.getId()==null ||  role.getId()==0){
            result=this.roleService.insert(role);
        }else{
            result=this.roleService.update(role);
        }

        ResponseMessage responseMessage=null;
        if(result){
            responseMessage =new ResponseMessage(ResponseCodeEnum.SUCCESS);
        }else{
            responseMessage =new ResponseMessage(ResponseCodeEnum.ERROR);
        }

        return responseMessage;
    }


    /**
     * 为角色分配权限的页面
     * @return
     */
    @GetMapping(value = "/role/{id}/permission")
    public String roleMenuPermission(@PathVariable("id") int id, Model model){
        Role role=this.roleService.findOne(id);
        model.addAttribute("role",role);
        return "admin/role/permission";
    }

    /**
     * 查询角色拥有的菜单ztree ajax数据
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/role/{id}/ownmenu")
    public ResponseMessage roleOwnMenuData(@PathVariable("id") int id){
        return  this.roleService.searchRoleOwnMenu(id);
    }

    /**
     * 为角色保存权限
     * @param roleId
     * @param perms
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/role/savePermission")
    public ResponseMessage savePermission(int roleId, String perms){
        return this.roleService.savePermission(roleId,perms);
    }


    /**
     * 显示角色拥有用户
     * @param roleId
     * @param model
     * @return
     */
    @GetMapping(value = "/role/{roleId}/ownuser")
    public String showOwnRoleUserPage(@PathVariable("roleId") int roleId, Model model){
        Role role=this.roleService.findOne(roleId);
        model.addAttribute("role",role);
        return "admin/role/ownuser";
    }

    @ResponseBody
    @PostMapping(value = "/role/ownuser/data")
    public ResponseMessage ownUserData(RoleSearch roleSearch){
        return this.roleService.ownRoleUser(roleSearch);
    }

    @ResponseBody
    @PostMapping(value = "/role/noownuser/data")
    public ResponseMessage noOwnUserData(RoleSearch roleSearch){
        return this.roleService.noOwnRoleUser(roleSearch);
    }


    /**
     * 为角色添加用户
     * @param roleId
     * @param permList
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/role/addown")
    public ResponseMessage addUserPermission(int roleId, @RequestParam("permList[]") List<Integer> permList ){
         return this.roleService.addUserPermission(roleId,permList);
    }

    /**
     * 为角色移出用户
     * @param roleId
     * @param permList
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/role/remove")
    public ResponseMessage removeUserPermission(int roleId, @RequestParam("permList[]") List<Integer> permList ){
        return this.roleService.removeUserPermission(roleId,permList);
    }

}
