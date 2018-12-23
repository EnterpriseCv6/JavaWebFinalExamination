package finaltest_chatsys.chatsys.controller;


import finaltest_chatsys.chatsys.entity.User;
import finaltest_chatsys.chatsys.service.UserService;
import finaltest_chatsys.chatsys.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String addUser(@RequestParam("") String userid, @RequestParam("") String upassword, @RequestParam("") String username,
                          @RequestParam("") String usersign, @RequestParam("") Date ctime,
                          @RequestParam("") Date birth, String address, @RequestParam("") int userstatus)
    {
        try
        {
            service.addUser(userid, upassword, username, usersign, ctime, birth, address, userstatus);
            return "";
        }
        catch (Exception e)
        {
            return "联系人已经存在!";
        }
    }

    // 修改密码
    @PostMapping(value = "updatepassword")
    public void updatePassword(String newPassword, String userid)
    {
        service.updatePassword(newPassword, userid);
    }
}
