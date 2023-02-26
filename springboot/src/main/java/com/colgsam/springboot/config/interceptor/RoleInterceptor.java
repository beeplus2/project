package com.colgsam.springboot.config.interceptor;

import com.colgsam.springboot.common.Constants;
import com.colgsam.springboot.common.Role;
import com.colgsam.springboot.exception.ServiceException;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * 自定义一个权限拦截器, 继承HandlerInterceptorAdapter类
 */
@Component
public class RoleInterceptor implements HandlerInterceptor {

    // 在调用方法之前执行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("经过了RoleInterceptor拦截器");

        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();

        // 获取出方法上的Role注解
        Role role = method.getAnnotation(Role.class);
        // 如果注解为null, 说明不需要拦截, 直接放过
        if (role == null) {
            return true;
        }

//        // 从前端直接发一个userId来模拟登录,如果有说明登录了,没有就没登录
//        String userId = (String) request.getParameter("userId");
//        // 没有信息说明没有登陆
//        if (userId == null || userId.length()==0) {
//            //自定义登录异常
//            throw new LoginException();
//        }

        // 到数据库权限表中查询用户拥有的角色集合, 进行对比完成权限校验
        //TODO
        List<String> userRole = Collections.EMPTY_LIST;

        if (userRole != null && userRole.size()>0) {
            // 写了注解说明需要权限
            // 拥有其中一个角色就通过
            if (role.roles().length > 0) {
                // 获取多个角色
                // 如果权限配置不为空, 则取出配置值
                String[] roles = role.roles();

                // 校验角色权限操作,自己实现.....
                // 如果角色校验不通过,在这可以不处理,留到最后处理
                
            } else if (role.rolesMust().length > 0) {

                // 必须有这些角色才通过
                String[] rolesMust = role.rolesMust();

                // 校验角色权限操作,自己实现.....
                // 如果角色校验不通过,在这可以不处理,留到最后处理

            } else {
                // 默认user
                String value = role.value();
                for (String str : userRole) {
                    if (value.equals(str)) return true;
                }
            }
        }
        
        // 说明没登录抛出自定义的角色异常,没有自定义异常可以直接返回false,代码就不会执行下去
        throw new ServiceException(Constants.CODE_401,"无权限");

        // 拦截之后应该返回公共结果, 这里没做处理
        // return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandler");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("afterCompletion");
    }

}

