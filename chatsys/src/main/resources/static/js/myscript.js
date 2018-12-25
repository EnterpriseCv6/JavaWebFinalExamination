var viewmodel = avalon.define({

    //id必须和页面上定义的ms-controller名字相同，否则无法控制页面
    $id: "viewmodel",
    datalist: {},
    apply: {},
    datalist1:{},
    text1:"请输入您想要搜索的id！",
    text2:"查询失败！",
    text3:"删除好友",
    searchFriend:function () {
        var friendid=document.getElementById("putin").value;
        if(friendid!=""){
        var userid="12345";
        var findUser = JSON.stringify({
            friendid:friendid,
            userid:userid,
        })
        $.ajax({
            type : 'POST',
            url : '/searchFriend',
            data : findUser,
            dataType : 'json',
            contentType: "application/json;charset=utf-8",
            success : function(user) {
                if(user!=""){
                viewmodel.datalist=user;
                viewmodel.text2="查询成功！";}
                else{
                    viewmodel.datalist=null;
                    viewmodel.text3="删除好友";
                    viewmodel.text2="该用户不是您的好友";}
            },
            error : function(user) {
                viewmodel.text="查询失败";
            }
        })
    }
        else{
            viewmodel.datalist=null;
            viewmodel.text3="删除好友";
            viewmodel.text2="请输入您想要搜索的好友id!";}
    },
    deleteFriend:function () {
        var friendid=document.getElementById("putin").value;
            var userid="12345";
            var findUser = JSON.stringify({
                friendid:friendid,
                userid:userid,
            })
            $.ajax({
                type : 'POST',
                url : '/deleteFriend',
                data : findUser,
                dataType : 'json',
                contentType: "application/json;charset=utf-8",
                success : function(result) {
                    viewmodel.datalist=null;
                    viewmodel.text2=null;
                    viewmodel.text3=result.result;
                },
                error : function(resultr) {
                    viewmodel.text3="删除失败";
                }
            })
        },
    searchUser:function () {
        var friendid=document.getElementById("putin").value;
        var findUser = JSON.stringify({
            friendid:friendid
        })
        $.ajax({
            type : 'POST',
            url : '/searchUser',
            data : findUser,
            dataType : 'json',
            contentType: "application/json;charset=utf-8",
            success : function(user) {
                if(user!=""){
                    viewmodel.text1="搜索成功!";
                viewmodel.datalist=user;
                viewmodel.apply="发送申请";
                }
            },
            error : function(user) {
                viewmodel.text1="搜索失败";
            }
        })
    },
    sendRequest:function () {
        var friendid=document.getElementById("putin").value;
        var userid="12345";
        var msg=document.getElementById("requestmessage").value;
        var findUser = JSON.stringify({
            friendid: friendid,
            userid: userid,
            msg: msg,
        })
        $.ajax({
            type : 'POST',
            url : '/sendRequest',
            data : findUser,
            dataType : 'json',
            contentType: "application/json;charset=utf-8",
            success : function(result) {
                viewmodel.apply=result.result;
                sendMsg(msg,3,friendid);
            },
            error : function(result) {
                viewmodel.apply=result.result;
            }
        });
    },
    getRequest:function () {
        var friendid=document.getElementById("putin").value;
        var userid="12345";
        var findUser = JSON.stringify({
            friendid: friendid,
            userid: userid
        })
        $.ajax({
            type : 'POST',
            url : '/getRequest',
            data : findUser,
            dataType : 'json',
            contentType: "application/json;charset=utf-8",
            success : function(result) {
                viewmodel.datalist1=result;
            },
            error : function(result) {
                viewmodel.text=result.result;
            }
        });
    },
    addFriend:function () {
        var friendid=document.getElementById("putin").value;
        var userid="12345";
        var friendgroup=document.getElementById("friendgroup").value;
        var findUser = JSON.stringify({
            friendid: friendid,
            userid: userid,
            friendgroup:friendgroup,
        })
        $.ajax({
            type : 'POST',
            url : '/addFriend',
            data : findUser,
            dataType : 'json',
            contentType: "application/json;charset=utf-8",
            success : function(result) {
                viewmodel.text2=result.result;
            },
            error : function(result) {
                viewmodel.text2=result.result;
            }
        });
    },
    refuseRequest:function () {
        var friendid=document.getElementById("putin").value;
        var userid="12345";
        var findUser = JSON.stringify({
            friendid: friendid,
            userid: userid,
        })
        $.ajax({
            type : 'POST',
            url : '/refuseRequest',
            data : findUser,
            dataType : 'json',
            contentType: "application/json;charset=utf-8",
            success : function(result) {
                viewmodel.text2=result.result;
            },
            error : function(result) {
                viewmodel.text2=result.result;
            }
        });
    },

});
//动态生成好友列表
function createList(list) {
    var group=new Object();
    var isE=-1;
    var j=0;
    var sul=document.getElementById('friendList');
    for(var i=0;i!=list.msg.length;i++){
        isE=isExist(group,list.msg[i].friendGroup,j);
        if(isE===-1){
            group[j]=list.msg[i].friendGroup;
            var li=document.createElement('li');
            var a1=document.createElement('a');
            var ul1=document.createElement('ul');
            //动态生成无序列表
            ul1.setAttribute('class','items');
            ul1.setAttribute('id','ul'+group[j]);
            //自动生成分组的名字超链接
            a1.setAttribute('href','#');
            a1.innerHTML=group[j];
            //将元素插入
            li.setAttribute('id','li1'+group[j]);
            li.setAttribute('class','list');
            li.appendChild(a1);
            li.appendChild(ul1);
            sul.appendChild(li);
            j++;
        }
            var li1=document.createElement("li");
            var a=document.createElement('a');
            //生成好友列表的超链接
            a.setAttribute('href','#');
            a.setAttribute('class','func');
            sessionStorage.setItem(list.msg[i].friendName,list.msg[i].friendid);
            a.innerHTML=list.msg[i].friendName;
            //生成该好友列并加入对应无序列表
            li1.setAttribute('id','li2'+list.msg[i].friendName);
            li1.setAttribute('class','list');
            li1.appendChild(a);
            var u=document.getElementById('ul'+list.msg[i].friendGroup);
            u.appendChild(li1);

    }
}
//判断当前已有分组中是否存在该分组名
function isExist(group,name,m) {
    var i=-1;

    if(m==0)
        i=-1;
    else {
        for (var j = 0; j < m; j++) {
            if (group[j] == name) {
                i = j;
            }
        }
    }
    alert(i);
    return i;
}
//发送当前聊天对象的id到后端
function talk(tarId) {
    sendMsg('','5',tarId);

}
$(function () {
    $("body").on('click','.list',function () {
        $(this).addClass("active");
    })
})
$(function () {
    $("body").on('click','.active',function () {
        $(this).removeClass("active");
    })
})/*
$(function () {
    $("body").on('click','.func',function () {
        var name=$(this).innerHTML;
        var id=sessionStorage.getItem(name);
        alert(id+" "+name);
        sendMsg("",'5',id);
    })
})*/
$(function () {
    $(".menu").on('click','.func',{foo:"文本:"},function () {
        var name=$(this).html();
        var id=sessionStorage.getItem(name);
        sendMsg("",'5',id);
    })

})

// 注册数据正确性判断
function sign() {
    var id = document.getElementById("signId").value;      // 获取表单账号
    var name = document.getElementById("signName").value;  // 获取表单用户名
    var pw1 = document.getElementById("signPW1").value;    // 获取表单密码
    var pw2 = document.getElementById("signPW2").value;    // 获取表单确认密码
    var birth = document.getElementById("signBirth").value;// 获取表单生日

    var idf = 0;     // 记录账号是否有非字母和数字
    for (var i = 0; i <= id.length; i++)
    {
        if ((id.charAt(i) >= 'a' && id.charAt(i) <= 'z')
            || (id.charAt(i) >= 'A' && id.charAt(i) <= 'Z')
            || (id.charAt(i) >= '0' && id.charAt(i) <= '9'))
        {
            idf = 1;   // 账号合法
            break;
        }

    }
    var birth = new Date(birth);

    var birthf = 1;   // 记录生日格式是否正确
    if (id.length > 15)
    {
        alert("账号不得超过15个字符"); return false;
    }

    else if (id.length == 0)
    {
        alert("账号不得为空！"); return false;
    }
    else if (idf == 0)
    {
        alert("账号只允许输入字母和数字！"); return false;
    }
    else if (name.length >= 10)
    {
        alert("用户名不得超过10个字符！"); return false;
    }
    else if (pw1.length < 6)
    {
        alert("密码不得小于6个字符！"); return false;
    }
    else if (pw1.length >= 20)
    {
        alert("密码不得大于20个字符！"); return false;
    }
    else if (pw1 != pw2)
    {
        alert("两次输入的密码不相等！"); return false;
    }
    else if (birth == "Invalid Date")
    {
        alert("请正确输入出生日期（XXXX-XX-XX）"); return false;
    }
    else
    {
        return true;
    }
}

// 登录格式判断
function login()
{
    var account = document.getElementById("loginAccount").value;
    var password = document.getElementById("loginPassword").value;

    if (account.length == 0)
    {
        alert("账号不能为空！"); return false;
    }
    else if (password.length == 0)
    {
        alert("密码不能为空！"); return false;
    }
    else if (password.length < 6)
    {
        alert("密码不能小于6位！"); return false;
    }
    else
    {
        return true;
    }
}






