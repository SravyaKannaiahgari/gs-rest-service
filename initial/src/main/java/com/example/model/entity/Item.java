package com.example.model.entity;

public class Item {
    private String name;
    private String image;
    private Integer id;
    private Boolean available;

    public void setAvailable(Boolean inputAvailable) {
        this.available = inputAvailable;
    }
    public void setId(Integer inputId) {
        this.id = inputId;
    }
    public void setImage(String inputImage) {
        this.image = inputImage;
    }
    public void setName(String inputName) {
        this.name = inputName;
    }

    public Boolean getAvailable() {
        return available;
    }
    public Integer getId() {
        return id;
    }
    public String getImage() {
        return image;
    }
    public String getName() {
        return name;
    }
}
