var projectName;

$(function () {
    projectName=$("body").attr("data-project");


    initValidator();

    //点击验证
    $("#btnSave").click(saveData);


});

function saveData(){
    // 触发验证操作
    // 手动调用，验证表单是否通过了
    var bsv = $('#editForm').data('bootstrapValidator');
    // 手动调用
    bsv.validate();
    setTimeout(function(){
        var result=bsv.isValid();
        // 验证不成功
        if(!result){
            return;
        }
        //更改验证成功的名称
       sendAjaxData();

    },200);

}

//发送ajax操作
function sendAjaxData(){
    
    var dataValue=$("#editForm").serialize();
    
    $.ajax({
        type:"POST",
        url:projectName+"/admin/savePassword",
        data:dataValue,
        dataType:"json",
        success:function (resp) {
            if(resp.code===0){

                //提示
                BootstrapDialog.show({
                    message: '成功',
                    message: '更新密码成功，请重新登录!',
                    closable: true,
                    closeByBackdrop: false,
                    closeByKeyboard: false,
                    buttons: [{
                        label: '确定',
                        action: function(dialogRef){
                            dialogRef.close();
                            window.location.href=projectName+"/admin/login";
                        }
                    }]
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

//验证
function initValidator(){

    $('#editForm').bootstrapValidator({
        excluded: [':disabled'],  // 排除的样式
        message: '输入的值无效',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok', // 验证通过的图标
            invalid: 'glyphicon glyphicon-remove',  // 验证失败的图标
            validating: 'glyphicon glyphicon-refresh'  // 正在验证中的图标
        },
        fields: {
            // 添加验证的表单的name
            password:{
            message: '输入的值无效',
            validators:{
                notEmpty: {
                    message: '密码不能为空'
                },
                stringLength: {
                    min: 4,
                    max: 20,
                    message: '值的长度必须在4-20个字符之间'
                },
              } // validators验证结束
           }, // 字段结束
            passwordConfirm:{
                message: '输入的值无效',
                validators:{
                    identical: {
                        field: 'password',
                        message: '两次输入的密码不一致'
                    }
                } // validators验证结束
            }, // 字段结束
        }
    });

}
