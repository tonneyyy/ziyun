var projectName="";
var primaryKey="";

$(function () {
    projectName=$("body").attr("data-project");

    initTable();

    initEvent();



    $("#btnSave").click(function () {
        var data=$("#editForm").serialize();
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
        primaryKey=rows[0].id;

        //ajax提交数据
        $.ajax({
            type:"POST",
            url:projectName+"/admin/members/"+primaryKey+"/save",
            data:data,
            dataType:"json",
            success:function(resp){
                if(resp.code===0){
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



    });


});

function initTable() {
    var $tb=$('#tb');
    $tb.bootstrapTable({
        url:projectName+'/admin/members/data',
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
            params["loginName"]=$("#membersown").val();
            params["mobile"]=$("#mobile").val();

            return params;
        },
        columns:[
            {
                radio:true
            },
            {
                field: 'loginName',
                title:'用户名',
                valign: 'middle',
                sortable: true
            },
            {
                field: 'loginPassword',
                title:'加密密码',
                valign: 'middle'
            },{
                field: 'mobile',
                title:'手机号',
                valign: 'middle'
            },{
                field: 'salt',
                title:'盐',
                valign: 'middle'
            },{
                field: 'regDate',
                title:'注册时间',
                sortable: true
            },{
                field: 'lastLoginDate',
                title:'最后一次登录时间',
                valign: 'middle'
            },{
                field: 'isGeust',
                title:'是否游客',
                valign: 'middle'
            },{
                field: 'studentId',
                title:'学生号',
                valign: 'middle'
            },{
                field: 'state',
                title:'状态',
                valign: 'middle',
                formatter:stateFormatter
            },
        ]
    });
}

function stateFormatter(value,row,index) {
    if(value==1){
        return "<span class=\"label label-primary\">启用</span>";
    }
    return "<span class=\"label label-danger\">禁用</span>";
}




function initEvent() {
//重新加载表格
    $("#btnSearch").click(function () {
        $("#tb").bootstrapTable('refresh',{query:{offset:0}});
    })


}


