package com.dita.dev.memoapp.bus;

public class SignUpEvent {

    public String username;
    public String userType;
    public String passwd;

    public SignUpEvent(String name, String passwd, String userType) {

        this.username = name;
        this.userType = userType;
        this.passwd = passwd;
    }

}
