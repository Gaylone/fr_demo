$(document).ready(function() {
    var checkProduct=0;
    $("#atype").blur(function(){
        var data={"aType":$("#atype").val()}
        $.ajax({
            url:"products/queryProductByConditions",
            data:JSON.stringify(data),
            contentType: "application/json;charset=UTF-8",
            method:"post",
            success:function (data){
                if (data!="0"){
                    $("#productMessage").text("该渔产分类已存在");
                    checkProduct=0;
                }else {
                    checkProduct=1;
                    $("#productMessage").text("");
                }
            }
        })
    })
    $("#saveProduct").click(function (){
        if (checkProduct==1){
            var data={"aType":$("#atype").val()}
            $.ajax({
                url:"products/saveProduct",
                method: "post",
                contentType: "application/json;charset=UTF-8",
                data:JSON.stringify(data),
                success:function (data){
                    if (data=="1"){
                        alert("渔产添加成功！")
                        $('#myModal').modal('hide')
                        location.reload();
                    }else {
                        alert("添加失败！")
                    }
                }
            })
        }
    })
})
