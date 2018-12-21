package finaltest_chatsys.chatsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {
    @RequestMapping("/test")
    public String html(){
        System.out.println("跳转");
        return "test";
    }
}
