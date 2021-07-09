package cn.itsource.fallback;

import cn.itsource.domain.User;
import cn.itsource.feignclient.UserFeignClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallBackFactory implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User getById(Long id) {
                throwable.printStackTrace();
                return new User(-1L,"工厂模式","这是托底数据...");
            }
        };
    }
}
