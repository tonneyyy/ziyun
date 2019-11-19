<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>老师管理</title>

    <meta name="keywords" content="">
    <meta name="description" content="">


    <link href="${ctx }/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">

    <!--日期插件-->
    <link href="${ctx}/static/css/plugins/datepicker/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <!-- bootstrap-table -->
    <link href="${ctx }/static/js/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">

    <!-- bootstrap-validator验证 -->
    <link href="${ctx }/static/js/plugins/validator/css/bootstrapValidator.css" rel="stylesheet">

    <!-- bootstrap-dialog 模态框框 -->
    <link href="${ctx }/static/js/plugins/dialog/css/bootstrap-dialog.css" rel="stylesheet">

    <link href="${ctx }/static/css/style.css?v=4.1.0" rel="stylesheet">


    <link href="${ctx }/static/js/plugins/bootstrap-select-1.13.0-dev/dist/css/bootstrap-select.css" rel="stylesheet">



</head>


<body   data-project="${ctx }" >



<div class="wrapper wrapper-content animated fadeInRight">

    <!-- 查询条件 -->
    <div class="row">
        <div class="col-md-12">

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">操作</h3>
                </div>

                <div class="panel-body">
                    <form class="form-inline">



                        <button type="button" class="btn btn-primary" id="btnAdd">新增</button>
                        <button type="button" class="btn btn-primary" id="btnEdit">编辑</button>

                    </form>
                </div>
            </div>

        </div>
    </div>

    <!-- 显示表格 bootstrap-table需要写的html -->
    <div class="row">
        <div class="col-md-12">

            <table id="tb">
            </table>

        </div>
    </div>



    <!-- 模态框 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">标题</h4>
                </div>
                <div class="modal-body">
                    <form id="editForm"  class="form-horizontal" >

                        <div class="form-group">
                            <label  class="col-sm-2 control-label">您的名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_name" name="name"  data-bv-trigger="blur"   placeholder="名称">
                            </div>
                        </div>

                        <div class="form-group" id="pwd">
                            <label  class="col-sm-2 control-label">您的密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="edit_password" name="password"  data-bv-trigger="blur"   placeholder="密码 ">
                            </div>
                        </div>


                        <div class="form-group">
                            <label  class="col-sm-2 control-label">专业</label>
                            <div class="col-sm-10">
                                <select class="selectpicker" id="edit_teachknowledge" name="knowledge"  multiple data-live-search="true">
                                    <option value="0">请选择</option>
                                    <option value="1">1.java</option>
                                    <option value="2">2.UI</option>

                                </select>


                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">您的电话</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="edit_mobile" name="mobile" data-bv-trigger="blur" placeholder="电话">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-10">
                                <select  class="form-control"id="edit_sex" name="sex" >
                                    <option value="0">请选择</option>
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                        </div>

                            <div class="form-group">
                                <div class="input-group date form_date" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                                    <div class="input-group-addon">出生日期</div>
                                    <input class="form-control" size="16" type="text" id="edit_birthday" name="birthday" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                <input type="hidden" id="dtp_input2"/>
                            </div>

                        <div class="form-group">
                            <label  class="col-sm-2 control-label">学历</label>
                            <div class="col-sm-10">
                                <select  class="form-control" id="edit_education" name="education">
                                    <option value="0">请选择</option>
                                    <option value="1">本科</option>
                                    <option value="2">专科</option>
                                    <option value="3">专科以下</option>
                                    <option value="4">研究生</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label  class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="edit_state" name="state">
                                    <option value="0">请选择</option>
                                    <option value="1">正常</option>
                                    <option value="2">休假</option>
                                    <option value="3">离职</option>
                                </select>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="btnSave">保存</button>
                </div>
            </div>
        </div>
    </div>


</div>


<!-- 全局js -->
<script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>

<!-- bootstrap-table -->
<script src="${ctx }/static/js/plugins/bootstrap-table/bootstrap-table.js?v=1.14.2"></script>
<script src="${ctx }/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js?v=1.14.2"></script>


<!-- Peity -->
<script src="${ctx }/static/js/plugins/peity/jquery.peity.min.js"></script>

<!-- bootstrap-validator验证 -->
<script src="${ctx }/static/js/plugins/validator/js/bootstrapValidator.js"></script>
<script src="${ctx }/static/js/plugins/validator/js/language/zh_CN.js"></script>
<!-- 日历插件 -->
<script src="${ctx }/static/js/plugins/datepicker/bootstrap-datetimepicker.js"></script>
<script src="${ctx }/static/js/plugins/datepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<!-- bootstrap-dialog模态框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>
<!-- 自定义js -->
<script src="${ctx }/static/js/content.js?v=1.0.0"></script>

<script src="${ctx }/static/js/plugins/bootstrap-select-1.13.0-dev/dist/js/bootstrap-select.min.js"></script>






<!-- 自定义 role.js -->
<script src="${ctx }/static/js/admin/teacher.js?v=1.0.0"></script>

</body>

</html>

