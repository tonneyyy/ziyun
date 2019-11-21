var projectName="";

$(function () {

    projectName=$("body").attr("data-project");
    console.log(projectName);

    initValidator();

    $("#btnLogin").click(loginMethod);

});

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
            account:{
                message: '输入的值无效',
                validators:{
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 30,
                        trim: 'trim',
                        message: '用户名长度必须在2到30个字符之间'
                    }

                } // validators验证结束

            }, // roleName结束
            password:{
                message: '输入的值无效',
                validators:{
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 20,
                        trim: 'trim',
                        message: '密码长度必须在4到20个字符之间'
                    }
                } // validators验证结束

            } // authority结束

        }
    });

}


function loginMethod(){

    // 触发验证操作
    // 手动调用，验证表单是否通过了
    var bsv = $('#editForm').data('bootstrapValidator');
    // 手动调用
    bsv.validate();

    setTimeout(function(){
        var result=bsv.isValid();
        // 验证成功
        if(result){
            loginExecute();
        }

    },1000);

}

function loginExecute(){

    $("#btnLogin").attr("disabled","disabled");

    //ajax发送数据库，登录判断
    //1、收集数据
    var dataStr=$("#editForm").serialize();
    console.log(dataStr);
    //2、写ajax
    $.ajax({
        type:"POST",
        url:projectName+"/teacher/loginExecute",
        data:dataStr,
        dataType:"json",
        success:function(resp){

            if(resp.code===0){
                //重定向到首页
                window.location.href=projectName+"/admin/index";
            }else{
                BootstrapDialog.alert({
                    title: '登录失败',
                    message: resp.message,
                    type: BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
                    closable: true, // <-- Default value is false
                    draggable: true, // <-- Default value is false
                    closeByBackdrop: false,
                    closeByKeyboard: false,
                    buttonLabel: '确定', // <-- Default value is 'OK',
                    callback: function(result) {

                       window.location.reload();

                    }
                });



            }
        }
    });
}




