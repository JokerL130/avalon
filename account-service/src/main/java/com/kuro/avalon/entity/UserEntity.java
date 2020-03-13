package com.kuro.avalon.entity;

import com.kuro.avalon.constants.UserStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

import static com.kuro.avalon.constants.DBDocumentConstants.USER_INFO;
import static com.kuro.avalon.constants.DBFieldConstants.*;

/**
 * 账户信息实体类
 *
 * @author kuro
 * @create 2020-03-10
 **/
@Data
@Document(USER_INFO)
public class UserEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    @Field(CODE)
    private String code;
    @Field(STATUS)
    private UserStatus status;
    @Indexed(unique = true)
    @Field(USERNAME)
    private String username;
    @Field(NICK_NAME)
    private String nickName;
    @Field(PASSWORD)
    private String password;
    @Indexed(unique = true)
    @Field(PHONE)
    private String phone;
    @Indexed(unique = true)
    @Field(MAIL)
    private String mail;
    @Field(ROLE_CODES)
    private Set<String> roleCodes;
    @Field(CREATED_USER)
    private String createdUser;
    @Field(CREATED_TIME)
    private Long createdTime;
    @Field(UPDATED_USER)
    private String updatedUser;
    @Field(UPDATED_TIME)
    private Long updatedTime;
    @Version
    @Field(VERSION)
    private Long version;
}
