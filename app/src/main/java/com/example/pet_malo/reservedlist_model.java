package com.example.pet_malo;

public class reservedlist_model {


    String prod_name,rev_desc,rev_quan,rev_categ,cust_name,cust_add,cust_contact,cust_email,store_id,prod_total,prod_image,prodcount,status;

    public reservedlist_model() {
    }

    public reservedlist_model(String prod_name, String rev_desc, String rev_quan, String rev_categ, String cust_name, String cust_add, String cust_contact, String cust_email, String store_id, String prod_total, String prod_image, String prodcount,String status) {
        this.prod_name = prod_name;
        this.rev_desc = rev_desc;
        this.rev_quan = rev_quan;
        this.rev_categ = rev_categ;
        this.cust_name = cust_name;
        this.cust_add = cust_add;
        this.cust_contact = cust_contact;
        this.cust_email = cust_email;
        this.store_id = store_id;
        this.prod_total = prod_total;
        this.prod_image = prod_image;
        this.prodcount = prodcount;
        this.status=status;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getRev_desc() {
        return rev_desc;
    }

    public void setRev_desc(String rev_desc) {
        this.rev_desc = rev_desc;
    }

    public String getRev_quan() {
        return rev_quan;
    }

    public void setRev_quan(String rev_quan) {
        this.rev_quan = rev_quan;
    }

    public String getRev_categ() {
        return rev_categ;
    }

    public void setRev_categ(String rev_categ) {
        this.rev_categ = rev_categ;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_add() {
        return cust_add;
    }

    public void setCust_add(String cust_add) {
        this.cust_add = cust_add;
    }

    public String getCust_contact() {
        return cust_contact;
    }

    public void setCust_contact(String cust_contact) {
        this.cust_contact = cust_contact;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getProd_total() {
        return prod_total;
    }

    public void setProd_total(String prod_total) {
        this.prod_total = prod_total;
    }

    public String getProd_image() {
        return prod_image;
    }


    public void setProd_image(String prod_image) {
        this.prod_image = prod_image;
    }
    public String getProd_count() {
        return prodcount;
    }
    public void setProd_count(String prodcount) {
        this.prodcount = prodcount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
