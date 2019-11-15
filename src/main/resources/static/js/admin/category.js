//加载后台用户 bootstrap-table
var projectName="";

$(function () {

    //得到项目名称
    projectName=$("body").attr("data-project");

    //初始化 表格内容(远程加载后台的地址)
   initTable();

});


function initTable(){
    var $tb= $('#tb');
    $tb.bootstrapTable({
        url: projectName+'/admin/category/data',
        method: 'POST',
        contentType:"application/x-www-form-urlencoded",   //使用post你就必须要更改
        striped: true,                         //是否显示行间隔色
        sidePagination: "server",              //分页方式：client客户端分页，server服务端分页（*）
        undefinedText: '',                  //空值怎么显示
        singleSelect: true,                  // 单选checkbox，默认为复选
        idField: 'id',
        columns: [
            {
                field: 'name',
                title:'分类名称',
                valign: 'middle'
            },{
                field: 'thumb',
                title:'图片',
                formatter:imgFormatter,
                valign: 'middle'
            },{
                field: 'displayorder',
                title:'排序',
                valign: 'middle'
            },{
                field: 'enabled',
                title:'启用',
                valign: 'middle',
                formatter:enabledFormatter,
            }
        ],
        //配置treegird配置
        parentIdField:"parentid",
        treeShowField: 'name',
        //当数据加载完成的时候，进行
        //重置树形节点视图
        onResetView: function () {
            $tb.treegrid({
                initialState: 'expanded',//收缩collapsed,展开expanded
                treeColumn: 0,//指明第几列数据改为树形
               /* expanderExpandedClass: 'glyphicon glyphicon-tag',
                expanderCollapsedClass: 'glyphicon glyphicon-book',*/
                onChange: function () {
                    var datas = $tb.bootstrapTable('getData');
                    console.log("datas:"+datas);
                    $tb.bootstrapTable('resetWidth');
                }
            });
        }
    });
}

//类型
function imgFormatter(value, row, index) {
    if (value != '') {
        return '<img src="'+value+'"  width="80">'
    }
    else {
        return '<img src="'+projectName+'/static/img/noimg.png"  width="80">'
    }
}

function enabledFormatter(value, row, index){
    if (value == 1) {
        return '<span class="label label-success">启用</span>'
    }
    else {
        return '<span class="label label-danger">禁用</span>'
    }
}