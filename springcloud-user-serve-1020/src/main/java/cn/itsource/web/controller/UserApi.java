package cn.itsource.web.controller;

import cn.itsource.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {

    @Value("${server.port}")
    private int port;

    @GetMapping("/user/{id}")
    public User getById(@PathVariable("id")Long id){
        return new User(id,"zs","你好张三,port:"+port);
    }
}
