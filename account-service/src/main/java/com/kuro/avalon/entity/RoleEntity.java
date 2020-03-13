package com.kuro.avalon.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

import static com.kuro.avalon.constants.DBDocumentConstants.ROLE_INFO;
import static com.kuro.avalon.constants.DBFieldConstants.*;

/**
 * 角色信息
 *
 * @author kuro
 * @create 2020-03-11
 **/
@Data
@Document(ROLE_INFO)
public class RoleEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    @Field(CODE)
    private String code;
    @Field(NAME)
    private String name;
    @Field(PERMISSION_CODES)
    private Set<String> permissionCodes;
    @Field(CREATED_USER_CODE)
    private String createdUserCode;
    @Field(CREATED_TIME)
    private Long createdTime;
    @Field(UPDATED_USER_CODE)
    private String updatedUserCode;
    @Field(UPDATED_TIME)
    private Long updatedTime;
    @Version
    @Field(VERSION)
    private Long version;
}
