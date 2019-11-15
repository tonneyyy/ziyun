//加载后台用户 bootstrap-table
var projectName="";
var primaryKey=0;  //主键

$(function () {

    //得到项目名称
    projectName=$("body").attr("data-project");

    //初始化 表格内容(远程加载后台的地址)
   initTable();

   //注册按钮事件
    initEvent();

    //表单非空验证规则
    initFormValidator();


});


function initTable(){
    $('#tb').bootstrapTable({
        url: projectName+'/admin/role/data',
        method: 'POST',
        contentType:"application/x-www-form-urlencoded",   //使用post你就必须要更改
        striped: true,                         //是否显示行间隔色
        cache: false,                          //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: true,                        //是否启用排序
        sortOrder: "asc",                      //排序方式
        sidePagination: "server",              //分页方式：client客户端分页，server服务端分页（*）
        undefinedText: '',                  //空值怎么显示
        singleSelect: true,                  // 单选checkbox，默认为复选
        showRefresh   : false,                  // 显示刷新按钮
        showColumns   : true,                  // 选择显示的列
        strictSearch: true,
        clickToSelect: true,                   // 点击选中行
        pagination: true,                      //是否显示分页
        pageNumber:1,                          //初始化加载第一页，默认第一页,并记录
        pageSize:5,                            //每页显示的数量
        pageList: [5, 10, 20, 50, 100],        //设置每页显示的数量
        paginationPreText:"上一页",
        paginationNextText:"下一页",
        queryParams : function (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            return params;
        },
        columns: [
            {
                radio: true
            },
            {
                field: 'id',
                title:'编号',
                valign: 'middle',
                sortable: true
            },
            {
                field: 'rolename',
                title:'角色名称',
                valign: 'middle',
                sortable: true
            },{
                field: 'authority',
                title:'角色标识',
                valign: 'middle'
            },{
                field: 'state',
                title:'状态',
                formatter:stateFormatter,
                valign: 'middle'
            },
        ]
    });
}

//格式化列
function stateFormatter(value,row,index){
    //
    if(value==1){
      return "<span class=\"label label-primary\">启用</span>";
    }
    return "<span class=\"label label-danger\">禁用</span>";

}

function initEvent(){

    //新增
    $("#btnAdd").click(addForm);

    //修改按钮
    $("#btnEdit").click(editForm);

    //保存按钮
    $("#btnSave").click(saveFormData);

    //为角色分配菜单权限
    $("#btnRoleMenu").click(roleMenuPermission);

    //为角色添加用户
    $("#btnOwnUser").click(ownUserPermission);

}

//新增操作
function addForm(){

    //主键一定为0
    primaryKey=0;

    //清空数据
    resetFormValue("#editForm input");

    //重置验证规则
    $('#editForm').data('bootstrapValidator').resetForm(true);


    $("#myModalLabel").html("添加数据");
    //显示模态框
    $('#myModal').modal('show');
}

//非空验证
function initFormValidator(){
    $('#editForm').bootstrapValidator({
        excluded: [':disabled'],  //排除的样式
        message: '必须输入值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',   //验证成功的图标样式
            invalid: 'glyphicon glyphicon-remove',  //验证失败的图标样式
            validating: 'glyphicon glyphicon-refresh'  //正在验证中的图标样式
        },
        fields: {
            //表单中的  元素的 name 进行设定它验证规则
            rolename:{
                validators: {
                    notEmpty: {
                        message: '这是必填字段'
                    },
                    remote: {
                        //验证规则应该是当焦点移开的时候，才验证，不然后台  扛不住的
                        url: projectName+'/admin/role/exist/rolename',
                        type:"POST",
                        data: {
                            id: function(){ return  primaryKey ;   }   //自定参数
                        },
                        message: '该值已被占用，请重新输入'
                    },
                }
            },  //验证结束
            authority:{
                validators: {
                    notEmpty: {
                        message: '这是必填字段'
                    },
                    stringLength: {
                        min: 2,
                        max: 15,
                        message: '值的长度必须在2-15个字符之间'
                    },
                    remote: {
                        url: projectName+'/admin/role/exist/authority',
                        type:"POST",
                        data: {
                            id: function(){ return  primaryKey ;   }   //自定参数
                        },
                        message: '该值已被占用，请重新输入'
                    },
                }
            },  //验证结束
        }
    });
}



//保存操作
function saveFormData(){
    //验证数据有效性
    var bsValidate=$('#editForm').data('bootstrapValidator');
    //触发验证规则
    bsValidate.validate();

    //禁用保存按钮，防止重复提交
    $("#btnSave").attr("disabled","disabled");

    //延迟1秒再去判断  setTimeout(方法,  1000);
    setTimeout(function(){
        //异步的，它有延迟
        var result=bsValidate.isValid();

        if(result){
            asyncSaveData();
        }
       //启用保存按钮
        $("#btnSave").removeAttr("disabled");
    },1200);

}

//异步延迟操作
function asyncSaveData(){
    //验证通过
        var saveData=$("#editForm").serialize();
        //主键加进去
        saveData+="&id="+primaryKey;

        console.log(saveData);

        //ajax提交数据
        $.ajax({
            type:"POST",
            url:projectName+"/admin/role/save",
            data:saveData,
            dataType:"json",
            success:function(resp){
                if(resp.code===0){
                    $('#myModal').modal('hide');
                    //重新加载表格
                    $('#tb').bootstrapTable("refresh");
                }else{
                    BootstrapDialog.show({
                        title: '操作失败',
                        type: BootstrapDialog.TYPE_DANGER,
                        message: resp.message
                    });
                }
            }
        });

}



//编辑操作
function editForm(){

    //判断你是否选中有要修改的数据
    var rows=$('#tb').bootstrapTable('getSelections');
    if(rows.length!=1){

        BootstrapDialog.show({
            title: '警告',
            type: BootstrapDialog.TYPE_DANGER,
            message:"请选择要修改的数据"
        });

        return;
    }

    //清空数据
    resetFormValue("#editForm input");

    //重置验证规则
    $('#editForm').data('bootstrapValidator').resetForm(true);

    //还原数据
    var data=rows[0];
    //主键
    primaryKey=data.id;

    //其它的表单元素( 理论上是： 编写 一个方法 循环  还原值)
    $("#edit_rolename").val(data.rolename);
    $("#edit_authority").val(data.authority);
    $("#edit_state").val(data.state);


    $("#myModalLabel").html("编辑数据");
    //显示模态框
    $('#myModal').modal('show');
}



//为角色分配菜单权限(新打开一个页面)
function roleMenuPermission(){
    //判断你是否选中有要修改的数据
    var rows=$('#tb').bootstrapTable('getSelections');
    if(rows.length!=1){
        BootstrapDialog.show({
            title: '警告',
            type: BootstrapDialog.TYPE_DANGER,
            message:"请选择要修改的数据"
        });

        return;
    }

    let data=rows[0];
    let  id=data.id;

    window.location.href=projectName+"/admin/role/"+id+"/permission";
}

//为角色添加用户(新打开一个页面)
function ownUserPermission(){
    //判断你是否选中有要修改的数据
    var rows=$('#tb').bootstrapTable('getSelections');
    if(rows.length!=1){
        BootstrapDialog.show({
            title: '警告',
            type: BootstrapDialog.TYPE_DANGER,
            message:"请选择要修改的数据"
        });

        return;
    }

    let data=rows[0];
    let  id=data.id;

    window.location.href=projectName+"/admin/role/"+id+"/ownuser";


}