package com.accenture.gradetool.domain.subject.user;

public class Password {

    private String oldPassword;

    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public Password setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public Password setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }
}
