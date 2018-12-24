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
    },
    getFriendList:function () {
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
    }
});
function createList(list) {
    var group=new Object();
    var isE=0;
    var j=0;
    var ul=document.getElementById('friendList');
    for(var i=0;i<=list.length;i++){
        isE=isExist(group,list.msg[i].group);
        if(isE==0){
            group[j]=list.msg[i].group;
            var li=document.createElement('li');
            li.setAttribute('id','li1'+j);
            li.setAttribute('value',group[j]);
            j++;
        }
        //动态添加li
    }
}
function isExist(group,name) {
    var i=0;
    if(group==null)
        i=0;
    else{
        for(var j=0;j<group.length;j++){
            if(group[j]==name)
            {
                i=j;

            }
        }
    }
    return i;
}