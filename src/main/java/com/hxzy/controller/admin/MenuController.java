package com.hxzy.controller.admin;

import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.validator.ValidatorImpl;
import com.hxzy.common.validator.ValidatorResult;
import com.hxzy.entity.Menu;
import com.hxzy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private ValidatorImpl validatorImpl;

    /**
     * 显示用户查询的页面
     * @return
     */
    @GetMapping(value = "/menu/search")
    public String  search(Model model){
        List<Menu> menuList = menuService.findByParentId(0);
        model.addAttribute("menuList",menuList);
        return "admin/menu/list";
    }


    /**
     * ajax查询请求
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/menu/data")
    public ResponseMessage ajaxData(){
        //再次组装，添加自定义返回码
        ResponseMessage responseMessage=this.menuService.findAll();

        return  responseMessage;

    }

    /**
     * 新增修改菜单
     * @param menu 传入菜单对象
     * @return 是否成功
     */
    @ResponseBody
    @PostMapping(value = "/menu/save")
    public ResponseMessage saveMajor(Menu menu){
        //判断值是否有问题
        ValidatorResult validatorResult=validatorImpl.validate(menu);
        //有错误
        if(validatorResult.isHasErrors()){
            return new ResponseMessage(ResponseCodeEnum.PARAMETER_ERROR, validatorResult.getErroMsgMap());
        }
        return this.menuService.saveMenu(menu);
    }

}
