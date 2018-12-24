package finaltest_chatsys.chatsys.controller;


import finaltest_chatsys.chatsys.entity.User;
import finaltest_chatsys.chatsys.service.UserService;
import finaltest_chatsys.chatsys.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;

@RestController
@RequestMapping(value = "user")
public class UserController
{
    @Autowired
    private UserServiceImpl service;


    // 增加用户
    // 对应表单id
    @PostMapping(value = "add")
    public String addUser(@RequestParam("") String userid, @RequestParam("") String upassword,
                          @RequestParam("") String username, @RequestParam("") String usersign,
                          @RequestParam("") Date birth, String address)
    {
        try
        {
            long time = System.currentTimeMillis();
            Date date = new Date(time);
            service.addUser(userid, upassword, username, usersign, date, birth, address, 1);
            return "";
        }
        catch (Exception e)
        {
            return "联系人已经存在!";
        }
    }

    // 登录
    @PostMapping(value = "login")
    public void login(String userid)
    {

        // 修改userstatus
        service.login(userid);
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

    @RequestMapping(value = "queryUserInformation",method = RequestMethod.GET)
    public User queryUserInformation(String userid)
    {

        return service.queryUserInformation(userid);
    }

    @PostMapping(value = "updateInformation")
    public void updateInformation(String newName, String newSign, Date newBirth, String newAddress, String userid)
    {
        service.updateInformation(newName, newSign, newBirth, newAddress, userid);
    }
}
