package com.ims.dto;

import com.ims.entity.User;

public class UserCreation {
    private String name;
    private String email;
    private String phone;
    private Integer pincode;
    private String city;
    private String country;
    private String password;
    
    public UserCreation(String name,String email,String phone,Integer pincode, String city, String country,String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pincode = pincode;
        this.city = city;
        this.country = country;
        this.password = password;
    }

    public User toUser() {
        if(name == null) {
            throw new IllegalArgumentException("Name is required.");
        }
        if(email == null) {
            throw new IllegalArgumentException("Email is required.");
        }
        if(phone == null) {
            throw new IllegalArgumentException("Phone is required.");
        }
        if(pincode == null) {
            throw new IllegalArgumentException("Pincode is required.");
        }
        if(password == null) {
            throw new IllegalArgumentException("Password is required.");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPincode(pincode);
        user.setCity(city);
        user.setCountry(country);
        user.setPassword(password);
        return user;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getPincode() {
        return pincode;
    }
    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }    
}
