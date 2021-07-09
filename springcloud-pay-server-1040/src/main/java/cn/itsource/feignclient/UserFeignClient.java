package cn.itsource.feignclient;

import cn.itsource.domain.User;
import cn.itsource.fallback.UserFeignClientFallBack;
import cn.itsource.fallback.UserFeignClientFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "user-server",fallback = UserFeignClientFallBack.class)
@FeignClient(value = "user-server",fallbackFactory = UserFeignClientFallBackFactory.class)
public interface UserFeignClient {
    @GetMapping("/user/{id}")
    public User getById(@PathVariable("id") Long id);
}
