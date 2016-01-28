package com.dita.dev.memoapp.data.remote;

import com.dita.dev.memoapp.data.model.Message;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by marvel on 10/5/15.
 */
public class MessageSerializer implements JsonDeserializer<Message> {
    @Override
    public Message deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Message message = new Message();
        if(json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            if(jsonObject.has("message")) {
                JsonElement products = jsonObject.get("message");
                if(products.isJsonArray()) {
                    //Type productListType = new TypeToken<List<Product>>(){}.getType();
                    //message.products = context.deserialize(products, productListType);
                } else {
                   // message.products = new ArrayList<Product>(1);
                   // Product product = context.deserialize(products, Product.class);
                   // message.products.add(product);
                }
            }
            return message;
        } else {
            // top level is supposed to be an object
            throw new IllegalStateException();
        }
    }
}
