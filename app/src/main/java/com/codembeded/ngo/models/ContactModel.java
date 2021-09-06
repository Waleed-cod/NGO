package com.codembeded.ngo.models;

public class ContactModel {
    String contact_img,contact_name,contact_phone;

    public ContactModel() {
    }

    public ContactModel(String char_text_box, String contact_name, String contact_phone) {
        this.contact_img = char_text_box;
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
    }

    public String getChar_text_box() {
        return contact_img;
    }

    public void setContact_img(String contact_img) {
        this.contact_img = contact_img;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }
}
