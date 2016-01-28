package com.dita.dev.memoapp.bus;

/**
 * Created by bwana.mkaya on 22/01/16.
 */
public class SignInEvent {
    public String id;
    public String passwd;

    public SignInEvent(String id, String passwd) {
        this.id = id;
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "SignInEvent{" +
                "id='" + id + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
