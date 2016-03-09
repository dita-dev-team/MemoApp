package com.dita.dev.memoapp.bus;

public class SignInStatus {
    public Boolean success;
    public String token;
    public String message;


    public SignInStatus() {
        this.success = false;
        this.token = null;
        this.message = null;
    }
}
