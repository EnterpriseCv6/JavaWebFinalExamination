function send() {
    var user = ["bj.jpg"];
    var num = 1;//判断左右
    var portrait_position = 0;
    var now = -1;//左右浮动
    var send_txt = document.getElementById('send_txt');
    var chat_ul = document.getElementById('chat_ul');
    var chat_span = chat_ul.getElementsByTagName('span');
    var chat_img = chat_ul.getElementsByTagName('img');
   send_btn.onclick = function () {
        if (send_txt.value == '') {
            alert("请不要惜字如金");
        } else {
            chat_ul.innerHTML += '<li><img src="images/six.jpg"><span>' + send_txt.value + '</span></li>';
            now++;
            if (num==0) {
                chat_span[now].className = 'spanleft';
                chat_img[now].className = 'imgleft';
            }
             else {
			 
				chat_span[now].className = 'spanright';
				 chat_img[now].className = 'imgright';
               
            }
            send_txt.value = '';
            // 内容过多时,将滚动条放置到最底端
            /*contentcontent.scrollTop = content.scrollHeight;*/
            sendMsg(send_txt);
        }
		}
}
Array.prototype.contains = function (element) {
for (var i = 0; i < this.length; i++) {
if (this[i] == element) {
return true;
}
}
return false;
}
window.onload = function() {
  var oDiv1 = document.getElementById('name1');
var oDiv2 = document.getElementById('div2');
document.onclick = function(e) {
if( !oDiv1.contains(e.target)) {
oDiv2.style.display = "none";
}
}
oDiv1.onmouseover = function() {
oDiv2.style.display = "block";
}
};
function ajax(userId) {
    var xmlHttp=false;
    var info;
    if(window.XMLHttpRequest){
        xmlHttp=new XMLHttpRequest();
    }else if(window.ActiveXObject){
        try{
            xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
        }catch(e){
            try{
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
            }catch (e) {
                window.alert("该浏览器不支持AJAX");

            }
        }
    }
    var method="POST";
    var url=window.location.host+"/friendInfoRequest/"+userId;
    xmlHttp.open(method,url,true);
    xmlHttp.onreadystatechange=function () {
        if(xmlHttp.readyState==4)
            info=xmlHttp.responseText;
    }
    xmlHttp.send();
    if(info!=null)
        return info;
}
function isExist (group,friendList,Fcount) {
    var isExist=-1;
    for(var i=0;i!=group.length();i++){
        if(group[i]==friendList[Fcount].group)
        {
            isExist=i;
            break;
        }
    }
    return isExist;
}
function sendMsg(message){
    var id=sessionStorage.getItem('chatId');
    var json={id:id,content:message,time:new Date().toLocaleString(),type:'1'};
    websocket.send(JSON.stringify(json));
}
function heart() {
    var json={conent:"",type:6,id:"",time:""};
    websocket.send(JSON.stringify(json));
}
function closeWebSocket(){
    websocket.close();
}
function setMessageInnerHTML(message){

}