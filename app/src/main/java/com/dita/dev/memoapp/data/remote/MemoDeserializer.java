package com.dita.dev.memoapp.data.remote;

import com.dita.dev.memoapp.data.model.*;
import com.dita.dev.memoapp.data.model.Error;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;


public class MemoDeserializer extends JsonDeserializer<MemoResponse> {

    @Override
    public MemoResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        MemoResponse response = new MemoResponse();
        JsonNode node = p.getCodec().readTree(p);

        if (node.get("error") != null) {
            response.error = new ObjectMapper().readValue(node.toString(), Error.class);
        } else if (node.get("message") != null) {
            // check if its a message with extra information
            if (node.get("message").isContainerNode()) {
                Iterator<JsonNode> children = node.get("message").elements();
                while (children.hasNext()) {
                    response.message = children.next().toString();
                }
            } else {
                response.message = node.get("message").toString();
            }
        } else if (node.get("data") != null) {
            JavaType type = null;
            ObjectMapper mapper = new ObjectMapper();
            if (node.findValue("user") != null) {
                type = mapper.getTypeFactory().constructParametricType(Data.class, UserData.class);
            } else if (node.findValue("token") != null) {
                type = mapper.getTypeFactory().constructParametricType(Data.class, Token.class);
            }

            response.data = new ObjectMapper().readValue(node.toString(), type);
        }
        return response;
    }
}
