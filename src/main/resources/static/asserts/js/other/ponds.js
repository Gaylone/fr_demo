$(document).ready(function() {
    var checkID=0;
    var fid=null;
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

});
