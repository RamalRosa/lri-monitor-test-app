package com.test.lri.dto.responses.users;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsersListResponse implements Serializable {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;


}
