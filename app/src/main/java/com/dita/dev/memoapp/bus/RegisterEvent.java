package com.dita.dev.memoapp.bus;

public class RegisterEvent {

    public String username;
    public String userType;
    public String passwd;

    public RegisterEvent(String name, String passwd, String userType) {

        this.username = name;
        this.userType = userType;
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "RegisterEvent{" +
                "username='" + username + '\'' +
                ", userType='" + userType + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
