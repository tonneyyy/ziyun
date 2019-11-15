<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> 更改密码</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link href="${ctx }/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">

    <!-- bootstrap-validator验证 -->
    <link href="${ctx }/static/js/plugins/validator/css/bootstrapValidator.css" rel="stylesheet">

    <!-- bootstrap-dialog 模态框框 -->
    <link href="${ctx }/static/js/plugins/dialog/css/bootstrap-dialog.css" rel="stylesheet">


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
            <h1>更改密码</h1>
        </div>
    </div>

    <div class="middle-box  animated fadeInDown">

            <form id="editForm" role="form" action="login.html">
                <input type="hidden" id="token"  name="token" value="${token}">

                <div class="form-group">
                    <h3>用户:${user.username}</h3>
                </div>


                <div class="form-group">
                    <input id="password" name="password" type="password" class="form-control" placeholder="请输入请密码">
                </div>

                <div class="form-group">
                    <input id="passwordConfirm" name="passwordConfirm" type="password" class="form-control" placeholder="请再一次输入密码">
                </div>

                <button type="button" class="btn btn-danger block full-width m-b" id="btnSave">更改密码</button>



            </form>
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


<script src="${ctx }/static/js/admin/changePwd.js"></script>
</body>

</html>
