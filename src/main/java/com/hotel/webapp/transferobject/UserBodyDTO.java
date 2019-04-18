package com.hotel.webapp.transferobject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserBodyDTO {

    @NotNull
    @NotEmpty
    private String username;

    public UserBodyDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserBodyDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}
