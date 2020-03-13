package com.kuro.avalon.handler;

import com.kuro.avalon.constants.UserStatus;
import com.kuro.avalon.dto.UserDetailDto;
import com.kuro.avalon.dto.UserInputDto;
import com.kuro.avalon.entity.UserEntity;
import com.kuro.avalon.exception.CheckException;
import com.kuro.avalon.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 账号处理层
 *
 * @author kuro
 * @create 2020-03-10
 **/
@Component
public class UserHandler {
    @Resource
    private UserRepository userRepository;
    @Resource
    private ReactiveMongoTemplate reactiveMongoTemplate;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    public Mono<ServerResponse> addOne(ServerRequest request) {
        String operatorUser = "";
        Mono<UserEntity> userEntityMono = request.bodyToMono(UserInputDto.class)
                // TODO: 校验
                //Dto转换为实体
                .map(inputDto -> {
                    UserEntity jobEntity = new UserEntity();
                    return this.inputTrans(inputDto, jobEntity, operatorUser);
                });
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(userRepository.insert(userEntityMono), UserEntity.class);
    }



    public Mono<ServerResponse> getUserDetail(ServerRequest request) throws CheckException {
        String account = request.queryParam("username").orElseThrow(() ->new CheckException("username", "null"));
        Criteria criteria = new Criteria();
        criteria.orOperator(
                Criteria.where("username").is(account),
                Criteria.where("mail").is(account),
                Criteria.where("phone").is(account));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(reactiveMongoTemplate.findOne(new Query(criteria), UserEntity.class)
                        .map(userEntity -> UserDetailDto.build(userEntity)), UserDetailDto.class);
    }

    private UserEntity inputTrans(@Valid UserInputDto inputDto, UserEntity entity, String operatorUser) {
        BeanUtils.copyProperties(inputDto, entity);
        entity.setPassword(passwordEncoder.encode(inputDto.getPassword()));
        entity.setUpdatedTime(System.currentTimeMillis());
        entity.setUpdatedUser(operatorUser);
        if(entity.getCreatedTime() == null) {
            entity.setCreatedTime(entity.getCreatedTime());
            entity.setCreatedUser(entity.getUpdatedUser());
            entity.setStatus(UserStatus.NORMAL);
        }
        return entity;
    }
}
