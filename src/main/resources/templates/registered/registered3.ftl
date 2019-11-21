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
     <div class="for-liucheng">
      <div class="liulist for-cur"></div>
      <div class="liulist for-cur"></div>
      <div class="liulist for-cur"></div>
      <div class="liutextbox">
       <div class="liutext for-cur"><strong>验证手机</strong><br /><em>1</em></div>
       <div class="liutext for-cur"><strong>设置新密码</strong><br /><em>2</em></div>
       <div class="liutext"><strong>注册完成</strong><br /><em>3</em></div>
      </div>
     </div><!--for-liucheng/-->
      <form class="forget-pwd">
      <div class="tp">
      	<img src="${ctx}/static/registered/img/zhuce.png" />
      <h2 class="gx">恭喜您，注册成功！</h2>
      <h5 class="hy">欢迎加入智云大家庭</h5>
      </div>
       <div class="subtijiao"><input type="button" id="next" value="去登陆体验吧" /></div>
      </form><!--forget-pwd/-->
    <!--  <div class="successs">
       <h3>恭喜您，修改成功！</h3>
      </div>-->
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

    $("#next").click(function () {
        window.location.href = projectName+"/student/login"
    })
})
</script>
<!---->
</body>
</html>
