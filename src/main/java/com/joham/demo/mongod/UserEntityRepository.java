package com.joham.demo.mongod;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * mongo查询
 *
 * @author joham
 */
public interface UserEntityRepository extends MongoRepository<UserEntity, String>,
        QuerydslPredicateExecutor<UserEntity> {

    /**
     * 根据用户名查询
     *
     * @param userName
     * @return
     */
    @Query("{'userName' : ?0}")
    List<UserEntity> findByUserName(String userName);

    /**
     * 用户名以字母开始
     *
     * @param a
     * @return
     */
    List<UserEntity> findByUserNameStartingWith(String a);
}
