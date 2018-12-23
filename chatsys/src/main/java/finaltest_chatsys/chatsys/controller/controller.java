package finaltest_chatsys.chatsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {
    @RequestMapping("/test")
    public String html(){
        System.out.println("跳转");
        return "test";
    }
    @RequestMapping("/temp")
    public String temp(ModelMap modelMap){
        modelMap.put("temp","测试页面");
        return "temp";
    }
}
