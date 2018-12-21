package finaltest_chatsys.chatsys;

import finaltest_chatsys.chatsys.controller.DownloadServlet;
import finaltest_chatsys.chatsys.controller.WebSocketEndPoint;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@SpringBootApplication
@ComponentScan("finaltest_chatsys.chatsys.controller")
@ComponentScan("finaltest_chatsys.chatsys.service")
@EntityScan("finaltest_chatsys.chatsys.entity")
public class ChatsysApplication {


    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext=SpringApplication.run(ChatsysApplication.class, args);
        WebSocketEndPoint.serApplicationContext(applicationContext);
    }

    @Bean
    public ServletRegistrationBean getServletRegistrationBean(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new DownloadServlet());

        bean.addUrlMappings("/download");

        return bean;

    }



}

