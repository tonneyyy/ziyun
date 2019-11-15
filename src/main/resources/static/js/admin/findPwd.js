var projectName;

$(function () {
    projectName=$("body").attr("data-project");

    //隐藏
    $("#verify").hide();

    initVerify();

    initValidator();

    //点击验证
    $("#btnNext").click(checkValidatorState);


});

function checkValidatorState(){

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
        $("#verify").show();

    },200);

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
            inputVal:{
                message: '输入的值无效',
                validators:{
                    notEmpty: {
                        message: '请输入邮箱'
                    }
                } // validators验证结束

            }, // roleName结束
        }
    });

}

function initVerify(){

    $('#mpanel4').slideVerify({
        type : 2,		//类型
        vOffset : 5,	//误差量，根据需求自行调整
        vSpace : 5,	//间隔
        imgName : [projectName+"/static/js/plugins/verify/images/1.jpg", projectName+"/static/js/plugins/verify/images/2.jpg"],
        imgSize : {
            width: '350px',
            height: '200px',
        },
        blockSize : {
            width: '20px',
            height: '20px',
        },
        barSize : {
            width : '350px',
            height : '20px',
        },
        ready : function() {
        },
        success : function() {
            //隐藏
            $("#verify").hide();
            sendEmail();
        },
        error : function() {
//		        	alert('验证失败！');
        }

    });
}



//重新初始化验证工具
function reloadVerify(){
    //清空里面的内容
    $("#mpanel4").empty();

    initVerify();
}

//调用后台发送邮件
function sendEmail(){
    //提交数据,模拟一个post提交
    $('#editForm :submit').removeAttr("disabled").trigger("click");
}