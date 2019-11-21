<#assign ctx="${request.contextPath}">

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>

<!--上传图片代码-->
<link rel="stylesheet" type="text/css" href="${ctx}/static/page/css/globle.css" />

<script type="text/javascript" src="${ctx}/static/page/js/jquery.min (2).js"></script>
<!---->
<!--上传封面代码-->
<link href="${ctx}/static/page/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/static/page/css/bootstrap-fileinput.css" rel="stylesheet">
<!---->
<!--动画-->
<link rel="stylesheet" href="${ctx}/static/page/css/animate.css" type="text/css"/>
<!---->

<link href="${ctx}/static/page/css/style1.css" rel="stylesheet" type="text/css"/>


</head>

<body data-project="${ctx }">
        <!--top-->

<!--上传-->
    <div class="yxq dpz">
    	<div class="wow pulse pg" data-wow-iteration="5" data-wow-duration="0.2s">上传作业</div>
        <div class="dp">
        	<div class="zy">
            	<h4 class="wow zoomInLeft">上传封面</h4>

<!--上传封面代码-->
<script src="/demos/googlegg.js"></script>
<div class="wow slideInLeft container" data-wow-delay="0.5s">
    <div class="page-header">
        <form id="uploadForm" enctype='multipart/form-data'>
            <div class="form-group">
                <div class="fileinput fileinput-new" data-provides="fileinput"  id="exampleInputUpload">
                    <div class="fileinput-new thumbnail" style="width: 200px;height: auto;max-height:150px;">
                        <img id='picImg' style="width: 100%;height: auto;max-height: 140px;" src="${ctx}/static/page/images/noimage.png" alt="" />
                    </div>
                    <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;"></div>
                    <div>
                        <span class="btn btn-primary btn-file">
                            <span class="fileinput-new">选择文件</span>
                            <span class="fileinput-exists">换一张</span>
                            <input type="file" name="pic1" id="picID" accept="image/gif,image/jpeg,image/x-png">
                        </span>
                        <a href="javascript:;" class="btn btn-warning fileinput-exists" data-dismiss="fileinput">移除</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>


<script src="${ctx}/static/page/js/jquery.min.js"></script>
<script src="${ctx}/static/page/js/bootstrap-fileinput.js"></script>
<script type="text/javascript">
    $(function () {
        //比较简洁，细节可自行完善
        $('#uploadSubmit').click(function () {
            var data = new FormData($('#uploadForm')[0]);
            $.ajax({
                url: 'xxx/xxx',
                type: 'POST',
                data: data,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    console.log(data);
                    if(data.status){
                        console.log('upload success');
                    }else{
                        console.log(data.message);
                    }
                },
                error: function (data) {
                    console.log(data.status);
                }
            });
        });

    })
</script>
<!---->
            	
                
                <h4 class="wow zoomInLeft" data-wow-delay="1s">上传图片</h4>
                <h5 class="wow slideInLeft" data-wow-delay="1.5s">注：图片不超过100张，支持批量上传。支持jpg/gif/png格式
rgb模式，不超过10m。</h5>
<!--上传图片代码-->
<script src="/demos/googlegg.js"></script>

<div class="wow slideInLeft" data-wow-delay="2s">
    <ul class="wow slideInLeft upload-ul clearfix">
        <li class="upload-pick">
            <div class="webuploader-container clearfix" id="goodsUpload"></div>
        </li>
    </ul>
</div>

<script src="${ctx}/static/page/js/webuploader.min.js"></script>
<script src="${ctx}/static/page/js/diyUpload.js"></script>
<script>
$(function(){

	//上传图片
	var $tgaUpload = $('#goodsUpload').diyUpload({
		url:'uploadFilePath',
		success:function( data ) { },
		error:function( err ) { },
		buttonText : '',
		accept: {
			title: "Images",
			extensions: 'gif,jpg,jpeg,bmp,png'
		},
		thumb:{
			width:120,
			height:90,
			quality:100,
			allowMagnify:true,
			crop:true,
			type:"image/jpeg"
		}
	});


});
</script>
<!---->
            </div>

            <div class="pf">
            <form id="upload">
                <div class="wow bounceInRight zpxx" data-wow-delay="1s">
                	<h5>作品信息</h5>
                    <div class="mc"><input type="text" name="title" placeholder="请输入作品名称"/>
                    <span>限50字以下</span>
                    </div>
                    <div class="sm">
                    	<textarea class="sm_1" name="description" placeholder="请输入作品说明"></textarea>
                        <span>限200字以下</span>
                    </div>
                
                </div>
                <div class="wow bounceInRight zpxx zplb" data-wow-delay="1.5s">
                	<h5>作品类别</h5>

              <div class="bq1">
                    	<span>标签：</span>
                  <#list data01 as a>
                    	<input type="radio" value="${a.id}" id="${a.name}" name="label" class="zpin" /><label for="yc">${a.name}</label>
                  </#list>
                    </div>
                    <div class="nr">
                    	<span>内容：</span>
                        <#list data02 as b>
                        <input type="checkbox" value="${b.id}" id="${b.name}" name="content"/><label for="pm">${b.name}</label>
                        </#list>
                    </div>
                    <div class="lx1">
                    	<span> 类型：</span>
                    <#list data03 as c>
                        <input type="radio" value="${c.id}" id="${c.name}" name="types" /><label for="gr">${c.name}</label>
                    </#list>
                    </div>
                </div>
                <div class="wow bounceInDown qued" data-wow-delay="2.5s">
                    <button type="button" id="makeSure">确定</button>
                    <button class="fh_1" id="back">返回</button>
    			</div>
                </form>
            </div>
        </div>
    </div>
    
    
<!--bottom.ftl-->

<!--动画-->    
        <script src="${ctx}/static/page/js/wow.min.js"></script>
        <script src="${ctx}/static/page/js/Upload.js"></script>
<script>
if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))){
	new WOW().init();
};
</script>
<!---->
</body>
</html>
