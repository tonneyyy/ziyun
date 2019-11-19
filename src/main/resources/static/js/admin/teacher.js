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


    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        minView: 2,
        format:"yyyy-mm-dd"
    });

});


function initTable(){
    $('#tb').bootstrapTable({
        url: projectName+'/admin/teacher/data',
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
                field: 'name',
                title:'名称',
                valign: 'middle',
                sortable: true
            },{
                field: 'teachKnowledgeString',
                title:'所带专业',
                valign: 'middle'
            },
            {
                field: 'mobile',
                title:'电话号码',
                valign: 'middle'

            },
            {
                field: 'sex',
                title:'性别',
                valign: 'middle',
                sortable: true
            },
            {
                field: 'birthday',
                title:'生日',
                valign: 'middle',
                sortable: true,
                formatter:function (vlue) {
                    return  vlue.substr(0,10)
                }
            },
            {
                field: 'education',
                title:'学历',
                formatter:educationFormatter,
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
        return "<span class=\"label label-primary\">正常</span>";
    }else {
        return "<span class=\"label label-primary\">休假</span>";
    }
    return "<span class=\"label label-danger\">离职</span>";

}
function educationFormatter(value,row,index) {
    if(value==4){
        return "<span class=\"label label-success\">研究生</span>";
    }
    if(value==1){
        return "<span class=\"label label-primary\">本科</span>";
    }
    if(value==2){
        return "<span class=\"label label-warning\">专科</span>";
    }
    return "<span class=\"label label-danger\">专科以下</span>";
}




function initEvent(){

    //新增
    $("#btnAdd").click(addForm);

    //修改按钮
    $("#btnEdit").click(editForm);

    //保存按钮
    $("#btnSave").click(saveFormData);



}

//新增操作
function addForm(){

    //主键一定为0
    primaryKey=0;

    //清空数据
    resetFormValue("#editForm input ");
    $("select").val("0");

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
            name:{
                validators: {
                    notEmpty: {
                        message: '这是必填字段'
                    },
                    remote: {
                        //验证规则应该是当焦点移开的时候，才验证，不然后台  扛不住的
                        url: projectName+'/admin/teacher/exist/name',
                        type:"POST",
                        data: {
                            id: function(){ return  primaryKey ;   }   //自定参数
                        },
                        message: '该值已被占用，请重新输入'
                    },
                }
             },  //验证结束
            mobile:{
                validators: {
                    notEmpty: {
                        message: '这是必填字段'
                    },
                    stringLength: {
                        length:11,
                        message: '值的长度必须在11个字符之间'
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
        url:projectName+"/admin/teacher/save",
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

    $("#pwd").hide();
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
    resetFormValue("#editForm input ");
    $("select").val("0");


    //重置验证规则
    $('#editForm').data('bootstrapValidator').resetForm(true);



    //还原数据
    var data=rows[0];
    //主键
    primaryKey=data.id;


    $("#edit_name").val(data.name);

    $("#edit_teachknowledge").val(data.teachKnowledge);
    $('#edit_teachknowledge').selectpicker('refresh');

    $("#edit_mobile").val(data.mobile);
    $("#edit_sex").val(data.sex);
    $("#edit_birthday").val(data.birthday);
    $("#edit_mobile").val(data.mobile);
    $("#edit_education").val(data.education);
    $("#edit_state").val(data.state);



    $("#myModalLabel").html("编辑数据");
    //显示模态框
  $('#myModal').modal('show');
}



