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
        method: "get",
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
})
