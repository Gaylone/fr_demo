$(document).ready(function() {

});
function freeze(tid){
    $.ajax({
        data:JSON.stringify({"tid":tid}),
        url:"techMan/freeze",
        method:"post",
        contentType: "application/json;charset=UTF-8",
        success:function (result){
            if (result=="1"){
                alert("冻结成功")
                location.reload()
            }else {
                alert("操作失败")
                location.reload()
            }
        }
    })
}
function active(tid){
    $.ajax({
        data:JSON.stringify({"tid":tid}),
        url:"techMan/active",
        method:"post",
        contentType: "application/json;charset=UTF-8",
        success:function (result){
            if (result=="1"){
                alert("恢复成功")
                location.reload()
            }else {
                alert("操作失败")
                location.reload()
            }
        }
    })
}
