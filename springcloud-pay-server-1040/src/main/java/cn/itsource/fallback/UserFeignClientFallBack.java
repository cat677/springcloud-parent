package cn.itsource.fallback;

import cn.itsource.domain.User;
import cn.itsource.feignclient.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallBack implements UserFeignClient {

    private Logger log = LoggerFactory.getLogger(UserFeignClientFallBack.class);

    @Override
    public User getById(Long id) {
        log.info("用户服务不可用哦");
        return new User(-1L,"","这是托底数据...");
    }
}
