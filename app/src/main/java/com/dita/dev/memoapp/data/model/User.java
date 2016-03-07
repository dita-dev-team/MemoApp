package com.dita.dev.memoapp.data.model;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "created_at",
        "email",
        "type",
        "updated_at",
        "username"
})
public class User {
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("email")
    public String email;
    @JsonProperty("type")
    public String type;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("username")
    public String username;
}
