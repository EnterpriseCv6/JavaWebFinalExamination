CREATE DATABASE chatSys;
USE chatSys;
#设置了序号自增的表，在插入会自增的值时请将插入值设置为0，他会自动按照当前的最大序号自增1
#用户表
CREATE TABLE userTable(
userid VARCHAR(15) PRIMARY KEY,
upassword VARCHAR(20),
userstatus INT
);
#用户信息表`userinfo``usertable`
CREATE TABLE userInfo(
userid VARCHAR(15) PRIMARY KEY,
username VARCHAR(10),
usersign VARCHAR(100),	#用户签名
ctime DATE,	#账号创建时间
birth DATE,
address VARCHAR(100),
FOREIGN KEY userInfo(userid) REFERENCES userTable(userid) ON UPDATE CASCADE
   ON DELETE RESTRICT
);
#聊天信息
CREATE TABLE chatInfo(
msgId INT AUTO_INCREMENT PRIMARY KEY, #消息的序号
sendid VARCHAR(15),	#发送人id
receiveid VARCHAR(15),	#接收人id
chattime VARCHAR(30),	#聊天时间
message VARCHAR(100),	#消息
FOREIGN KEY chatInfoSend(sendid) REFERENCES userTable(userid) ON UPDATE CASCADE
   ON DELETE RESTRICT,
   FOREIGN KEY chatInfoReceive(receiveid) REFERENCES userTable(userid)
);
#未处理的消息（当接收方未上线时暂存于此，上线后获取值并删除）
CREATE TABLE unprocessedMessage(
messageId INT AUTO_INCREMENT PRIMARY KEY,	#消息的序号
sendid VARCHAR(15),
receiveid VARCHAR(15),
stime VARCHAR(30),
message VARCHAR(100),
FOREIGN KEY unprocessedMessageSend(sendid) REFERENCES userTable(userid) ON UPDATE CASCADE
   ON DELETE RESTRICT,
   FOREIGN KEY unprocessedMessageReceive(receiveid) REFERENCES userTable(userid)
);
#好友表
CREATE TABLE friendInfo(
userid VARCHAR(15) PRIMARY KEY,	#用户id
friendid VARCHAR(15),	#好友id
friendName VARCHAR(10) REFERENCES userInfo(username), #好友名
friendgroup VARCHAR(10), #好友所在分组
FOREIGN KEY friendInfo(userid) REFERENCES userTable(userid) ON UPDATE CASCADE
   ON DELETE RESTRICT,
   FOREIGN KEY friendInfoFriend(friendid) REFERENCES userTable(userid)
)
#未处理请求表
CREATE TABLE unprocessedRequest(
friendMessageId INT AUTO_INCREMENT PRIMARY KEY, #消息的序号
tarid VARCHAR(15), #目标id
reqid VARCHAR(15), #请求人id
rtime VARCHAR(30), #发送的时间
reqname VARCHAR(10) REFERENCES userInfo(username),
msg VARCHAR(50),	#验证消息
reqgroup VARCHAR(10),	#请求方的好友分组，同意后要用
reqstatus INT,	#请求的状态，0为未处理，1为同意，2为拒绝
FOREIGN KEY unprocessedRequesttar(tarid) REFERENCES userTable(userid) ON UPDATE CASCADE
   ON DELETE RESTRICT,
   FOREIGN KEY unprocessedRequestreq(reqid) REFERENCES userTable(userid)
)