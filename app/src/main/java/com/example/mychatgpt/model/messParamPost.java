package com.example.mychatgpt.model;

public class messParamPost {
    private String model, prompt;
    private  Integer max_tokens, temperature;

    public messParamPost() {

    }

    public messParamPost(String model, String prompt, Integer max_tokens, Integer temperature) {
        this.model = model;
        this.prompt = prompt;
        this.max_tokens = max_tokens;
        this.temperature = temperature;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Integer getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(Integer max_tokens) {
        this.max_tokens = max_tokens;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }
}
