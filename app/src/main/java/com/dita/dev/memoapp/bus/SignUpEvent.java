package com.dita.dev.memoapp.bus;

/**
 * Created by marvel on 11/16/15.
 */
public class SignUpEvent {
    public String name;
    public String userType;
    public String passwd;

    public SignUpEvent(String name, String passwd, String userType) {
        this.name = name;
        this.userType = userType;
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "SignUpEvent{" +
                "name='" + name + '\'' +
                ", userType='" + userType + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
