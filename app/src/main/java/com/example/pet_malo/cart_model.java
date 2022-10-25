package com.example.pet_malo;

public class cart_model {
    String product_categ;
    String product_desc;
    String product_id;
    String product_img;
    String product_name;
    String product_price;
    String product_quan;
    String store;

    public cart_model() {
    }

    public cart_model(String product_categ, String product_desc, String product_id, String product_img, String product_name, String product_price, String product_quan, String store) {
        this.product_categ = product_categ;
        this.product_desc = product_desc;
        this.product_id = product_id;
        this.product_img = product_img;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quan = product_quan;
        this.store = store;
    }

    public String getProduct_categ() {
        return product_categ;
    }

    public void setProduct_categ(String product_categ) {
        this.product_categ = product_categ;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_quan() {
        return product_quan;
    }

    public void setProduct_quan(String product_quan) {
        this.product_quan = product_quan;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
