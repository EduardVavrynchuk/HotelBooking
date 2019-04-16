package com.hotel.webapp.transferobject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserBody {

    @NotNull
    @NotEmpty
    private String username;

    public UserBody() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
