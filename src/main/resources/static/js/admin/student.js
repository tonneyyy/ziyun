var projectName="";
var primaryKey="";

$(function () {
    //得到项目名称
    projectName=$("body").attr("data-project");
    //初始化 表格内容(远程加载后台的地址)
    initTable();
    initEvent();
    //表单验证
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

//表单验证
function initFormValidator(){
    $('#editForm').bootstrapValidator({
        excluded: [':disabled'],  //排除的样式
        message: '必须输入值',
        feedbackIcons: {
            // valid: 'glyphicon glyphicon-ok',   //验证成功的图标样式
            // invalid: 'glyphicon glyphicon-remove',  //验证失败的图标样式
            // validating: 'glyphicon glyphicon-refresh'  //正在验证中的图标样式
        },
        fields: {
            //表单中的  元素的 name 进行设定它验证规则
            // majorId:{
            //     validators: {
            //         greaterThan: {
            //             value : 1,
            //             message : '请选择专业'
            //         },
            //     }
            // },  //验证结束
            // classesId:{
            //     validators: {
            //         greaterThan: {
            //             value : 1,
            //             message : '请选择班级'
            //         },
            //     }
            // },  //验证结束
            name:{
                validators: {
                    notEmpty: {
                        message: '当前选项不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '名字的长度必须在1-10个字符之间'
                    },
                }
            },  //验证结束
            mobile:{
                validators: {
                    notEmpty: {
                        message: '当前选项不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号码长度必须为11位'
                    },
                }
            },  //验证结束
            idCard:{
                validators: {
                    notEmpty: {
                        message: '当前选项不能为空'
                    },
                    stringLength: {
                        min: 18,
                        max: 18,
                        message: '身份证号码长度必须为18位'
                    },
                }
            },  //验证结束

        }
    });
}

//加载表格
function initTable(){
    $('#tb').bootstrapTable({
        url: projectName+'/admin/student/data',
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
            params["classesId"]=$("#search_classes").val();
            params["majorId"]=$("#search_major").val();
            params["name"]=$("#search_name").val();
            params["sex"]=$("#search_sex").val();
            params["mobile"]=$("#search_mobile").val();
            params["state"]=$("#search_state").val();
            params["id"]=$("#search_id").val();
            return params;
        },
        columns: [
            {
                radio: true
            },{
                field: 'id',
                title:'学号',
                valign: 'middle',
                sortable: true
            },{
                field: 'name',
                title:'名字',
                valign: 'middle',
                sortable: true
            },{
                field: 'majorName',
                title:'专业',
                valign: 'middle',
                sortable: true,
            },{
                field: 'className',
                title:'班级',
                valign: 'middle',
                sortable: true,
            },{
                field: 'portrait',
                title:'头像地址',
                valign: 'middle',
                formatter:portraitFormat
            },{
                field: 'mobile',
                title:'手机号码',
                valign: 'middle',
                sortable: true
            },{
                field: 'joinDate',
                title:'入学日期',
                valign: 'middle',
                sortable: true,
                formatter:function (vlue) {
                    return  vlue.substr(0,10)
                }
            },{
                field: 'sex',
                title:'性别',
                valign: 'middle',
                sortable: true
            },{
                field: 'education',
                title:'学历',
                valign: 'middle',
                sortable: true,
                formatter:educationFormatter
            },{
                field: 'state',
                title:'状态',
                valign: 'middle',
                formatter:stateFormatter
            }
        ]
    });
}

//头像
function portraitFormat(value,row,index){
    //状态
    if(value){
        return "<img src='"+value+"' width='50'>";
    }

    return "";

}

//状态:1正常 2休学 3退学 格式化列
function stateFormatter(value,row,index){
    //状态
    if(value==1){
        return "<span class=\"label label-success\">在读</span>";
    }
    if(value==2){
        return "<span class=\"label label-warning\">休学</span>";
    }
    return "<span class=\"label label-danger\">退学</span>";

}
//学历
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


function initEvent() {
    //查询专业选择事件
    $("#search_major").change(function () {
        let majorId =$(this).val();
        //获取班级列表元素
        let classes=$("#search_classes");
        //清空班级列表内容
        classes.find("option:gt(0)").remove();
        ajaxLoadClass(majorId,classes);
    });
    //查询按钮事件
    $("#btnSelect").click(searchStudent);
    //新增
    $("#btnAdd").click(addForm);
    //修改按钮
    $("#btnEdit").click(editForm);
    // //保存按钮
    $("#btnSave").click(saveFormData);

    $("#edit_major").on("change",function () {
        let majorId =$(this).val();
        //获取班级列表元素
        let classes=$("#edit_classes");
        //清空班级列表内容
        classes.find("option:gt(0)").remove();
        ajaxLoadClass(majorId,classes);
    });


    //图片插件
    KindEditor.ready(function(K) {


        var editor = K.editor({
            uploadJson :  projectName+"/api/upload",  //配置文件上传路径
            allowFileManager : false   //不允许查看服务器图片文件
        });

        K('#btnChoose').click(function() {
            //加载图片插件
            editor.loadPlugin('image', function() {
                editor.plugin.imageDialog({
                    showRemote : false,
                    imageUrl : K('#edit_portrait').val(),
                    clickFn : function(url, title, width, height, border, align) {
                        //上传完成以后url回调服务器地址
                        K('#edit_portrait').val(url);
                        editor.hideDialog();
                    }
                });
            });
        });
    });



}

function searchStudent() {
    $("#tb").bootstrapTable('refresh',{query:{offset:0}});
}


//新增操作
function addForm(){
    //新增主键String为空
    primaryKey="";
    //清空数据
    resetFormValue("#editForm input");
    //获取班级列表元素
    let classes=$("#edit_classes");
    //清空班级列表内容
    classes.find("option:gt(0)").remove();
    $("#edit_major option:first").prop("selected","selected");
    $("#edit_classes option:first").prop("selected","selected");
    $("#edit_education option:first").prop("selected","selected");
    $("#edit_state option:first").prop("selected","selected");

    //重置验证规则
    $('#editForm').data('bootstrapValidator').resetForm(true);
    $("#myModalLabel").html("添加学生");

    //显示模态框
    $('#myModal').modal('show');
}


//ajax获取班级请求
function ajaxLoadClass(majorId,classes,classesId) {
    if(majorId>0){
        $.ajax({
            type: "GET",
            url: projectName+"/admin/classes/"+majorId+"/data",
            dataType:"json",
            success: function(resp){
                if(resp.code===0){
                    let dataList=resp.result;
                    for(let i=0;i<dataList.length;i++){
                        //根据下标取出每个班级
                        let data=dataList[i];
                        let str=``;
                        if(classesId==data.id){
                            str =`<option value="${data.id}" selected>${data.name}</option>`;
                        }else {
                            str=`<option value="${data.id}">${data.name}</option>`;
                        }

                        //追加到列表中去
                        classes.append(str);
                    }
                }else{
                    BootstrapDialog.show({
                        title: '提示',
                        type: BootstrapDialog.TYPE_DANGER,
                        message: "数据解析失败"
                    });
                }
            }
        });

    }
}


//编辑操作
function editForm(){
    //判断你是否选中有要修改的数据
    var rows=$('#tb').bootstrapTable('getSelections');
    if(rows.length!=1){
        BootstrapDialog.show({
            title: '提示',
            type: BootstrapDialog.TYPE_DANGER,
            message:"请选择要修改的班级"
        });
        return;
    }
    let date=new Date(rows[0]["joinDate"]).getTime();





    let halfYear=getHalfYearAgoDate();




    if(halfYear>date){
        BootstrapDialog.show({
            title: '提示',
            type: BootstrapDialog.TYPE_DANGER,
            message:"请选择最近半年的学生"
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
    //获取班级列表元素
    let classes=$("#edit_classes");
    //清空班级列表内容
    classes.find("option:gt(0)").remove();
    ajaxLoadClass(data.majorId,classes,data.classesId);

    //循环还原值
    var elems=$("#editForm :input");
    for(var i=0;i<elems.length;i++){
        var el=$(elems[i]);
        var type=el.attr("name");

        for(var key in data){
            if(key===type){
                if(key==="joinDate"||key==="birthday"){
                    el.val(data[key].substr(0,10) );
                }else {
                    el.val(data[key] );
                }
            }
        }
    }

    $("#myModalLabel").html("修改学生");

    //显示模态框
    $('#myModal').modal('show');
}

//获取当前日期半年前日期
function getHalfYearAgoDate(){
    let curDate = (new Date()).getTime();
    // 将半年的时间单位换算成毫秒
    let halfYear = 365 / 2 * 24 * 3600 * 1000;
    let result = curDate - halfYear;  // 半年前的时间（毫秒单位）

    return result;
}


//组装对象数据
function getClasses() {
    //组装对象
    let student = {
        id:primaryKey,
        name: $("#edit_name").val(),
        majorId : $("#edit_major").val(),
        classesId : $("#edit_classes").val(),
        portrait:$("#edit_portrait").val(),
        mobile :$("#edit_mobile").val(),
        joinDate:$("#edit_joinDate").val(),

        // joinDate:function () {
        //     let date=$("#edit_joinDate").val().substr(0,10).replace(/-/g,"/");
        //     return new Date(date);
        // },
        sex :$("#edit_sex").val(),
        birthday:$("#edit_birthday").val(),
        // birthday:function () {
        //     let date=$("#edit_birthday").val().substr(0,10).replace(/-/g,"/");
        //     return new Date(date);
        // },
        education:$("#edit_education").val(),
        schoolName:$("#edit_schoolName").val(),
        collegeMajor:$("#edit_collegeMajor").val(),
        idCard:$("#edit_idCard").val(),
        qq:$("#edit_qq").val(),
        homeAddress:$("#edit_homeAddress").val(),
        currentAddress:$("#edit_currentAddress").val(),
        state:$("#edit_state").val(),
    };
    return student;
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
    let data=getClasses();
    //ajax提交数据
    $.ajax({
        type:"POST",
        url:projectName+"/admin/student/save",
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