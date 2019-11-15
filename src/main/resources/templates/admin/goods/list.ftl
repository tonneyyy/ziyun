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

                        <div class="form-group">
                            <label >一级分类</label>
                            <select  class="form-control" id="pcate">
                                <option value="0">所有分类</option>
                                <#list firstCat as f>
                                    <option value="${f.id}">${f.name}</option>
                                </#list>

                            </select>
                        </div>

                        <div class="form-group">
                            <label >二级分类</label>
                            <select  class="form-control" id="ccate">
                                <option value="0">请选择</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label >三级分类</label>
                            <select  class="form-control" id="tcate">
                                <option value="0">请选择</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label >名称</label>
                            <input type="text"  class="form-control" id="title">
                        </div>

                        <div class="form-group">
                            <label >货号</label>
                            <input type="text"  class="form-control" id="goodssn">
                        </div>

                        <div class="form-group">
                            <label >状态</label>
                            <select  class="form-control" id="status">
                                <option value="1" selected>上架</option>
                                <option value="2">下架</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label >附加属性</label>
                            <select  class="form-control" id="extValue">
                                <option value="0" selected>全部</option>
                                <option value="1" >新品</option>
                                <option value="2">免邮</option>
                                <option value="3">热卖</option>
                                <option value="4">折扣</option>
                                <option value="5">推荐</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label >排序方式</label>
                            <select  class="form-control" id="orderString">
                                <option value="g.createtime desc" selected>时间由近到远</option>
                                <option value="g.createtime" >时间由远到近</option>
                                <option value="g.productprice" >价格由低到高</option>
                                <option value="g.productprice desc" >价格由高到低</option>
                            </select>
                        </div>

                        <button type="button" class="btn btn-primary" id="btnSearch">查询</button>

                        <button type="button" class="btn btn-primary" id="btnAdd">新增</button>

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

<!-- bootstrap-dialog模态框 -->
<script src="${ctx }/static/js/plugins/dialog/js/bootstrap-dialog.min.js"></script>
<!-- 自定义js -->
<script src="${ctx }/static/js/content.js?v=1.0.0"></script>

<!-- 自定义 goods.js -->
<script src="${ctx }/static/js/admin/goods.js?v=1.0.0"></script>

</body>

</html>

