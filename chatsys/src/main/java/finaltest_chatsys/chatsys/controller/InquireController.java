package finaltest_chatsys.chatsys.controller;

import finaltest_chatsys.chatsys.entity.Inquire;
import finaltest_chatsys.chatsys.service.InquireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class InquireController {
    @Autowired
    private InquireService inquireService;
    @RequestMapping(value = "/search/{InquireId}",method = RequestMethod.GET)
    @ResponseBody
    public Inquire selectUser(@PathVariable int InquireId){
        return inquireService.searchUser(InquireId);   }
}
