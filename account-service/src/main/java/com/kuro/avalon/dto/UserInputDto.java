package com.kuro.avalon.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

import static com.kuro.avalon.constants.DBFieldConstants.*;
import static com.kuro.avalon.constants.DBFieldConstants.ROLE_CODES;

/**
 * 用户入参
 *
 * @author kuro
 * @create 2020-03-12
 **/
@Data
public class UserInputDto {
    private String code;
    private String username;
    private String nickName;
    private String password;
    private String phone;
    private String mail;
    private Set<String> roleCodes;
}
