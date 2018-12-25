package finaltest_chatsys.chatsys.controller;


import finaltest_chatsys.chatsys.entity.MyFriend;
import finaltest_chatsys.chatsys.entity.UnprocessedMessage;
import finaltest_chatsys.chatsys.entity.UnprocessedRequest;
import finaltest_chatsys.chatsys.entity.User;
import finaltest_chatsys.chatsys.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller

@RequestMapping(value = "user")
public class UserController
{
    @Autowired
    private UserServiceImpl service;


    // 增加用户
    // 对应表单id
    @PostMapping(value = "add")
    public String addUser(@RequestParam("signId") String userid, @RequestParam("signPW") String upassword,
                          @RequestParam("signName") String username,
                          @RequestParam("signBirth") Date birth)
    {
        try
        {
            String address="";
            String usersign="";
            long time = System.currentTimeMillis();
            Date date = new Date(time);
            service.addUser(userid, upassword, username, usersign, date, birth, address, 1);
            return "redirect:/test";
        }
        catch (Exception e)
        {
            return "联系人已经存在！";
        }
    }

    // 登录成功
    @PostMapping(value = "login")
    public @ResponseBody  List<List> login(String userid)
    {

        // 修改userstatus
        service.login(userid);

        // 获取为处理信息
        List<UnprocessedMessage> UnpMsg = service.findUnpMesssage(userid);

        // 获取未处理请求
        List<UnprocessedRequest> UnpReq = service.countUnpRequest(userid);

        // 获取好友
        List<MyFriend> myFriends = service.findFriends(userid);

        // 将三个list放入一个list中
        List<List> list = new ArrayList();
        list.add(UnpMsg);
        list.add(UnpReq);
        list.add(myFriends);
        return list;
    }

    // 退出
    @PostMapping(value = "logoff")
    public void logoff(String userid)
    {
        // 修改userstatus
        service.logoff(userid);
    }

    // 修改密码
    @PostMapping(value = "updatePassword")
    public void updatePassword(String newPassword, String userid)
    {
        service.updatePassword(newPassword, userid);
    }

    // 获取个人信息
    @PostMapping(value = "queryUserInformation")
    public User queryUserInformation(String userid)
    {
        return service.queryUserInformation(userid);
    }

    // 更新个人信息
    @PostMapping(value = "updateInformation")
    public void updateInformation(String newName, String newSign, Date newBirth, String newAddress, String userid)
    {

        service.updateInformation(newName, newSign, newBirth, newAddress, userid);
    }
}
