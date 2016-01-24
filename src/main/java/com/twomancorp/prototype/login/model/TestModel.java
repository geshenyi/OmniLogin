package com.twomancorp.prototype.login.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by ssge on 1/23/16.
 */
public class TestModel {
    @Id
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
