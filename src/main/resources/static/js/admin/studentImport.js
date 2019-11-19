var projectName="";

$(function () {
    //得到项目名称
    projectName=$("body").attr("data-project");


    $("#excel_major").on("change",function () {
        let majorId =$(this).val();
        //获取班级列表元素
        let classes=$("#excel_classes");
        //清空班级列表内容
        classes.find("option:gt(0)").remove();
        ajaxLoadClass(majorId,classes);

    });

});


//ajax获取班级请求
function ajaxLoadClass(majorId,classes,classesId) {
    if(majorId>0){
        $.ajax({
            type: "GET",
            url: projectName+"/admin/classes/"+majorId+"/data",
            dataType:"json",
            success: function(resp){
                if(resp.code===0){
                    let dataList=resp.result;
                    for(let i=0;i<dataList.length;i++){
                        //根据下标取出每个班级
                        let data=dataList[i];
                        let str=``;
                        if(classesId==data.id){
                            str =`<option value="${data.id}" selected>${data.name}</option>`;
                        }else {
                            str=`<option value="${data.id}">${data.name}</option>`;
                        }

                        //追加到列表中去
                        classes.append(str);
                    }
                }else{
                    BootstrapDialog.show({
                        title: '提示',
                        type: BootstrapDialog.TYPE_DANGER,
                        message: "数据解析失败"
                    });
                }
            }
        });

    }
}