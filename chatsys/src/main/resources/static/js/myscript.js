var viewmodel = avalon.define({

    //id必须和页面上定义的ms-controller名字相同，否则无法控制页面
    $id: "viewmodel",
    datalist: {},
    text: "请求数据",

    getMessage: function () {
        var id=JSON.stringify({
            userId:'3',
            tarId:'2'
        });
        $.ajax({
            type: "post",
            url: '/getMessage/',    //向springboot请求数据的url
            data: id,
            dataType:"json",
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                var info=data;
                setMessageInnerHTML(info.msg[0].infoContent);
            }
        });
    }
 /*   getFriendList:function () {
        var uid={
            userId:'3'
        };
        $.ajax({
            type:'post',
            url:'getFriendList/',
            data:uid,
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success:function (data) {
                var list=data;
                createList(list)
            }
        })
    }*/
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
            li1.appendChild(a);
            var u=document.getElementById('ul'+list.msg[i].friendGroup);
            u.appendChild(li1);

    }
    var s=JSON.stringify({
        msg:group
    })
    sessionStorage.setItem('group',s);
    sessionStorage.setItem('groupLength',j.toString());
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
        var flag=0;
        var tarId=sessionStorage.getItem('tarId');
        var id=sessionStorage.getItem(name);
        if(id==sessionStorage.getItem('tarId')){
            flag=1;
        }
        if(flag==0) {
            sessionStorage.setItem('tarId', id);
            $('#download_a').attr('href', window.location.host + '/download?userId=' + sessionStorage.getItem('userId') + "&tarId=" + id);
            $('#download_a').attr('target', '_blank');
            if($('#chat_'+tarId).length>0)
                $('#chat_'+tarId).toggle();
            if($('#chat_'+id).length>0){
                $('#chat_'+id).toggle();
            }else{
                var u=document.createElement('ul');
                u.setAttribute('id','chat_'+id);
                u.setAttribute('class','chat_content');
                document.getElementById('chat-div').appendChild(u);
            }
            sendMsg("",'5',id);
        }
    })

})
function sendT() {
    var msg=document.getElementById("send_txt").value;
    send(0,msg);
    sendMsg(msg,'1',sessionStorage.getItem('tarId'));
}

$(function () {
    var obj=sessionStorage.getItem('group');
    var group=JSON.parse(obj).msg;
    var n=sessionStorage.getItem('groupLength');
    var s=document.getElementById('selectBox');
    for(var i=0;i<n;i++){
        var o=document.createElement('option');
        o.innerHTML=group[i];
        o.value=group[i];
        s.appendChild(o);
    }
})
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
