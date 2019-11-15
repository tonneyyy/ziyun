<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 注册</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link href="${ctx }/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">

    <!-- bootstrap-validator验证 -->
    <link href="${ctx }/static/js/plugins/validator/css/bootstrapValidator.css" rel="stylesheet">

    <!-- bootstrap-dialog 模态框框 -->
    <link href="${ctx }/static/js/plugins/dialog/css/bootstrap-dialog.css" rel="stylesheet">

    <!--  滑动验证 -->
    <link href="${ctx }/static/js/plugins/verify/css/verify.css" rel="stylesheet">

    <link href="${ctx }/static/css/style.css?v=4.1.0" rel="stylesheet">

    <script>
        if(window.top !== window.self){
            window.top.location = window.location;
        }
    </script>

</head>

<body data-project="${ctx }" >

<div class="container-fluid">

    <div class="forget_header">
        <div class="wrapper">
            <h1>找回密码</h1>
        </div>
    </div>

    <div class="middle-box  animated fadeInDown">

            <form id="editForm" method="post" role="form" action="${ctx}/admin/sendEmail">
                <div class="form-group">
                    <input id="inputVal" name="inputVal" type="email" class="form-control" placeholder="请输入邮箱" required="">
                </div>

                <button type="button" class="btn btn-danger block full-width m-b" id="btnNext">发送邮件</button>

                <p class="text-muted text-center"><small>已经有账户了？</small>
                    <a href="${ctx}/admin/login">点此登录</a>
                </p>

            </form>
    </div>

    <div id="verify"  class="alert alert-success alert-dismissible  findPwdVerify" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>

        <div id="mpanel4">
        </div>

    </div>

</div>



<!-- 全局js -->
<script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>

<!-- bootstrap-validator验证 -->
<script src="${ctx }/static/js/plugins/validator/js/bootstrapValidator.js"></script>
<script src="${ctx }/static/js/plugins/validator/js/language/zh_CN.js"></script>

<!-- bootstrap-dialog模态框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>

<!-- 滑动验证-->
<script src="${ctx }/static/js/plugins/verify/js/verify.js"></script>

<script src="${ctx }/static/js/admin/findPwd.js"></script>
</body>

</html>
