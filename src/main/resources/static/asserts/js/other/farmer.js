var checkIDNUM=false;
var checkName=false;
var checkAddress=false;

$(document).ready(function() {

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
