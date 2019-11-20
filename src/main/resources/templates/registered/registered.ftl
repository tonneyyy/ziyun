<#assign ctx="${request.contextPath}">

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jQuery按步骤找回密码验证表单代码</title>
<link rel="shortcut icon" href="${ctx}/static/registered/images/favicon.ico" />
<link type="text/css" href="${ctx}/static/registered/css/css.css" rel="stylesheet" />

<script type="text/javascript" src="${ctx}/static/registered/js/jquery-1.8.3-min.js"></script>
<script type="text/javascript">
 //导航定位
// $(function(){
//	// $(".nav li:eq(2) a:first").addClass("navCur")
//	 //验证手机 邮箱
//	 $(".selyz").change(function(){
//	   var selval=$(this).find("option:selected").val();
//		 if(selval=="0"){
//			 $(".sel-yzsj").show()
//			 $(".sel-yzyx").hide()
//			 }
//		 else if(selval=="1"){
//			 $(".sel-yzsj").hide()
//			 $(".sel-yzyx").show()
//			 }
//		 })
//	 })
</script>
<!--动画-->
<link rel="stylesheet" href="${ctx}/static/registered/css/animate.css" type="text/css"/>
<!---->

</head>

<body data-project="${ctx}">
<!--<script src="${ctx}/static/registered/demos/googlegg.js"></script>-->
 <!--top-->
<#include "../common/top2.ftl">


<!---->
  <div class="content">
  <div class="wow fadeInRight bjtx" data-wow-duration="3s">
  	<img src="${ctx}/static/registered/img/beij.png"/>
  </div>
   <div class="web-width">
     <div class="for-liucheng">
      <div class="wow fadeInRightBig liulist for-cur"></div>
      <div class="wow fadeInRightBig liulist"></div>
      <div class="wow fadeInRightBig liulist"></div>
      <div class="liutextbox">
       <div class="wow fadeInRightBig liutext for-cur"><strong>验证手机</strong><br /><em>1</em></div>
       <div class="wow fadeInRightBig liutext" data-wow-delay="0.6s"><strong>设置新密码</strong><br /><em>2</em></div>
       <div class="wow fadeInRightBig liutext" data-wow-delay="1s"><strong>注册完成</strong><br /><em>3</em></div>
      </div>
     </div><!--for-liucheng/-->
    
     <form id="mobile" class="wow zoomInLeft forget-pwd" data-wow-delay="1.2s" data-wow-duration="1.5s">
     <dl>
        <dt>验证方式：</dt>
        <dd>
         <select class="selyz">
          <option value="0">验证手机</option>
         </select>
        </dd>
        <div class="clears"></div>
       </dl>
       <dl>
        <dt>用户名：</dt>
        <dd><input type="text" placeholder="请输入2-9个汉字/4-8个字符" /></dd>
        <div class="clears"></div>
       </dl>
       <dl class="sel-yzsj">
        <dt>手机号码：</dt>
        <dd><input type="text" value="" placeholder="请输入手机"  /></dd>
        <div class="clears"></div>
       </dl>
       <dl>
        <dt>手机校验码：</dt>
        <dd class="xy"><input type="text" class="xym" /> <button class="xym_1">获取验证码</button></dd>
        <div class="clears"></div>
       </dl>
       <div class="subtijiao"><input type="button" id="next" value="下一步" /></div>
       <h5 class="zdl">我已有账号，<a href="#">再次登录</a></h5>
       <h5 class="zdl">注册遇到问题，请<a href="#">咨询在线客服</a></h5>
      </form><!--forget-pwd/-->
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

        window.location.href = projectName+"/register/stepTwo"
    })
})
</script>
<!---->

</body>
</html>
