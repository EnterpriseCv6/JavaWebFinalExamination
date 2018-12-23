package finaltest_chatsys.chatsys.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

@Controller
public class controller {
    @RequestMapping("/test")
    public String html(){
        System.out.println("跳转");
        return "test";
    }
    @RequestMapping(value="/images",produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public BufferedImage images() throws Exception{
        return ImageIO.read(new FileInputStream(new File("C://Users//胡虞勇//Pictures//赤诚.png")));
    }
}
