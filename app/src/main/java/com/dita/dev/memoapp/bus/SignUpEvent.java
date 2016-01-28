package com.dita.dev.memoapp.bus;

/**
 * Created by marvel on 11/16/15.
 */
public class SignUpEvent {
    public String name;
    public String ID;
    public String passwd;

    public SignUpEvent(String name, String ID, String passwd) {
        this.name = name;
        this.ID = ID;
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "SignUpEvent{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
