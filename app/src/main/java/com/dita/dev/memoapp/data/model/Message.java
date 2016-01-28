package com.dita.dev.memoapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by marvel on 9/30/15.
 */
public class Message {

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("Error")
    @Expose
    public String Error;

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("id_no")
    @Expose
    private String idNo;
    @SerializedName("name")
    @Expose
    private String name;
}
