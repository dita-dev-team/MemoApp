package com.dita.dev.memoapp.data.model;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "error"
})
public class Error {

    @JsonProperty("error")
    public Error_ error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Generated("org.jsonschema2pojo")
    @JsonPropertyOrder({
            "code",
            "message"
    })
    public static class Error_ {

        @JsonProperty("code")
        public Integer code;
        @JsonProperty("message")
        public String message;
    }
}


