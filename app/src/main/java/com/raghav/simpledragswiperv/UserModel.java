package com.raghav.simpledragswiperv;

public class UserModel {
    private String name;
    private String phone;
    private String country;

    public UserModel(String name, String phone, String country) {
        this.name = name;
        this.phone = phone;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
