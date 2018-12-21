package finaltest_chatsys.chatsys.controller;

import finaltest_chatsys.chatsys.entity.chatMessage;
import finaltest_chatsys.chatsys.service.messageGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@WebServlet(name = "DownloadServlet",urlPatterns = "/download")
public class DownloadServlet extends HttpServlet {
    @Autowired
    private messageGetService messageGetService;
    @Override
    public  void init(ServletConfig config) throws ServletException{
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId=request.getParameter("userId");
        String tarId=request.getParameter("tarId");
        String fileName=userId+"&"+tarId+".txt";
        List<chatMessage> rs=messageGetService.selectChatMessage(userId,tarId);
        File file=new File(fileName);
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream out=new FileOutputStream(file);
            PrintStream p=new PrintStream(out);
            for(chatMessage message:rs){
                p.println(message.getTime());
                p.println(message.getSendId()+":"+message.getMessage());
            }
            p.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        FileInputStream in=new FileInputStream(file);
        OutputStream outputStream=response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            outputStream.write(buffer, 0, len);
        }
        in.close();
        outputStream.close();
    }
}
