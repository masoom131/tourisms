package com.web.tourism.payload;

import com.web.tourism.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private long userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userGender;
    private String userPassword;
    /*private Set<RoleDto> roles = new HashSet<>();*/
}
