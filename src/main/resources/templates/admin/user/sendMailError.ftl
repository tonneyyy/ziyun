<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>找回密码-邮件发送失败</title>

    <link href="${ctx }/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">


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
            <h1>操作失败</h1>
        </div>
    </div>

    <div class="middle-box  animated fadeInDown">
            <h3 style="color:red;">${message!}</h3>

           <a href="${ctx}/admin/login">返回登录页</a>


    </div>

</div>

<!-- 全局js -->
<script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>

</body>

</html>
