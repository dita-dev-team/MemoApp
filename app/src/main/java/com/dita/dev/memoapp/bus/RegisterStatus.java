package com.dita.dev.memoapp.bus;

public class RegisterStatus {
    public Boolean success;
    public String message;

    public RegisterStatus() {
        this.success = false;
        this.message = null;
    }
}
