package com.wym.demo.service;

import com.wym.demo.pojo.User;
import com.wym.demo.repository.UserRepository;
import com.wym.demo.result.MessageCode;
import com.wym.demo.utils.Md5Utils;
import com.wym.demo.utils.TokenUtils;
import com.wym.demo.exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("用户名已存在");
        }
        String pwd = user.getPassword();
        String md5Password = Md5Utils.md5Password(pwd, "abc");
        user.setSalt("abc");
        user.setPassword(md5Password);
        return userRepository.save(user);
    }

    //用户登录
    public Map<String,Object> loginUser(User user) throws CustomerException {
        if(user.getUsername()==null||user.getPassword().trim().equals("")){
            throw new CustomerException(MessageCode.USERNAME_NULL);
        }
        User admin = userRepository.findByUsername(user.getUsername());
        if (admin == null) {
            throw new IllegalArgumentException("用户名或密码错误！");
        }
        String md5Password = Md5Utils.md5Password(user.getPassword(), admin.getSalt());
        if(!md5Password.equals(admin.getPassword())){
            throw new IllegalArgumentException("用户名或密码错误！");
        }
        //生成token
        String token = TokenUtils.token(user.getId(), user.getUsername());
        Map<String,Object> map= new HashMap<>();
        map.put("admin",admin);
        map.put("token",token);
        return map;
    }

    //修改用户
    public void updateUser(User user){
         userRepository.updateUser(user.getPassword(),user.getEmail(),user.getBirthday(),user.getId());
    }



}
