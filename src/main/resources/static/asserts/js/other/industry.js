var IID=null;
$(document).ready(function() {
    $("#addIndustry").click(function (){
        IID=null;
        $.ajax({
            url:"industry/getAllFreePonds",
            method:"post",
            dataType: "json",
            success:function (data){
                $("#productPanel").html("")
                if (data.length==0){
                    alert("抱歉，当前没有闲置的池塘可供生产")
                    $("#addIndustry1").modal('hide')
                    location.reload();
                }else {
                    $("#productPanel").append("\t<div class=\"form-group\">\n" +
                        "<label>池塘</label>\n" +
                        "<select class=\"form-control\" name=\"ponds\" id=\"ponds\">\n" +
                        "</select>\n" +
                        "</div>")
                    for (var i=0 ;i<data.length;i++){
                        $("#ponds").append("<option value=\""+data[i].pid+"\">"+data[i].pName+"</option>")
                    }
                    //先有闲置的鱼塘再谈生产任务
                    $.ajax({
                        url:"industry/getAllProduct",
                        method:"post",
                        dataType:"json",

                        success:function (data){
                            for (var i=0;i<data.length;i++){
                                $("#productPanel").append(
                                    "<div class=\"form-group\">\n" +
                                    "    <label >"+data[i].aType+"</label>\n" +
                                    "    <input type=\"text\" class=\"form-control\" name=\""+data[i].aType+"\" placeholder=\"鱼苗数量\">\n" +
                                    "  </div>"
                                )
                            }
                            $("#productPanel").append("<div class=\"form-group\">\n" +
                                "<label>预成本(元)</label>\n" +
                                "<input type=\"text\" class=\"form-control\" id=\"pre_cost\" name=\"pre_cost\" placeholder=\"预先成本\">\n" +
                                "</div>")
                        }
                    })

                }
            }
        })
    })

$("#submitIndustry").click(function (){
    var data =JSON.stringify($("#productPanel").serializeArray())
    $.ajax({
        url:"industry/addIndustry",
        data:data,
        method:"POST",
        contentType: "application/json;charset=UTF-8",
        success:function (result){
            if (result=="success"){
                alert("成功为该池塘创建养殖任务！")
                $("#addIndustry1").modal('hide')
                location.reload();
            }else {
                alert("创建养殖任务出错。请查看日志。")
                $("#addIndustry1").modal('hide')
                location.reload();
            }
        }
    })
})


$("#saveDetail").click(function (){
    var data = $("#detailPanel").serializeArray()
    $.ajax({
        url:"industry/updateIndustry/"+IID,
        method: "post",
        data:JSON.stringify(data),
        contentType: "application/json;charset=UTF-8",
        success:function (result){
            if(result=="error"){
                alert("更新养殖任务内容失败")
                $('#myModal').modal("hide");
                location.reload()
            }
            if (result=="issues"){
                alert("更新养殖任务内容有点问题，可能没有完全更新")
                $('#myModal').modal("hide");
                location.reload()
            }
            if (result=="success"){
                $.ajax({
                    url:"industry/getIndustryByID",
                    data:JSON.stringify({"iid":IID}),
                    method:"post",
                    contentType: "application/json;charset=UTF-8",
                    dataType:"json",
                    success:function (result){
                        if (result!=null){
                            $("#costMessage").text( "原有成本是"+result[0].total_cost+"仅要求输入增加的成本，如果没有请填0")
                        }
                    }
                })
                $('#myModal').modal("hide");
                $("#myModal2").modal('show')
            }


        }
    })

})


    $("#saveIndustry").click(function (){
        var data = {"iid":IID,"total_cost":$("#total_cost").val(),"harvestDate":$("#harvestDate").val()}
        $.ajax({
            url:"industry/updateIndustry",
            data:JSON.stringify(data),
            method:"post",
            contentType: "application/json;charset=UTF-8",
            success:function (result){
                if (result=="success"){
                    $("#myModal2").modal('hide')
                    alert("成功完成养殖任务！")
                    IID=null;
                    location.reload()
                }
            }
        })
        IID=null;
    })
})
function gettd(iid){
    IID=null;
    var data={"iid":iid}
    $("#detailPanel").html("");
    $.ajax({
        url:"industry/detail",
        data:JSON.stringify(data),
        method:"post",
        contentType: "application/json;charset=UTF-8",
        success:function (result){
            var data =JSON.parse(result)
            for (var i=0;i<data.length;i++){
                $("#detailPanel").append(
                    "<div class=\"form-group\">\n" +
                    "<label>"+data[i].aType+"</label>\n" +
                    "<input type=\"text\" class=\"form-control\" name=\""+data[i].aType+"\" placeholder=\"收成重量(斤)\">\n" +
                    "<label>采购单价(元/斤)</label>\n" +
                    "<input type=\"text\" class=\"form-control\" name=\""+data[i].aType+"单价\" placeholder=\"单价(元/斤)\">\n" +
                    "</div>"
                )
            }
            IID=iid;
            $('#myModal').modal("show")
        }
    })

}
function  showDetail(iid){
    $.ajax({
        url:"industry/detail/"+iid,
        method:"post",
        dataType:"json",
        success:function (result){
            clearData();
            if (result!=null){
                $("#pName").text(result.pName)
                $("#fName").text(result.fName)
                $("#totalCost").text(result.totalCost+"元")
                $("#totalHarvest").text(result.totalHarvest+"元")
                $("#profit").text(result.totalHarvest-result.totalCost+"元")
                $("#harvestDatetime").text(result.harvestDatetime)
                for (var i=0;i<result.industryDetailPOJOList.length;i++){
                    $("#industry_detail_list").append("<tr>" +
                        "<td>"+result.industryDetailPOJOList[i].aType+"</td>" +
                        "<td>"+result.industryDetailPOJOList[i].pre_num+"</td>" +
                        "<td>"+result.industryDetailPOJOList[i].weight+"</td>" +
                        "<td>"+result.industryDetailPOJOList[i].unit_price+"</td>" +
                        "<td>"+result.industryDetailPOJOList[i].weight * result.industryDetailPOJOList[i].unit_price+"</td>" +
                        "</tr>")
                }
                if(result.totalHarvest-result.totalCost<0){
                    $("#profit").css("color","red")
                }else {
                    $("#profit").css("color","rgb(46,169,223)")
                }
            }
            $('#detailModal').modal("show")
        }
    })

    function clearData(){
        $("#industry_detail_list").html("")
        $("#pName").text("")
        $("#fName").text("")
        $("#totalCost").text("")
        $("#totalHarvest").text("")
        $("#profit").text("")
        $("#harvestDatetime").text("")
    }
}
