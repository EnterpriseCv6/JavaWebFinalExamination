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
            chat_ul.innerHTML += '<li><img src="C:/Users/Administrator/Desktop/six.jpg"><span>' + send_txt.value + '</span></li>';
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