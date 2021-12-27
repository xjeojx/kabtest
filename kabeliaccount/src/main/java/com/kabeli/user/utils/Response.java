package com.kabeli.user.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class Response {

    private boolean status;
    private String message;
    private JsonElement data;

    public String getMessage(){
        Gson gson = new Gson();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("message", message);
        if(data != null){
            jsonObject.add("data", data);
        }
        return gson.toJson(jsonObject);
    }

}
