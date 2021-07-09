package cn.itsource.web.controller;

import cn.itsource.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import javafx.beans.DefaultProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@DefaultProperties(defaultFallback ="fallbackUser")	//统一降级配置
@RestController
public class OrderApi {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand
    @GetMapping("/order/{id}")
    public User getById(@PathVariable("id") Long id){
        return restTemplate.getForObject("http://user-server/user/"+id,User.class);
    }

    public User fallbackUser(){
        return new User(-1L,"","未知数据");
    }
}
