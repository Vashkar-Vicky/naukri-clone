package io.mountblue.naukriproject.dto;

import io.mountblue.naukriclone.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserRequestDTO {
    private String email;
    private String password;
    private String mobileNumber;
    private Role role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
