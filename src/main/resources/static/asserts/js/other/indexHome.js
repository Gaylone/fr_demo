var FLAG=false
function showmodal(){
    $("#myModals").modal('show')
}
$(document).ready(function (){
    $.ajax({
        url:"products/countProducts",
        method:"post",
        success:function (result){
            $("#countProducts").text(result)
        }

    })
    $.ajax({
        url: "ponds/getPondsNum",
        method: "post",
        success: function (result) {
            $("#countPonds").text(result)
        }
    })
    $.ajax({
        url: "diseases/countDiseases",
        method: "post",
        success: function (result) {
            $("#countDisease").text(result)
        }
    })
    $.ajax({
        url: "case/countCase",
        method: "post",
        success: function (result) {
            $("#countCase").text(result)
        }
    })
    $.ajax({
        url: "farmer/countFarmer",
        method: "post",
        success: function (result) {
            $("#countFarmers").text(result)
        }
    })
    $("#newPass2").blur(function (){
        $("#passMessage").text("")
        if ($("#newPass1").val()==null||$("#newPass2")==null
            ||$("#newPass1")==''||$("#newPass2")==''){
            $("#passMessage").text("密码不得为空")
            FLAG=false;
        }else {
            if ($("#newPass1").val()!=$("#newPass2").val()){
                $("#passMessage").text("两次密码不一致")
                FLAG=false;
            }else {
                $("#passMessage").text("")
                FLAG=true;
            }
        }
    })
    $("#saveNewPass").click(function (){
        if (FLAG==true){
            $.ajax({
                url:"techMan/changePass",
                method:"post",
                contentType: "application/json;charset=UTF-8",
                data:JSON.stringify({"pswd":$("#newPass1").val()}),
                success:function (result){
                    if (result=='1'){
                        alert("密码修改成功，请重新登录")
                        window.location.replace("login")
                    }else {
                        alert("密码修改失败")
                    }
                }
            })
        }
    })
})
