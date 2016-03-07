package com.dita.dev.memoapp.data.model;

public class MemoResponse {
    public Error error;
    public String message;
    public Data data;

    public MemoResponse() {
        error = null;
        message = null;
        data = null;
    }
}
