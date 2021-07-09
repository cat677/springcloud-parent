package cn.itsource.config;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
@NoScan
public class RibbonConfig {

    @Bean
    public RandomRule randomRule(){
        return new RandomRule();
    }
}
