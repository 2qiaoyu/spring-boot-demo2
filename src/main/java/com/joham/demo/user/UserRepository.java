package com.joham.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author joham
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查询
     *
     * @param userName
     * @return
     */
    List<User> findByUserName(String userName);

    /**
     * 根据邮箱查询
     *
     * @param email
     * @return
     */
    List<User> findByEmailLike(String email);
}
