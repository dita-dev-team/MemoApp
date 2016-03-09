package com.dita.dev.memoapp.bus;

/**
 * Created by bwana.mkaya on 22/01/16.
 */
public class SignInEvent {
    public String username;
    public String passwd;

    public SignInEvent(String username, String passwd) {
        this.username = username;
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "SignInEvent{" +
                "username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
