package com.example.springproject.model.request;

import com.example.springproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String name;
    private String email;
    private String login;
    private String password;

    public User build(){
        User user = new User()
                .setName(this.name)
                .setEmail(this.email)
                .setLogin(this.login)
                .setPassword(this.password);
        return user;
    }

}
