//为角色分配菜单权限
var projectName;
$(function(){

    projectName=$("body").attr("data-project");

    initTreeMenu();

    //全选操作
    $("#checkAll").click(function () {
        var treeObj = $.fn.zTree.getZTreeObj("menu");

        var checkedValue=$(this).prop("checked");

        treeObj.checkAllNodes(checkedValue);
    });

    //保存按钮
    $("#btnSave").click(confirmSaveFormData);
});

//加载菜单
function initTreeMenu(){

    //设置
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    //数据 ajax
    var roleId=$("#id").val();

    $.ajax({
        type:"GET",
        url:projectName+"/admin/role/"+roleId+"/ownmenu",
        dataType:"json",
        success:function (resp) {
            if(resp.code===0){

                var zNodes=resp.result;

                $.fn.zTree.init($("#menu"), setting, zNodes);

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


//保存前的提示
function confirmSaveFormData(){

    BootstrapDialog.confirm({
        title: '确认',
        message: '确定要保存该角色的权限吗?',
        type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
        closable: true, // <-- Default value is false
        draggable: true, // <-- Default value is false
        closeByBackdrop: false, // 点击窗体以外的区域不允许关闭
        closeByKeyboard: true, //按下esc 键 能关闭
        btnCancelLabel: '取消', // <-- Default value is 'Cancel',
        btnOKLabel: '确定', // <-- Default value is 'OK',
        btnOKClass: 'btn-primary', // <-- If you didn't specify it, dialog type will be used,
        callback: function(result) {
            if(result) {
                //ajax操作保存数据
                SaveFormData();
            }
        }
    });

}

//保存数据
function SaveFormData(){
    var roleId=$("#id").val();
    //当前选中的值
    var treeObj = $.fn.zTree.getZTreeObj("menu");
    var nodes = treeObj.getCheckedNodes(true);

    //把id组装成   1-2-3 的字符串
    var  menuIds=[];
    for(var i in nodes){
        var obj=nodes[i];
        menuIds.push(obj.id);
       // menuIds[i]= obj.id;
    }

    //把集合转换成字符串    javascript自带的方法  join("")
    var str="";
    str=menuIds.join("-");

    //ajax提交给后台
    $.ajax({
        type:"POST",
        url:projectName+"/admin/role/savePermission",
        data:{
            "roleId":roleId,
            "perms":str
       },
        dataType:"json",
        success:function (resp) {
            if(resp.code===0){
                BootstrapDialog.show({
                    title: '操作成功',
                    type: BootstrapDialog.TYPE_PRIMARY,
                    message: resp.message
                });
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