$(document).ready(function() {
    var checkName=0;
    $("#dName").blur(function(){
        var data={"dName":$("#dName").val()}
        if($("#dName").val()==null||$("#dName").val()==""){
            var checkName=0;
            $("#dNameMassage").text("渔病名称不得为空");
        }else {
            $.ajax({
                url:"diseases/getDiseaseByName",
                data:JSON.stringify(data),
                contentType: "application/json;charset=UTF-8",
                method:"post",
                success:function (data){
                    if (data!="0"){
                        $("#dNameMassage").text("该渔病已存在");
                        checkName=0;
                    }else {
                        checkName=1;
                        $("#dNameMassage").text("");
                    }
                }
            })
        }

    })
    $("#saveDisease").click(function () {
        if(checkName==1){
            var data ={"dName":$("#dName").val(),"memo":$("#memo").val()}
            $.ajax({
                url:"diseases/saveDisease",
                method: "post",
                data:JSON.stringify(data),
                contentType: "application/json;charset=UTF-8",
                success:function (result){
                    if (result=="success"){
                        $("#myModal").modal("hide")
                        alert("新增渔病成功！")
                        location.reload()
                    }else {
                        alert("！新增渔病失败！")
                        location.reload()
                    }
                }
            })
        }
    })
})
