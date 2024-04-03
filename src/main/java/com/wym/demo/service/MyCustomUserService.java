package com.wym.demo.service;


import com.wym.demo.pojo.User;
import com.wym.demo.repository.UserRepository;
import com.wym.demo.utils.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MyCustomUserService implements UserDetailsService {
    /**
     * 登陆验证时，通过username获取用户的所有权限信息
     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     */

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //调用数据库，对username进行查询，看看在数据库中是否存在
        MyUserDetails myUserDetail = new MyUserDetails();
        User user = userRepository.findByUsername(username);
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("用户名不能为空！");
        }
        if (!username.equals(user.getUsername())) {
            throw new RuntimeException("用户名不存在！");
        }
        myUserDetail.setUsername(username);
        myUserDetail.setPassword("123456");
        return myUserDetail;
    }
}