package com.example.mychatgpt.model;

import java.util.List;

public class messResponse {
    private String id, object;
    private List<Choices> choices;

    public messResponse(String id, String object, List<Choices> choices) {
        this.id = id;
        this.object = object;
        this.choices = choices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Choices> getChoices() {
        return choices;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }
}
