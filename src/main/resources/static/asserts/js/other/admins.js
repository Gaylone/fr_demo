$(document).ready(function() {

$("#confirmChangeP").click(function (){
    var v1 = $("#newPass1").val();
    var v2 = $("#newPass2").val();
    if (v1==v2){
        $.ajax({
            url:"",
            data:v1,
            dataType:"text",
            success:function (result){
                if (result=="succeeded"){
                    alert("密码修改成功")
                }else {
                    alert("修改失败")
                }
            },
            error:function (result){

            }
        })
    }else {
        alert("两次密码不一致")
    }
})
});