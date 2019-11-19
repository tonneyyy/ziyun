<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>学生管理系统</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/static/css/animate.css" rel="stylesheet">

    <!-- bootstrap-dialog 模态框框 -->
    <link href="${ctx}/static/js/plugins/dialog/css/bootstrap-dialog.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body data-project="${ctx}" >

<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">

        <div class="col-md-12">

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">批量导入学生信息</h3>
                </div>

                <div class="panel-body">
                        <div class="panel">
                            <a  class="btn btn-info" href="${ctx}/static/excel/student_import.xlsx" style="margin-left: 10px">下载模板</a>
                       </div>
                </div>
           </div>
        </div>
    </div>

    <!-- 显示表格 bootstrap-table需要写的html -->
    <div class="row">
        <div class="col-md-12">

            <form id="editFormExcel" method="post"  class="form-horizontal"  action="${ctx}/admin/student/batch"  enctype="multipart/form-data">

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">选择专业</div>
                        <select class="form-control" name="majorId" id="excel_major"  >
                            <option value="0" selected>请选择...</option>
                            <#list majorList as m >
                                <option value="${m.id}">${m.name}</option>
                            </#list>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">选择班级</div>
                        <select class="form-control" name="classesId" id="excel_classes"  >
                            <option value="0" selected>请选择...</option>
                        </select>
                    </div>
                </div>


                <div class="form-group">
                    <div class="input-group">
                        <div  class="input-group-addon">选择文件</div>
                        <input type="file" class="form-control" id="excel_attach" name="attach"  placeholder="请选择文件" >
                    </div>
                </div>

                <input type="submit" value="批量导入" class="btn btn-primary" name="btnSumit">

            </form>

        </div>
    </div>

    <div class="error">
        ${error!}
    </div>




</div>


<!-- 全局js -->
<script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>


<!-- bootstrap-dialog模态框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>
<!-- 自定义js -->
<script src="${ctx }/static/js/content.js?v=1.0.0"></script>

<script src="${ctx }/static/js/admin/studentImport.js?v=1.0.0"></script>

</body>

</html>

