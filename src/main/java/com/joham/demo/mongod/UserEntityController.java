package com.joham.demo.mongod;

import com.joham.demo.annotation.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * mongodb测试
 *
 * @author joham
 */
@RestController
@RequestMapping("/user")
public class UserEntityController {

    @Autowired
    private UserEntityService userService;

    /**
     * 添加
     */
    @PostMapping("/add")
    public String testSaveUser(@Valid @RequestBody UserEntity userEntity) {
        userService.saveUser(userEntity);
        return "1";
    }

    /**
     * 查询
     */
    @GetMapping("/find")
    @MyLog
    public ResponseEntity<List<UserEntity>> findUserByUserName() {
//        UserEntity user = userService.findUserByUserName("小明");
        List<UserEntity> userEntityList = userService.find();
        return ResponseEntity.ok(userEntityList);
    }

    @GetMapping("/find1")
    public ResponseEntity<List<UserEntity>> find1() {
        List<UserEntity> userEntityList = userService.find1();
        return ResponseEntity.ok(userEntityList);
    }

    @GetMapping("/find2")
    public ResponseEntity<List<UserEntity>> find2() {
        List<UserEntity> userEntityList = userService.find2();
        return ResponseEntity.ok(userEntityList);
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public void updateUser() {
        UserEntity user = new UserEntity();
        user.setId(2L);
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        userService.updateUser(user);
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public void deleteUserById() {
        userService.deleteUserById(1L);
    }
}
