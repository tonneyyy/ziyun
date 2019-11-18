<#assign ctx="${request.contextPath}">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>商品管理系统</title>

    <meta name="keywords" content="">
    <meta name="description" content="">


    <link href="${ctx }/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/static/css/animate.css" rel="stylesheet">

    <!-- jquery-treegrid.css-->
    <link href="${ctx }/static/js/plugins/jquery-treegrid/css/jquery.treegrid.css" rel="stylesheet">
    <!-- bootstrap-table -->
    <link href="${ctx }/static/js/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">

    <!-- bootstrap-validator验证 -->
    <link href="${ctx }/static/js/plugins/validator/css/bootstrapValidator.css" rel="stylesheet">

    <!-- bootstrap-dialog 模态框框 -->
    <link href="${ctx }/static/js/plugins/dialog/css/bootstrap-dialog.css" rel="stylesheet">

    <link href="${ctx }/static/css/style.css?v=4.1.0" rel="stylesheet">

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
                        <button type="button" class="btn btn-primary" id="btnEdit">修改</button>
                    </form>
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
                                        <div class="input-group-addon">选择菜单目录</div>
                                        <select class="form-control" name="parentId" id="edit_parentId">
                                            <option value="0">创建菜单目录</option>
                                            <#list menuList as m>
                                                <option value="${m.id}">${m.menuName}</option>
                                            </#list>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">选择按钮菜单</div>
                                        <select class="form-control" name="towMenu" id="edit_towMenu">
                                            <option value="0">请选择</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">菜单名字</div>
                                        <input type="text" class="form-control" name="menuName" id="edit_menuName"
                                               placeholder="请输入菜单名称..." data-bv-trigger="blur">
                                    </div>
                                </div>




                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">菜单URL</div>
                                        <input type="text" class="form-control" id="edit_actionName" name="actionName" placeholder="请输入菜单名字..."  data-bv-trigger="blur">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <div  class="input-group-addon">权限标识</div>
                                        <input type="text" class="form-control" id="edit_authority" name="authority"  placeholder="请输入菜单URL..."  data-bv-trigger="blur" >
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">选择菜单类型</div>
                                        <select class="form-control" name="menuLevel" id="edit_menuLevel">
                                            <option value="1">目录</option>
                                            <option value="2">菜单</option>
                                            <option value="3">按钮</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">菜单排序</div>
                                        <input type="text" class="form-control" name="sort" id="edit_sort"
                                               placeholder="请输入排序数字..." data-bv-trigger="blur">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">状态</div>
                                        <select class="form-control" name="state" id="edit_state">
                                            <option value="1">可见</option>
                                            <option value="2">隐藏</option>
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
    </div>

    <!-- 显示表格 bootstrap-table需要写的html -->
    <div class="row">
        <div class="col-md-12">

            <table id="tb">
            </table>

        </div>
    </div>




</div>


<!-- 全局js -->
<script src="${ctx }/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/static/js/bootstrap.min.js?v=3.3.6"></script>


<!-- bootstrap-table -->
<script src="${ctx }/static/js/plugins/bootstrap-table/bootstrap-table.js?v=1.14.2"></script>
<script src="${ctx }/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js?v=1.14.2"></script>

<!-- jquery-treegrid.js-->
<script src="${ctx }/static/js/plugins/jquery-treegrid/js/jquery.treegrid.js?v=1.14.2"></script>
<script src="${ctx }/static/js/plugins/jquery-treegrid/js/jquery.treegrid.bootstrap3.js?v=1.14.2"></script>
<!-- bootstrap-table/treegrid扩展 -->
<script src="${ctx }/static/js/plugins/bootstrap-table/extensions/treegrid/bootstrap-table-treegrid.js?v=1.14.2"></script>


<!-- Peity -->
<script src="${ctx }/static/js/plugins/peity/jquery.peity.min.js"></script>

<!-- bootstrap-validator验证 -->
<script src="${ctx }/static/js/plugins/validator/js/bootstrapValidator.js"></script>
<script src="${ctx }/static/js/plugins/validator/js/language/zh_CN.js"></script>

<!-- bootstrap-dialog模态框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>
<!-- 自定义js -->
<script src="${ctx }/static/js/content.js?v=1.0.0"></script>

<!-- 自定义 role.js -->
<script src="${ctx }/static/js/admin/menu.js?v=1.0.0"></script>

</body>

</html>

