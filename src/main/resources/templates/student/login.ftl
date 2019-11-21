<#assign ctx="${request.contextPath}">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生登录入口</title>
    <link href="${ctx }/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <!-- bootstrap-validator验证 -->
    <link href="${ctx }/static/js/plugins/validator/css/bootstrapValidator.css" rel="stylesheet">
    <!-- bootstrap-dialog 模态框框 -->
    <link href="${ctx }/static/js/plugins/dialog/css/bootstrap-dialog.css" rel="stylesheet">

    <!---->
    <link type="text/css" href="${ctx }/static/student/css/css.css" rel="stylesheet" />
    <link href="${ctx }/static/css/login.css" rel="stylesheet">
    <!--动画-->
    <link rel="stylesheet" href="${ctx }/static/student/css/animate.css" type="text/css"/>



</head>

<body data-project="${ctx}">
<!--top-->
<div class="yxq">
    <a href="../页面/index.html">
        <div class="bz">
            <img src="${ctx }/static/student/img/biaozhi.jpg"/>
        </div>
    </a>

    <ul class="nav">
        <a href="${ctx}/teacher/login">
            <li>老师入口</li>
        </a>
        <li class="na">学生入口</li>
    </ul>
</div>

<div class="content bjx">
    <div class="wow fadeInRight bjx_1" data-wow-duration="3s">
        <img src="${ctx }/static/student/img/beij.png"/>
    </div>
    <div class="web-width">
        <div class="bjt">
            <h2 class="wow fadeInLeft">来智云</h2>
            <h1 class="wow fadeInLeft" data-wow-delay="0.5s">作业提交更智能</h1>
            <p class="wow fadeInLeft" data-wow-delay="1s"></p>
            <h4 class="wow fadeInLeft" data-wow-delay="1.5s">方便学生查看作业的批改情况，以便于更好地学习方便学生和老师更好的互动式的学习与教学</h4>
        </div>

        <div class="dly">
            <form method="get" class="forget-pwd for" id="editForm" onsubmit="return false;">

                <div  class="form-group" js-tab="1" id="form-group">
                    <div class="tab-title">
                        <a href="javascript:;" class="item item-cur">密码登录</a>
                    </div>
                    <div class="tab-cont">
                        <ul class="tab-cont__wrap">
                            <li class="item">
                                <dl class="dlj">
                                    <dd>
                                        <div class="form-group">
                                            <div>
                                                <input type="text" class="form-control uname" id="account"  name="account" placeholder="用户名/手机号" autocomplete="off">
                                            </div>
                                        </div>
                                    </dd>
                                </dl>
                                <dl  class="dlj">
                                    <dd>
                                        <div class="form-group">
                                            <div>
                                                <input type="password" class="form-control pword m-b" id="password"  name="password" placeholder="密码">
                                            </div>
                                        </div>
                                    </dd>
                                </dl>
                                <input type="button" id="btnLogin" class="btn btn-success btn-block" value="登录" >
                                <div class="dll">
                                    <div class="zd"><input type="checkbox" value="dl" id="dl" checked="checked"/><label for="dl">下次自动登录</label></div>
                                    <div class="wj"><a href="#">忘记密码</a>｜<a href="${ctx}/register/index">注册</a></div>
                                </div>
                                <div class="dsf">
                                    <p>第三方账号登录</p>
                                    <ul class="ds">
                                        <a href="https://weibo.com/"><li class="d_1"></li></a>
                                        <a href="https://im.qq.com/index.shtml"><li class="d_2"></li></a>
                                        <a href="https://wx.qq.com/"><li class="d_3"></li></a>
                                    </ul>

                                </div>
                            </li>
                        </ul>
                    </div>
                </div>

            </form>
        </div>


    </div><!--web-width/-->
</div><!--content/-->
<!--bottom-->
<#include "../common/bottom2.ftl">


<script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>
<!-- bootstrap-validator验证 -->
<script src="${ctx }/static/js/plugins/validator/js/bootstrapValidator.js"></script>
<script src="${ctx }/static/js/plugins/validator/js/language/zh_CN.js"></script>
<!-- bootstrap-dialog模态框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>
<!--动画-->
<script src="${ctx }/static/student/js/wow.min.js"></script>
<script>
    if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))){
        new WOW().init();
    };
</script>
<script src="${ctx}/static/student/js/login.js?v=1.0.0"></script>

</body>
</html>
