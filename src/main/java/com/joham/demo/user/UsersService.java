package com.joham.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author joham
 */
@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(cacheNames = "UsersServiceCache", keyGenerator = "myKeyGenerator")
    public List<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<User> get(String email) {
        return userRepository.findByEmailLike(email);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    public List<User> getALL() {
        return userRepository.findAll();
    }

    /**
     * 分页查询用户
     *
     * @param pageable
     * @return
     */
    public Page<User> getALLByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * 新增用户
     * @param student
     */
    @CacheEvict(cacheNames = "UsersServiceCache", allEntries = true)
    public void insert(User student) {
        userRepository.save(student);
    }
}
