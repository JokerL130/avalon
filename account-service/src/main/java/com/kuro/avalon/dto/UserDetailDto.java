package com.kuro.avalon.dto;

import com.kuro.avalon.entity.UserEntity;
import lombok.Data;

import java.util.Set;

/**
 * @author kuro
 * @create 2020-03-10
 **/
@Data
public class UserDetailDto {
    private String username;
    private String password;
    private Set<String> roles;

    public UserDetailDto() {
    }

    public UserDetailDto(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static UserDetailDto build(UserEntity userEntity) {
        return new UserDetailDto(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRoleCodes());
    }
}
