//加载后台用户 bootstrap-table
var projectName="";
var primaryKey=0;

$(function () {

    //得到项目名称
    projectName=$("body").attr("data-project");

    //初始化 表格内容(远程加载后台的地址)
    initTable();

    initEvent();

    initFormValidator();
});


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
            menuName:{
                validators: {
                    notEmpty: {
                        message: '这是必填字段'
                    },
                }
            },  //验证结束
        }
    });
}


function initTable(){
    var $tb= $('#tb');
    $tb.bootstrapTable({
        url: projectName+'/admin/menu/data',
        method: 'POST',
        contentType:"application/x-www-form-urlencoded",   //使用post你就必须要更改
        striped: true,                         //是否显示行间隔色
        sidePagination: "server",              //分页方式：client客户端分页，server服务端分页（*）
        undefinedText: '',                  //空值怎么显示
        singleSelect: true,                  // 单选checkbox，默认为复选
        idField: 'id',
        columns: [
            {
                field: 'id',
                title:'编号',
                align: 'center'
            },
            {
                radio:true,
                align: 'center'
            },
            {
                field: 'menuName',
                title:'菜单名称',
                valign: 'middle'
            },{
                field: 'menuLevel',
                title:'类型',
                formatter:typeFormatter,
                valign: 'middle'
            },{
                field: 'actionName',
                title:'url地址',
                valign: 'middle'
            },{
                field: 'authority',
                title:'权限值',
                valign: 'middle'
            },{
                field: 'sort',
                title:'排序',
                valign: 'middle'
            },{
                field: 'display',
                title:'可见性',
                formatter:displayFormatter,
                valign: 'middle'
            },
        ],
        //配置treegird配置
        parentIdField:"parentId",
        treeShowField: 'menuName',
        //当数据加载完成的时候，进行
        //重置树形节点视图
        onResetView: function () {
            $tb.treegrid({
                initialState: 'expanded',//收缩collapsed,展开expanded
                treeColumn: 0,//指明第几列数据改为树形
                /* expanderExpandedClass: 'glyphicon glyphicon-tag',
                 expanderCollapsedClass: 'glyphicon glyphicon-book',*/
                onChange: function () {
                    var datas = $tb.bootstrapTable('getData');
                    console.log("datas:"+datas);
                    $tb.bootstrapTable('resetWidth');
                }
            });
        }
    });
}

//类型
function typeFormatter(value, row, index) {
    if (value == 1) {
        return '<span class="label label-primary">目录</span>'
    }
    else if (value == 2) {
        return '<span class="label label-info">菜单</span>'
    }
    else if( value==3){
        return '<span class="label label-default">按钮</span>'
    }

    return '-'
}

function displayFormatter(value, row, index){
    if (value == 1) {
        return '<span class="label label-success">可见</span>'
    }
    else {
        return '<span class="label label-danger">不可见</span>'
    }
}

function initEvent() {
    //新增
    $("#btnAdd").click(addForm);
    //修改按钮
    $("#btnEdit").click(editForm);
    //保存按钮
    $("#btnSave").click(saveFormData);
}

//新增操作
function addForm(){
    //新增主键String为空
    primaryKey=0;
    //清空数据
    resetFormValue("#editForm input");
    //重置验证规则
    $('#editForm').data('bootstrapValidator').resetForm(true);
    $("#myModalLabel").html("添加菜单");

    //显示模态框
    $('#myModal').modal('show');
}

//编辑操作
function editForm(){
    //判断你是否选中有要修改的数据
    var rows=$('#tb').bootstrapTable('getSelections');
    if(rows.length!=1){
        BootstrapDialog.show({
            title: '提示',
            type: BootstrapDialog.TYPE_DANGER,
            message:"请选择要修改的菜单"
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

    //循环还原值
    var elems=$("#editForm :input");
    for(var i=0;i<elems.length;i++){
        var el=$(elems[i]);
        var type=el.attr("name");

        for(var key in data){
            if(key===type){
                el.val(data[key] );
            }
        }
    }

    $("#myModalLabel").html("修改菜单");

    //显示模态框
    $('#myModal').modal('show');
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
    let data=$("#editForm").serialize();
    data+='&id='+primaryKey;
    //ajax提交数据
    $.ajax({
        type:"POST",
        url:projectName+"/admin/menu/save",
        data:data,
        dataType:"json",
        success:function(resp){
            if(resp.code===0){
                $('#myModal').modal('hide');
                BootstrapDialog.show({
                    title: '提示',
                    type: BootstrapDialog.TYPE_DANGER,
                    message: resp.message
                });
                //重新加载表格
                $('#tb').bootstrapTable("refresh");
            }else{
                BootstrapDialog.show({
                    title: '提示',
                    type: BootstrapDialog.TYPE_DANGER,
                    message: resp.message
                });
            }
        }
    });

}