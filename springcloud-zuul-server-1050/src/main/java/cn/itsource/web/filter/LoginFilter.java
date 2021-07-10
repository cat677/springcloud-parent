package cn.itsource.web.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.PathMatcher;
import java.util.HashMap;
import java.util.Map;
@Component
//过滤器
public class LoginFilter extends ZuulFilter {
    //路径匹配器
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //放行
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String url = request.getRequestURI();
        boolean skip = pathMatcher.match("/services/pay/login", url) ||
                       pathMatcher.match("/services/order/login", url);
        return !skip;
    }

    @Override
    public Object run() throws ZuulException {
        //登陆逻辑
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
        response.setContentType("application/json; charset=UTF-8");
        String token = request.getHeader("token");
        if (!StringUtils.hasLength(token)){
            Map<String,Object> jsonResult = new HashMap<>();
            jsonResult.put("success","false");
            jsonResult.put("message","没有访问权限");
            try {
                response.getWriter().print(JSON.toJSONString(jsonResult));
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentContext.setSendZuulResponse(false);
        }
        return null;
    }
}
