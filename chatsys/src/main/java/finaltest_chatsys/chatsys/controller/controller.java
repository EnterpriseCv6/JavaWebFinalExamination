package finaltest_chatsys.chatsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {
    @RequestMapping("/html")
    public String html(){
        return "/test";
    }
}
