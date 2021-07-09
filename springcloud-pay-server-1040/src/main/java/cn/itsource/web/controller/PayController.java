package cn.itsource.web.controller;

import cn.itsource.domain.User;
import cn.itsource.feignclient.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class PayController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/pay/{id}")
    public User getById(@PathVariable("id") Long id){
        return userFeignClient.getById(id);
    }

}
