var hasDisease=false;
var hasPonds=false;
var hasFarmer=false;
var hasSolution=false;
var hasContent=false;
var hasLoss =false;
var hasTitle =false;
$(document).ready(function() {

    $.ajax({
        url:"diseases/getAllDisease",
        method:"post",
        dataType:"json",
        success:function (result){
            $("#disease").html(" <option id=\"disease_opt\">请选择一种疾病</option>")
            if (result.length==0){
                $("#disease").html(" <option id=\"disease_opt\">当前没有疾病登记在案</option>")
            }else {
                for (var i = 0;i<result.length;i++){
                    $("#disease").append(" <option value=\""+result[i].did+"\">"+result[i].dName+"</option>")
                }
                hasDisease=true
            }

        }
    })
    $("#disease").change(function (){
        $("#disease_opt").remove()
        hasDisease=true;
    })
    $("#ponds").change(function (){
        $("#ponds_opt").remove()
        hasPonds=true;
    })
    $.ajax({
        url:"farmer/getAllFarmer",
        dataType:"json",
        success:function (result){
            $("#farmer").html(" <option id=\"farmer_opt\">请选择一个农户</option>")
            if (result.length==0){
                hasFarmer=false
                $("#farmer").html(" <option id=\"farmer_opt\">抱歉，当前没有农户登记在册</option>")
            }else {
                for (var i = 0;i<result.length;i++){
                    $("#farmer").append(" <option value=\""+result[i].fid+"\">"+result[i].fName+"</option>")
                }
                hasFarmer=true
            }

        }
    })
    $("#farmer").change(function(){
        var p_fid=$("#farmer").val();
        $("#farmer_opt").remove()
        $.ajax({
            url:"ponds/getPondsByFID",
            data:JSON.stringify({"p_fid":p_fid}),
            method:"post",
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            success:function (result){
                $("#ponds").html("<option id=\"ponds_opt\">请选择名下池塘</option>")
                if (result.length==0){
                    hasPonds=false
                    $("#ponds").html("<option id=\"ponds_opt\">该农户名下没有池塘</option>")
                }else {
                    for (var i = 0;i<result.length;i++){
                        $("#ponds").append(" <option value=\""+result[i].pid+"\">"+result[i].pName+"</option>")
                    }
                    hasPonds=true
                }

            }
        })
    })

    $("#solution").blur(function (){
        if ($("#solution").val()==null||$("#solution").val()==""){
            $("#solutionMsg").text("解决方案不得为空")
            hasSolution=false;
        }else {
            $("#solutionMsg").text("")
            hasSolution=true;
        }
    })
    check()
    $("#saveCase").click(function (){
        if(hasDisease==true&&hasPonds==true &&hasLoss==true&&hasFarmer==true
            &&hasSolution==true &&hasTitle==true&& editor.txt.html()!=null&&
            editor.txt.html()!=""){
            $.ajax({
                url:"case/saveCase",
                method:"post",
                data:JSON.stringify({
                    "c_did":$("#disease").val(),
                    "c_pid":$("#ponds").val(),
                    "solution":$("#solution").val(),
                    "content":editor.txt.html(),
                    "title":$("#title").val(),
                    "loss":$("#loss").val()
                }),
                contentType: "application/json;charset=UTF-8",
                success:function (result){
                    if (result=="success"){
                        alert("成功新建病历！")
                        location.reload()
                    }else {
                        alert("新建病历失败！")
                        location.reload()
                    }
                }
            })
        }else {
            alert("数据不完整！")
        }
    })

})
function check(){


    $("#loss").blur(function (){
        if ($("#loss").val()==null||$("#loss").val()==""){
            hasLoss=false;
            $("#lossMsg").text("损失不得为空，无损失填0")

        }else {
            if(!isNumber($("#loss").val())){
                $("#lossMsg").text("必须是数字")
                hasLoss=false;
            }else{
                $("#lossMsg").text("")
                hasLoss=true;
            }
        }
    })


    $("#title").blur(function (){
        if ($("#title").val()==null||$("#title").val()==""){
            hasTitle=false;
            $("#titleMsg").text("病历标题不得为空")
        }else {
            $("#titleMsg").text("")
            hasTitle=true;
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
function showDetail(cid){
    $("#titleLab").text("");$("#farmerLab").text("");$("#FarmerPhoneLab").text("")
    $("#pondLab").text("");$("#pondAddressLab").text("");$("#diseaseLab").text("");$("#content").html("")
    $("#lossLab").text("");$("#solutionLab").text("");$("#techManLab").text("");$("#techManPhoneLab").text("")
    $.ajax({
        url:"case/getCaseByCID/"+cid,
        dataType:"json",
        method:"post",
        success:function (data){
            if (data!=null){
                $("#titleLab").text(data.title)
                $("#farmerLab").text(data.fName)
                $("#FarmerPhoneLab").text(data.fPhone)
                $("#pondLab").text(data.pName)
                $("#pondAddressLab").text(data.pAddress)
                $("#diseaseLab").text(data.dName)
                $("#content").html(data.content)
                $("#lossLab").text(data.loss)
                $("#solutionLab").text(data.solution)
                $("#techManLab").text(data.tName)
                $("#techManPhoneLab").text(data.tPhone)
                $("#detail").modal("show")
            }else {
                alert("查看病历详情出错")
                location.reload()
            }
        }
    })
}
