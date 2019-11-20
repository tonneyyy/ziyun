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

    <!--日期插件-->
    <link href="${ctx}/static/css/plugins/datepicker/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <!-- bootstrap-table -->
    <link href="${ctx}/static/js/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
    <!-- bootstrap-validator验证 -->
    <link href="${ctx}/static/js/plugins/validator/css/bootstrapValidator.css" rel="stylesheet">
    <!-- bootstrap-dialog 模态框框 -->
    <link href="${ctx}/static/js/plugins/dialog/css/bootstrap-dialog.css" rel="stylesheet">
    <!-- kineditor -->
    <link href="${ctx}/static/js/plugins/kindeditor/themes/default/default.css" rel="stylesheet">

    <link href="${ctx}/static/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body data-project="${ctx}" >

<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-md-12">

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">操作</h3>
                </div>

                <div class="panel-body">
                    <form class="form-inline" id="searchForm">
                        <div class="panel">
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon">专业</div>
                                    <select class="form-control" name="majorId" id="search_major" data-bv-trigger="blur">
                                        <option value="0" selected>请选择...</option>
                                        <#list majorList as m >
                                            <option value="${m.id}">${m.name}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon">班级</div>
                                    <select class="form-control" name="classesId" id="search_classes" data-bv-trigger="blur">
                                        <option value="0" selected>请选择...</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon">性别</div>
                                    <select  class="form-control" id="search_sex" name="sex">
                                        <option value="">请选择</option>
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon">就读状态</div>
                                    <select  class="form-control" id="search_state" name="state">
                                        <option value="0">请选择</option>
                                        <option value="1">在学</option>
                                        <option value="2">休学</option>
                                        <option value="3">退学</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">学号</div>
                                <input type="text" class="form-control" id="search_id" name="id"  placeholder="请输入学号..."  data-bv-trigger="blur" >
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">姓名</div>
                                <input type="text" class="form-control" id="search_name" name="name"  placeholder="请输入姓名..."  data-bv-trigger="blur" >
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">手机号码</div>
                                <input type="text" class="form-control" id="search_mobile" name="mobile"  placeholder="请输入手机号码..."  data-bv-trigger="blur" >
                            </div>
                        </div>



                        <button type="button" class="btn btn-info" id="btnSelect" style="margin-left: 10px">查询</button>
                        <button type="button" class="btn btn-success" id="btnAdd" style="margin-left: 10px">新增</button>
                        <button type="button" class="btn btn-primary" id="btnEdit" style="margin-left: 10px">编辑</button>
                        <button type="button" class="btn btn-danger" id="btnEdit" style="margin-left: 10px">删除</button>


                        <a  class="btn btn-info" href="${ctx}/static/excel/student_import.xlsx" style="margin-left: 10px">下载模板</a>
                        <a   class="btn btn-success"  href="${ctx}/admin/student/import" style="margin-left: 10px">导入学生信息</a>
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
                            <div class="input-group">
                                <div class="input-group-addon">选择专业</div>
                                <select class="form-control" name="majorId" id="edit_major" data-bv-trigger="blur">
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
                                <select class="form-control" name="classesId" id="edit_classes" data-bv-trigger="blur">
                                    <option value="0" selected>请选择...</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">姓名</div>
                                <input type="text" class="form-control" id="edit_name" name="name"  placeholder="请输入姓名..."  data-bv-trigger="blur" >
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">头像地址</div>
                                <input type="text" class="form-control" id="edit_portrait" name="portrait"  placeholder="请输入头像地址..."   >
                                <div class="input-group-btn">
                                    <button type="button"  id="btnChoose" class="btn btn-primary">选择头像</button>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">手机号码</div>
                                <input type="text" class="form-control" id="edit_mobile" name="mobile"  placeholder="请输入手机号码..."  data-bv-trigger="blur" >
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group date form_date" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
                                <div class="input-group-addon">入学时间</div>
                                <input class="form-control" size="16" type="text"id="edit_joinDate" name="joinDate" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            <input type="hidden" id="dtp_input1"/>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">性别</div>
                                <select  class="form-control" id="edit_sex" name="sex">
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group date form_date" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                                <div class="input-group-addon">出生日期</div>
                                <input class="form-control" size="16" type="text"id="edit_birthday" name="birthday" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            <input type="hidden" id="dtp_input2"/>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">选择学历</div>
                                <select  class="form-control" id="edit_education" name="education">
                                    <option value="1">本科</option>
                                    <option value="2">大专</option>
                                    <option value="3">大专以下</option>
                                    <option value="4">研究生</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">学校名称</div>
                                <input type="text" class="form-control" id="edit_school" name="school"  placeholder="请输入学校名称..."  data-bv-trigger="blur" >
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">所学专业</div>
                                <input type="text" class="form-control" id="edit_collegeMajor" name="collegeMajor"  placeholder="请输入所学专业..."  data-bv-trigger="blur" >
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">身份证号码</div>
                                <input type="text" class="form-control" id="edit_idCard" name="idCard"  placeholder="请输入身份证号码..."  data-bv-trigger="blur" >
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">QQ号码</div>
                                <input type="text" class="form-control" id="edit_qq" name="qq"  placeholder="请输入QQ号码..."  data-bv-trigger="blur" >
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">家庭地址</div>
                                <input type="text" class="form-control" id="edit_homeAddress" name="homeAddress"  placeholder="请输入家庭地址..."  data-bv-trigger="blur" >
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div  class="input-group-addon">现住地址</div>
                                <input type="text" class="form-control" id="edit_currentAddress" name="currentAddress"  placeholder="请输入现住地址..."  data-bv-trigger="blur" >
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">就读状态</div>
                                <select  class="form-control" id="edit_state" name="state">
                                    <option value="1">在学</option>
                                    <option value="2">休学</option>
                                    <option value="3">退学</option>
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

<!-- 日历插件 -->
<script src="${ctx }/static/js/plugins/datepicker/bootstrap-datetimepicker.js"></script>
<script src="${ctx }/static/js/plugins/datepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<!-- bootstrap-validator验证 -->
<script src="${ctx }/static/js/plugins/validator/js/bootstrapValidator.js"></script>
<script src="${ctx }/static/js/plugins/validator/js/language/zh_CN.js"></script>

<!-- bootstrap-dialog模态框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>

<!-- kineditor -->
<script src="${ctx }/static/js/plugins/kindeditor/kindeditor-all.js"></script>
<script src="${ctx }/static/js/plugins/kindeditor/lang/zh-CN.js"></script>

<!-- 自定义js -->
<script src="${ctx }/static/js/content.js?v=1.0.0"></script>

<script src="${ctx }/static/js/admin/student.js?v=1.0.0"></script>

</body>

</html>

