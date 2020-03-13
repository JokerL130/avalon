package com.kuro.avalon.repository;

import com.kuro.avalon.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 账号持久层
 *
 * @author kuro
 * @create 2020-03-10
 **/
@Repository
public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {
}
