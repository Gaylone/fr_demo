var checkIDNUM=false;
var checkName=false;
var checkAddress=false;
var FID=null;
function changeInfo(fid){
    $("#oldNameMessage").text("")
    $.ajax({
        url:"farmer/getFarmer/"+fid,
        method:"post",
        dataType: "json",
        success:function (result){
            $("#oldNameMessage").text(result[0].fName)
            FID=fid;
        }
    })
}
$(document).ready(function() {
    $("#updateInfo").click(function (){
        if (FID!=null){
            if($("#new_fName").val()==''&&$("#new_fAddress").val()==''&&$("#new_fPhone").val()==''){
                alert("请填写修改的信息，否则请关闭")
                return false
            }else {
                var data = {
                    "fid":FID,
                    "fName":$("#new_fName").val(),
                    "fAddress":$("#new_fAddress").val(),
                    "fPhone":$("#new_fPhone").val()
                }
                $.ajax({
                    url:"farmer/updateInfo",
                    data:JSON.stringify(data),
                    contentType: "application/json;charset=UTF-8",
                    method:"post",
                    success:function (result) {
                        if (result=='1'){
                            alert("信息更新完成")
                            location.reload()
                        }else {
                            alert("信息更新失败")
                            location.reload()
                        }
                    }
                })
            }


            FID=null
        }


    })

    $("#fidnum").blur(function(){
        var idnum=$("#fidnum").val()
        var data ={"IDNum":idnum}
        if (idnum==null||idnum==""){
            checkIDNUM=false;
            $("#IDMessage").text("身份证号不能为空!");
        }else {
            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            if(!reg.test(idnum)) {
                $("#IDMessage").text("身份证号格式不合法!");
                checkIDNUM=false;
            }else {
                $.ajax({
                    url:"farmer/queryFarmerIDNum",
                    method:"post",
                    dataType: "json",
                    contentType: "application/json;charset=UTF-8",
                    data:JSON.stringify(data),
                    success:function (data) {
                        if (data == "-1"){
                            $("#IDMessage").text("该身份证号已存在!");
                            checkIDNUM=false;
                        }else {
                            $("#IDMessage").text("");
                            checkIDNUM=true;
                        }
                    }
                })
            }
        }

    });
    $("#fname").blur(function(){
        if ($("#fname").val()==null||$("#fname").val()==""){
            checkName=false;
            $("#NameMessage").text("姓名不能为空!");
        }else {
            checkName=true;
            $("#NameMessage").text("");
        }
    })
    $("#faddress").blur(function(){
        if ($("#faddress").val()==null||$("#fname").val()==""){
            checkAddress=false;
            $("#AddressMessage").text("住址不能为空!");
        }else {
            checkAddress=true;
            $("#AddressMessage").text("");
        }
    })

$("#saveFarmer").click(function (){
    var fname=$("#fname").val()
    var fsex=$("input[name='sex']:checked").val()
    var faddress=$("#faddress").val()
    var fphone=$("#fphone").val()
    var fidnum =$("#fidnum").val()
    var fbirth= $("#fbirth").val()

    if (checkAddress==true&&checkName==true&&checkIDNUM==true){
        $.ajax({
            url:"farmer/saveFarmer",
            method:"post",
            contentType:"application/json",
            data: JSON.stringify({
                "fName":fname,
                "fSex":fsex,
                "fIdNum":fidnum,
                "fBirthday":fbirth,
                "fAddress":faddress,
                "fPhone":fphone
            }),
            success:function (data){
                if (data=="1"){
                    alert("添加成功！")
                    $('#myModal').modal('hide')
                    location.reload();
                }else {
                    if (data=="-1"){
                        alert("数据不完整！")
                    }
                    alert("添加失败！")
                }
            }
        })
    }

})
});
function freeze(fid){
    alert("是否确定转业该养殖户")
    $.ajax({
        url:"farmer/freeze/"+fid,
        method:"post",
        success:function (result){
            if (result=='1'){
                alert("转业成功")
                location.reload()
            }else {
                alert("转业失败")
                location.reload()
            }
        }
    })
}
function active(fid){
    $.ajax({
        url:"farmer/active/"+fid,
        method:"post",
        success:function (result){
            if (result=='1'){
                alert("从业成功")
                location.reload()
            }else {
                alert("从业失败")
                location.reload()
            }
        }
    })

}
