var projectName="";
var primaryKey;


$(function () {

    primaryKey=$("#id").val();

    //得到项目名称
    projectName=$("body").attr("data-project");

    //初始化 表格内容(远程加载后台的地址)
    initOwnUserTable();

    //初始化 表格内容(远程加载后台的地址)
    initNoOwnUserTable();

    initEvent();

});

function initOwnUserTable(){
    $('#ownUserTb').bootstrapTable({
        url: projectName+'/admin/role/ownuser/data',
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
        pageSize:10,                            //每页显示的数量
        pageList: [5, 10, 20, 50, 100],        //设置每页显示的数量
        paginationPreText:"上一页",
        paginationNextText:"下一页",
        queryParams : function (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            params["roleId"]=primaryKey;
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
                field: 'username',
                title:'用户名',
                valign: 'middle',
                sortable: true
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

function initNoOwnUserTable(){
    $('#noOwnUserTb').bootstrapTable({
        url: projectName+'/admin/role/noownuser/data',
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
        pageSize:10,                            //每页显示的数量
        pageList: [5, 10, 20, 50, 100],        //设置每页显示的数量
        paginationPreText:"上一页",
        paginationNextText:"下一页",
        queryParams : function (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            params["roleId"]=primaryKey;
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
                field: 'username',
                title:'用户名',
                valign: 'middle',
                sortable: true
            }
        ]
    });
}

function initEvent(){

    //增加用户角色
    $("#btnAdd").click(addPermission);

    //移出用户
    $("#btnRemove").click(removePermission);

    
}

function addPermission(){
    //判断你是否选中有要修改的数据
    var rows=$('#noOwnUserTb').bootstrapTable('getSelections');
    if(rows.length==0){
        BootstrapDialog.show({
            title: '警告',
            type: BootstrapDialog.TYPE_DANGER,
            message:"请从右边选择用户信息"
        });
        return;
    }

    //取得名称
    let arr=[];
    for(let i in rows){
       let obj=rows[i];
       arr.push(obj.id);
    }

    //保存的数据
    let saveData={
        "roleId":primaryKey,
        "permList":arr
    };

    $.ajax({
        type:"POST",
        url:projectName+"/admin/role/addown",
        data:saveData,
        dataType:"json",
        success:function (resp) {
            if(resp.code===0){
                //重新加载表格
                $('#noOwnUserTb').bootstrapTable("refresh", {query:{offset:0}});
                $('#ownUserTb').bootstrapTable("refresh",{query:{offset:0}});
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

function removePermission(){
    //判断你是否选中有要修改的数据
    var rows=$('#ownUserTb').bootstrapTable('getSelections');
    if(rows.length==0){
        BootstrapDialog.show({
            title: '警告',
            type: BootstrapDialog.TYPE_DANGER,
            message:"请从左边选择移出用户信息"
        });
        return;
    }

    //取得名称
    let arr=[];
    for(let i in rows){
        let obj=rows[i];
        arr.push(obj.id);
    }

    //保存的数据
    let saveData={
        "roleId":primaryKey,
        "permList":arr
    };

    $.ajax({
        type:"POST",
        url:projectName+"/admin/role/remove",
        data:saveData,
        dataType:"json",
        success:function (resp) {
            if(resp.code===0){
                //重新加载表格
                $('#noOwnUserTb').bootstrapTable("refresh", {query:{offset:0}});
                $('#ownUserTb').bootstrapTable("refresh",{query:{offset:0}});
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
