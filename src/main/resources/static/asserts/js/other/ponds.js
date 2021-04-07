var pid2 =null;
$(document).ready(function() {
    var checkID=0;
    var fid=null;
    var checkNum=false;
    //新增池塘时的校验
    $("#fIDNum").blur(function(){
        var idnum=$("#fIDNum").val()
        var data ={"IDNum":idnum}
        $.ajax({
            url:"ponds/getFarmerByIDNum",
            method:"post",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(data),
            success:function (param) {

                if (param.length>0){
                    $("#farmerInfo").css("color","bule")
                  $("#farmerInfo").text("当前身份证对应的农户为:"+param[0].fName)
                    fid=param[0].fid;
                    checkID=1;
                }else {
                    $("#farmerInfo").text("查无此人");
                    checkID=0;
                }
            }
        })
    });
    //获取池塘个数计数器
    $.ajax({
        url:"ponds/getPondsNum",
        method:"GET",
        success:function (data){
            $("#quantity").text(data)
        }
    });
    //搜索表单提交
    $("#search_submit").click(function (){
        $("#pond_search_form").submit()
    })

    //新增池塘
    $("#savePond").click(function () {
        var data={
            "pAddress":$("#paddress").val(),
            "pSize":$("#psize").val(),
            "pType":$("#ptype").val(),
            "p_fid":fid,
            "pName":$("#pname").val(),
        }
        if (checkID==1){
            $.ajax({
                url:"ponds/addPond",
                method:"post",
                contentType:"application/json",
                data: JSON.stringify(data),
                success:function (param) {
                    if (param=="0"){
                        alert("添加失败")
                    }else {
                        alert("添加成功")
                        $('#myModal').modal('hide')
                        location.reload();
                    }
                }
            })
        }
    })
    $("#new_size").onblur(function (){
        $("#psizeMessage").text("")
        if (isNumber($("#new_size").val())){
            checkNum=true;
            $("#psizeMessage").text("")
        }else {
            checkNum=false;
            $("#psizeMessage").text("只能输入数字")
        }
    })
    $("#updateInfo").click(function (){
        var data={
            "pid":pid2,
            "pName":$("#new_pname").val(),
            "p_fid":$("#new_fName").val(),
            "pType":$("#new_type").val(),
            "pSize":$("#new_size").val()
        }
        if (checkNum){
            $.ajax({
                url:"ponds/updateInfo",
                data:JSON.stringify(data),
                contentType: "application/json;charset=UTF-8",
                method:"post",
                success:function (result){
                    if (result=="1"){
                        alert("信息更改完成")
                        location.reload();
                    }else {
                        alert("信息修改不成功")
                        location.reload();
                    }
                }
            })
        }

        pid2=null;
    })
});
function filled(pid){
    alert("您确定要填埋此池塘吗？填埋后此池塘将不可使用！")
    $.ajax({
        url:"ponds/filled"+pid,
        method:"post",
        success:function (result){
            if (result=="1"){
                alert("已填埋！")
                location.reload()
            }else {
                alert("填埋失败！")
                location.reload()
            }
        }
    })
}
function changeINFO(pid){
    $("#old_pName").text("")
    $("#old_fName").text("")
    $("#old_pType").text("")
    $("#old_pSize").text("")
    $("#new_fName").html("")
    pid2=pid
    $.ajax({
        url:"ponds/getPondsByFID",
        data:JSON.stringify({"pid":pid}),
        method:"post",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success:function (result){
            $("#old_pName").text(result[0].pName)
            $("#old_fName").text(result[0].fName)
            $("#old_pSize").text(result[0].pSize)
            $("#old_pType").text(result[0].pType)
        }
    })
    $.ajax({
        url:"farmer/getAllFarmer",
        method:"post",
        dataType: "json",
        success:function (result){
            if (result.length==0){
                $("#new_fName").append("<option>抱歉，没有养殖户信息</option>")
            }else {
                for (var i=0;i<result.length;i++){
                    $("#new_fName").append("<option value=\""+result[i].fid+"\">"+result[i].fName+"</option>")
                }
            }
        }
    })
}
function isNumber(value) {         //验证是否为数字
    var patrn = /^(-)?\d+(\.\d+)?$/;
    if (patrn.exec(value) == null || value == "") {
        return false
    } else {
        return true
    }
}
