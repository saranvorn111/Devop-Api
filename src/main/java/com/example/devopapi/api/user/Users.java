package com.example.devopapi.api.user;

import com.example.devopapi.api.auth.Role;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Integer id;
    private String name;
    private String gender;
    private String oneSignalId;
    private String studentCardId;
    private Boolean isStudent;
    private Boolean isDeleted;

    //Auth feature info

    private String email;
    private String password;
    private Boolean isVerified;
    private String verifiedCode;

    //Role info
    List<Role> roles;
}
