package com.kabeli.user.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
import java.util.List;

@Entity(name = "account")
@Table(name = "account")
@Data
public class Account implements Jsonizer{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_account", updatable = false, nullable = false)
    private UUID id_account;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @CreationTimestamp
    private Date created;

    @CreationTimestamp
    private Date updated;

    @Column(name = "last_login")
    @CreationTimestamp
    private Date lastLogin;

    @Column()
    private Boolean isActive=false;

    @Column
    private String token="";

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Phone> phones;

    public JsonElement toJson(){
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id_account", getId_account().toString());
        jsonObject.addProperty("name", getName());
        jsonObject.addProperty("email", getEmail());
        jsonObject.addProperty("password", getPassword());
        jsonObject.addProperty("created", getCreated().toString());
        jsonObject.addProperty("updated", getUpdated().toString());
        jsonObject.addProperty("lastLogin", getLastLogin().toString());
        jsonObject.addProperty("isActive", getIsActive());
        jsonObject.addProperty("token", getToken());

        JsonArray array=new JsonArray();
        for(Phone phone: phones){
            array.add(phone.toJson());
        }
        jsonObject.add("phones", array);

        return jsonObject;
    }


}
