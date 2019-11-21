<#assign ctx="${request.contextPath}">

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Author" contect="http://www.webqin.net">
<title>忘记密码</title>
<!--动画-->

<link rel="stylesheet" href="${ctx}/static/registered/css/animate.css" type="text/css"/>
<!---->
<link rel="shortcut icon" href="${ctx}/static/registered/images/favicon.ico" />

<link type="text/css" href="${ctx}/static/registered/css/css.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/static/registered/js/jquery-1.8.3-min.js"></script>
</head>

<body data-project="${ctx}">
<!--top-->
<#include "../common/top2.ftl">


<!---->

  <div class="content">
  <div class="wow fadeInRight bjtx" data-wow-duration="3s">
  	<img src="${ctx}/static/registered/img/beij.png"/>
  </div>
   <div class="web-width">
     <div class="for-liucheng">
      <div class="liulist for-cur"></div>
      <div class="liulist for-cur"></div>
      <div class="liulist"></div>
      <div class="liutextbox">
       <div class="liutext for-cur"><strong>验证手机</strong><br /><em>1</em></div>
       <div class="liutext for-cur"><strong>设置新密码</strong><br /><em>2</em></div>
       <div class="liutext"><strong>注册完成</strong><br /><em>3</em></div>
      </div>
     </div><!--for-liucheng/-->
     <form action="${ctx}/templates/registered/registered3.ftl" id="forget-pwd" class="forget-pwd">
       <dl>
        <dt>用户名：</dt>
        <dd><input type="text" placeholder="账号 / 手机号 "/></dd>
        <div class="clears"></div>
       </dl> 
       <dl>
        <dt>新密码：</dt>
        <dd><input type="password" placeholder="请设置密码6-20位" /></dd>
        <div class="clears"></div>
       </dl> 
       <dl>
        <dt>确认密码：</dt>
        <dd><input type="password" /></dd>
        <div class="clears"></div>
       </dl> 
       <div class="subtijiao"><input type="button" id="next" value="提交" /></div>
      </form><!--forget-pwd/-->
      <form class="forget-pwd fh_1">
       <div class="subtijiao"><input type="button" id="pre" value="返回上一步" /></div>
      </form>
   </div><!--web-width/-->
  </div><!--content/-->
<!--bottom-->

<!---->
   <!--动画-->    
    <script src="${ctx}/static/registered/js/wow.min.js"></script>
<script>
if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))){
	new WOW().init();
};
$(function () {

    projectName=$("body").attr("data-project");

    $("#pre").click(function () {
        window.location.href = projectName+"/register/index"
    })
    $("#next").click(function () {
        window.location.href = projectName+"/register/stepThree"
    })
})
</script>
<!---->
</body>
</html>
