var projectName = "";
var primaryKey = 0;


$(function(){

    primaryKey = $("#id").val();

    projectName = $("body").attr("data-project");

    initownUserTable();

    initnoOwnUserTable();

    $("#btnRemove").click(saveRemove)

    $("#btnAdd").click(saveAdd);
});

function initownUserTable() {
    $('#ownTeacherTb').bootstrapTable({
        url: projectName+'/admin/classes/ownteacher/data',
        method: 'POST',
        contentType:"application/x-www-form-urlencoded",   //使用post你就必须要更改
        striped: true,                         //是否显示行间隔色
        cache: false,                          //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: true,                        //是否启用排序
        sortOrder: "asc",                      //排序方式
        sidePagination: "server",              //分页方式：client客户端分页，server服务端分页（*）
        undefinedText: '',                  //空值怎么显示
        singleSelect: false,                  // 单选checkbox，默认为复选
        showRefresh   : false,                  // 显示刷新按钮
        showColumns   : false,                  // 选择显示的列
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
            params["classesId"] = primaryKey;
            return params;
        },
        columns:[
            {
                checkbox: true
            },
            {
                field: 'id',
                title:'编号',
                valign: 'middle',
                sortable: true
            },
            {
                field: 'name',
                title:'老师名称',
                valign: 'middle',
                sortable: true
            },
            {
                field: 'teachKnowledge',
                title:'所带专业',
                valign: 'middle'
            },
            {
                field: 'sex',
                title:'性别',
                valign: 'middle'
            },
            {
                field: 'education',
                title:'学历',
                formatter:function (value,row,index) {
                    if(value==1){
                        return "本科"
                    }
                    if(value==2){
                        return "专科"
                    }
                    if(value == 3){
                        return "专科以下"
                    }
                    if(value == 4){
                        return "研究生"
                    }
                },
                valign: 'middle'
            },
            {
                field: 'state',
                title:'状态',
                valign: 'middle'
            },
        ]
    });
}

function initnoOwnUserTable() {
    $('#noOwnTeacherTb').bootstrapTable({
        url: projectName+'/admin/classes/noownteacher/data',
        method: 'POST',
        contentType:"application/x-www-form-urlencoded",   //使用post你就必须要更改
        striped: true,                         //是否显示行间隔色
        cache: false,                          //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: true,                        //是否启用排序
        sortOrder: "asc",                      //排序方式
        sidePagination: "server",              //分页方式：client客户端分页，server服务端分页（*）
        undefinedText: '',                  //空值怎么显示
        singleSelect: false,                  // 单选checkbox，默认为复选
        showRefresh   : false,                  // 显示刷新按钮
        showColumns   : false,                  // 选择显示的列
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
            params["classesId"] = primaryKey;
            return params;
        },
        columns: [
            {
                checkbox: true
            },
            {
                field: 'id',
                title:'编号',
                valign: 'middle',
                sortable: true
            },
            {
                field: 'name',
                title:'老师名称',
                valign: 'middle',
                sortable: true
            },
            {
                field: 'teachKnowledge',
                title:'所带专业',
                valign: 'middle'
            },
            {
                field: 'sex',
                title:'性别',
                valign: 'middle'
            },
            {
                field: 'education',
                title:'学历',
                formatter:function (value,row,index) {
                    if(value==1){
                        return "本科"
                    }
                    if(value==2){
                        return "专科"
                    }
                    if(value == 3){
                        return "专科以下"
                    }
                    if(value == 4){
                        return "研究生"
                    }
                },
                valign: 'middle'
            },
            {
                field: 'state',
                title:'状态',
                valign: 'middle'
            },
        ]
    });
}

function saveRemove() {
    var rows=$("#ownTeacherTb").bootstrapTable('getAllSelections');
    if(rows.length === 0){
        BootstrapDialog.show({
            title: '警告',
            type: BootstrapDialog.TYPE_DANGER,
            message:"请选择要修改的数据"
        });
        return;
    }
    console.log(rows.length);
    var teacherId = [];
    var arr = $('#ownTeacherTb').bootstrapTable('getAllSelections');
    for(var i in arr){
        var id = arr[i].id
        teacherId[i] = id;
    }

    $.ajax({
        type:"POST",
        url:projectName+"/admin/classes/saveRemove",
        data:{
            "classesId":primaryKey,
            "permsList":teacherId
        },
        dataType:"json",
        success:function (resp) {
            if(resp.code===0){
                BootstrapDialog.show({
                    title: '操作成功',
                    type: BootstrapDialog.TYPE_PRIMARY,
                    message: resp.message
                });
                $('#ownTeacherTb').bootstrapTable("refresh");
                $('#noOwnTeacherTb').bootstrapTable("refresh");
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

function saveAdd() {
    var rows=$('#noOwnTeacherTb').bootstrapTable('getAllSelections');
    if(rows.length === 0 ){
        BootstrapDialog.show({
            title: '警告',
            type: BootstrapDialog.TYPE_DANGER,
            message:"请选择要修改的数据"
        });
        return;
    }

    var teacherId = [];
    var arr = $('#noOwnTeacherTb').bootstrapTable('getAllSelections');
    for(var i in arr){
        var id = arr[i].id
        teacherId[i] = id;
    }

    $.ajax({
        type:"POST",
        url:projectName+"/admin/classes/saveAdd",
        data:{
            "classesId":primaryKey,
            "permsList":teacherId
        },
        dataType:"json",
        success:function (resp) {
            if(resp.code===0){
                BootstrapDialog.show({
                    title: '操作成功',
                    type: BootstrapDialog.TYPE_PRIMARY,
                    message: resp.message
                });
                $('#ownTeacherTb').bootstrapTable("refresh");
                $('#noOwnTeacherTb').bootstrapTable("refresh");
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
