package com.joham.demo.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * redis缓存以及spring缓存
 * https://www.cnblogs.com/txt1024/p/15806631.html
 *
 * @author joham
 */

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/getUser")
    @Cacheable(value = "user-key")
    public User getUser() {
        User user = userService.findById(1L);
        log.info("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> user = usersService.findByUserName("aa");
        return user;
    }

    @RequestMapping("/get")
    public User get() {
        List<User> user = userService.get("%11%");
        if (CollectionUtils.isEmpty(user)) {
            return null;
        }
        return user.get(0);
    }

    @RequestMapping("/add")
    public String add() {
        User user = new User();
        user.setEmail("aa@qq.com");
        user.setUserName("aa");
        user.setNickName("aa");
        user.setPassWord("123456");
        user.setRegTime(new Date().toString());
        usersService.insert(user);
        return "success";
    }
}
