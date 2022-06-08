package com.example.pet_malo;

public class model
{
    String card_id,images,title,description;

    public model() {
    }

    public model(String card_id, String images, String title, String description) {
        this.card_id = card_id;
        this.images = images;
        this.title = title;
        this.description = description;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
