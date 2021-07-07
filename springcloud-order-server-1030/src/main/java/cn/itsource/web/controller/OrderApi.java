package cn.itsource.web.controller;

import cn.itsource.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderApi {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/{id}")
    public User getById(@PathVariable("id") Long id){
        return restTemplate.getForObject("http://user-server/user/"+id,User.class);
    }
}
