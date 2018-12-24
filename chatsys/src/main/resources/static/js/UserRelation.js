/*var viewmodel = avalon.define({
    //id必须和页面上定义的ms-controller名字相同，否则无法控制页面
    $id: "viewmodel",
    textone:"搜索",
    datalist: {},
    request:function () {
        var friendid=document.getElementById("putin").value;
        var findUser = JSON.stringify({
            friendid:friendid
        })
        $.ajax({
            type : 'POST',
            url : '/searchFriend',
            data : findUser,
            dataType : 'json',
            contentType: "application/json;charset=utf-8",
            success : function(user) {
                viewmodel.datalist=user;
                viewmodel.text="搜索成功";
            },
            error : function(user) {
                viewmodel.text="搜索失败";
            }
        });
    },
});