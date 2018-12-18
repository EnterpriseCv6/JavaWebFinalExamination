package finaltest_chatsys.chatsys.controller;

import com.alibaba.fastjson.JSONObject;
import finaltest_chatsys.chatsys.service.messageGetService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "chatMsgServlet")
public class chatMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String userId=request.getParameter("userId");
        String tarId=request.getParameter("tarId");
        messageGetService messageGetService=new messageGetService();
        JSONObject json=new JSONObject();
        json.put("msg",messageGetService.select(userId,tarId));
        messageGetService.delete(userId,tarId);
        out.print(json.toJSONString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
