package com.joham.demo.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/testSelectOne")
    public User testSelectOne() {
        User user = userMapper.selectById(1L);
        return user;
    }

    @RequestMapping("/testInsert")
    public void testInsert() {
        User user = new User();
        user.setUserName("bab");
        user.setEmail("neo@tooool.org");
        user.setNickName("asd");
        user.setPassWord("xcb");
        user.setRegTime("2023-03-21:15:36:22");
        userMapper.insert(user);
    }

    @RequestMapping("/testUpdate")
    public void testUpdate() {
        userMapper.update(null, Wrappers.<User>lambdaUpdate().set(User::getEmail, "123@123.com").eq(User::getId, 2L));
    }

    @RequestMapping("/testPage")
    public void testPage() {
        System.out.println("----- baseMapper 自带分页 ------");
        Page<User> page = new Page<>(1, 10);
        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>());
//        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>().gt("id", 26));
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        System.out.println(userIPage.getRecords());
        System.out.println("----- baseMapper 自带分页 ------");
    }

    @RequestMapping("/testSelect")
    public void testSelect() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

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
