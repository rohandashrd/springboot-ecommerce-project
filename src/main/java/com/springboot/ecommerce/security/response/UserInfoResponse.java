package com.springboot.ecommerce.security.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserInfoResponse {
    @Getter
    @Setter
    private Long id;

    @Setter
    @Getter
    private String jwtToken;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private List<String> roles;

    public UserInfoResponse(Long id,String username, List<String> roles, String jwtToken) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.jwtToken = jwtToken;

    }

    public UserInfoResponse(Long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}


