package com.kabeli.user.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "phone")
@Data
public class Phone implements Jsonizer{

    @Id
    @GeneratedValue
    private UUID id_phone;

    @Column
    private Integer number;

    @Column
    private Integer citycode;

    @Column
    private Integer countrycode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account")
    private Account account;

    public JsonElement toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("number", getNumber());
        jsonObject.addProperty("citycode", getCitycode());
        jsonObject.addProperty("countrycode", getCountrycode());
        return jsonObject;
    }

}