package com.chipnews.api.dtos;

import com.chipnews.api.entities.User;
import org.springframework.beans.BeanUtils;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;

    public UserResponse(User user) {
        BeanUtils.copyProperties(user, this);
    }

    public UserResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
