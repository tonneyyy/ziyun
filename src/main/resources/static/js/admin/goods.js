//加载后台 bootstrap-table
var projectName="";

$(function () {

    //得到项目名称
    projectName=$("body").attr("data-project");

    //初始化 表格内容(远程加载后台的地址)
   initTable();

    /**
     * 分类变化操作(三级联动)
     */
   categoryChange();

    /**
     * 按钮初始化事件
     */
   initEvent();

});




function initTable(){
    $('#tb').bootstrapTable({
        url: projectName+'/admin/goods/data',
        method: 'POST',
        contentType:"application/x-www-form-urlencoded",   //使用post你就必须要更改
        striped: true,                         //是否显示行间隔色
        cache: false,                          //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: true,                        //是否启用排序
        sortOrder: "asc",                      //排序方式
        sidePagination: "server",              //分页方式：client客户端分页，server服务端分页（*）
        undefinedText: '',                  //空值怎么显示
        singleSelect: true,                  // 单选checkbox，默认为复选
        showRefresh   : false,                  // 显示刷新按钮
        showColumns   : true,                  // 选择显示的列
        strictSearch: true,
        clickToSelect: true,                   // 点击选中行
        pagination: true,                      //是否显示分页
        pageNumber:1,                          //初始化加载第一页，默认第一页,并记录
        pageSize:5,                            //每页显示的数量
        pageList: [5, 10, 20, 50, 100],        //设置每页显示的数量
        paginationPreText:"上一页",
        paginationNextText:"下一页",
        queryParams : function (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            params["pcate"]=$("#pcate").val();
            params["ccate"]=$("#ccate").val();
            params["tcate"]=$("#tcate").val();
            params["title"]=$("#title").val();
            params["goodssn"]=$("#goodssn").val();
            params["status"]=$("#status").val();
            params["extValue"]=$("#extValue").val();
            params["orderString"]=$("#orderString").val();
            return params;
        },
        columns: [
            {
                radio: true
            },
            {
                field: 'id',
                title:'编号',
                valign: 'middle'
            },
            {
                field: 'title',
                title:'产品名称',
                valign: 'middle'
            },{
                field: 'pcate',
                title:'所属分类',
                valign: 'middle',
                formatter:cateFormatter
            },{
                field: 'thumb',
                title:'图片',
                formatter:imgFormatter,
                valign: 'middle'
            },{
                field: 'goodssn',
                title:'货号',
                valign: 'middle'
            },{
                field: 'productprice',
                title:'销售价',
                valign: 'middle'
            },
            {
                field: 'total',
                title:'库存',
                valign: 'middle',
                formatter:totalFormatter
            },
            {
                field: 'isnew',
                title:'附加属性',
                valign: 'middle',
                formatter:extFormatter
            },{
                field: 'status',
                title:'状态',
                valign: 'middle',
                formatter:statusFormatter
            },
        ]
    });
}

//分类格式化
function cateFormatter(value,row,index){
   return row.firstCateName+"/"+row.secondCateName+"/"+row.thirdCateName;
}

//图片
function imgFormatter(value,row,index){
  return  "<img  src='"+value+"'  width='50'  >";
}


//格式化列
function stateFormatter(value,row,index){
    //
    if(value==1){
      return "<span class=\"label label-primary\">启用</span>";
    }
    return "<span class=\"label label-danger\">禁用</span>";
}

//库存提示
function totalFormatter(value,row,index){
    if(value<=5){
        return "<span class=\"label label-danger\">"+value+"</span>";
    }
    return value;
}

//附加属性
function extFormatter(value,row,index){
    let  extValue='';

    if(row.isnew===1){
        extValue+="<span class=\"label label-primary\">新品</span>";
    }
    if(row.issendfree===1){
        extValue+="<span class=\"label label-default\">免邮</span>";
    }

    if(row.ishot===1){
        extValue+="<span class=\"label label-danger\">热卖</span>";
    }
    if(row.isdiscount===1){
        extValue+="<span class=\"label label-warning\">折扣</span>";
    }
    if(row.isrecommand===1){
        extValue+="<span class=\"label label-info\">推荐</span>";
    }
    return extValue;
}

function statusFormatter(value,row,index){
   if(value===1){
       return "<span class=\"label label-primary\">上架</span>"
   }

   return "<span class=\"label label-default\">下架</span>";
}

//三级联动
function categoryChange(){

    //一级分类发生变化
    $("#pcate").change(function(){

        //清空 三级分类，保留 请选择
        let threeCate=$("#tcate");
        threeCate.find("option:gt(0)").remove();

        //清空二级分类,保留请选择
        let twoCate=$("#ccate");
        twoCate.find("option:gt(0)").remove();

        //一级分类选择中的值
        var parentid=$(this).val();
        if(parentid>0){
            //读取二级分类
            $.ajax({
                type: "GET",
                url: projectName+"/admin/category/"+parentid+"/data",
                dataType:"json",
                success: function(resp){
                     if(resp.code===0){
                         let dataList=resp.result;

                         for(let i=0;i<dataList.length;i++){
                             let data=dataList[i];
                             let str=`<option value="${data.id}">${data.name}</option>`;
                             //追加到列表中去
                             twoCate.append(str);
                         }

                     }else{

                         alert("解析后台数据失败...");

                     }
                }
            });

        }

    });

    //二级分类发生变化
    $("#ccate").change(function(){
        //清空 三级分类，保留 请选择
        let threeCate=$("#tcate");
        threeCate.find("option:gt(0)").remove();

        //一级分类选择中的值
        var parentid=$(this).val();
        if(parentid>0) {
            //读取三级分类
            $.ajax({
                type: "GET",
                url: projectName + "/admin/category/" + parentid + "/data",
                dataType: "json",
                success: function (resp) {
                    if (resp.code === 0) {
                        let dataList = resp.result;

                        for (let i = 0; i < dataList.length; i++) {
                            let data = dataList[i];
                            let str = `<option value="${data.id}">${data.name}</option>`;
                            //追加到列表中去
                            threeCate.append(str);
                        }

                    } else {

                        alert("解析后台数据失败...");

                    }
                }
            });
        }

    });

}

//页面按钮事件
function initEvent(){
     //点击查询按钮(重新查询)
    $("#btnSearch").click(function(){
        //从第1页开始查询
        $("#tb").bootstrapTable('refresh',{query:{offset:0}});

    });

}