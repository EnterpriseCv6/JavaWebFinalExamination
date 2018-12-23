package finaltest_chatsys.chatsys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import finaltest_chatsys.chatsys.entity.Info;
import finaltest_chatsys.chatsys.service.messageGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//获取用户未处理的聊天信息
@Controller
public class messageController {
    @Autowired
    private messageGetService messageGetService;

    @RequestMapping(value="/getMessage",method = RequestMethod.POST)
    @ResponseBody
    public String getMessage(@RequestBody String jsonS){
        System.out.println("get");
        JSONObject jsonObject= JSON.parseObject(jsonS);
        String userId=(String)jsonObject.get("userId");
        String tarId=(String)jsonObject.get("tarId");
        List<Info> chatInfo=messageGetService.select(userId,tarId);
        JSONObject json=new JSONObject();
        json.put("msg",chatInfo);
        return json.toJSONString();
    }
}
