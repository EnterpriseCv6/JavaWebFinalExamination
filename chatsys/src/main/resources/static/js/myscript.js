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