<#assign ctx="${request.contextPath}">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生登录入口</title>
    <!--动画-->
    <link rel="stylesheet" href="${ctx }/static/student/css/animate.css" type="text/css"/>
    <!---->
    <link type="text/css" href="${ctx }/static/student/css/css.css" rel="stylesheet" />
</head>

<body>
<!--top-->
<div class="yxq">
    <a href="../页面/index.html">
        <div class="bz">
            <img src="${ctx }/static/student/img/biaozhi.jpg"/>
        </div>
    </a>

    <ul class="nav">
        <a href="../内页/wode_dpg_ls.html">
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
            <h3 class="wow fadeInLeft" data-wow-delay="1.5s">方便学生查看作业的批改情况，以便于更好地学习方便学生和老师更好的互动式的学习与教学</h3>
        </div>



        <div class="dly">
            <form method="get" class="forget-pwd for">
                <div class="tab" js-tab="1">
                    <div class="tab-title">
                        <a href="javascript:;" class="item item-cur">密码登录</a>

                    </div>
                    <div class="tab-cont">
                        <ul class="tab-cont__wrap">

                            <li class="item">
                                <dl class="dlj">
                                    <dd><input type="text" placeholder="账号 / 手机号 / 邮箱"/></dd>
                                    <div class="clears"></div>
                                </dl>
                                <dl  class="dlj">
                                    <dd><input type="password" placeholder="请输入正确的密码" /></dd>
                                    <div class="clears"></div>
                                </dl>
                                <a href="../页面/index -已登录.html"><div class="subtijiao jj">登录</div></a>
                                <div class="dll">
                                    <div class="zd"><input type="checkbox" value="dl" id="dl" checked="checked"/><label for="dl">下次自动登录</label></div>
                                    <div class="wj"><a href="#">忘记密码</a>｜<a href="index.html">注册</a></div>
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
<div class="bottom">
    <ul class="tm">
        <li>智云</li>
        <li>帮助中心</li>
        <li>学习中心</li>
        <li class="yq">友情链接</li>
    </ul>
    <h6>DT人才培训基地（重庆中心）&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;晋ICP备16009028号&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;咨询热线：400-7777-699&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;地址：重庆渝中区文化宫6F&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;版权所有：华信智原</h6>
</div>



<script src="${ctx }/static/student/js/jquery-1.8.3.min.js"></script>

<!--动画-->
<script src="${ctx }/static/student/js/wow.min.js"></script>
<script>
    if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))){
        new WOW().init();
    };
</script>

</body>
</html>
