var viewmodel = avalon.define({
    //id必须和页面上定义的ms-controller名字相同，否则无法控制页面
    $id: "viewmodel",
    datalist: {},
    text: "搜索好友",
    text1:"发送申请",
    text2:"查看请求",
    text3:"同意请求",
    request:function () {
        var friendid=document.getElementById("friendid").value;
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
    sendRequest:function () {
        var friendid=document.getElementById("friendid").value;
        var userid=document.getElementById("userid").value;
        var msg=document.getElementById("msg").value;
        var findUser = JSON.stringify({
            friendid:friendid,
            userid:userid,
            msg:msg
        })
        $.ajax({
            type : 'POST',
            url : '/sendRequest',
            data : findUser,
            dataType : 'json',
            contentType: "application/json;charset=utf-8",
            success : function(result) {
                viewmodel.text1=result.result;
            },
            error : function(result) {
                viewmodel.text1=result.result;
            }
        });
    },
    checkRequest:function () {
        var friendid=document.getElementById("friendid").value;
        var userid=document.getElementById("userid").value;
        var findUser = JSON.stringify({
            friendid:friendid,
            userid:userid
        })
        $.ajax({
            type : 'POST',
            url : '/checkRequest',
            data : findUser,
            dataType : 'json',
            contentType: "application/json;charset=utf-8",
            success : function(user) {
                viewmodel.datalist=user;
            },
            error : function(result) {
                viewmodel.text2="查看失败";
            }
        });
    },
    agreeRequest:function () {
        var friendid=document.getElementById("friendid").value;
        var userid=document.getElementById("userid").value;
        var findUser = JSON.stringify({
            friendid:friendid,
            userid:userid
        })
        $.ajax({
            type : 'POST',
            url : '/agreeRequest',
            data : findUser,
            dataType : 'json',
            contentType: "application/json;charset=utf-8",
            success : function(result) {
                viewmodel.text3="同意成功";
            },
            error : function(result) {
                viewmodel.text3="同意失败";
            }
        });
    }
});