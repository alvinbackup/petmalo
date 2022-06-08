package com.example.pet_malo;

public class prod_model {
    String p_id, prod_id,product_categ,product_name,price,description,stock,image;

    public prod_model() {
    }

    public prod_model(String p_id, String prod_id, String product_categ, String product_name, String price, String description, String stock, String image) {
        this.p_id = p_id;
        this.prod_id = prod_id;
        this.product_categ = product_categ;
        this.product_name = product_name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.image = image;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProduct_categ() {
        return product_categ;
    }

    public void setProduct_categ(String product_categ) {
        this.product_categ = product_categ;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
