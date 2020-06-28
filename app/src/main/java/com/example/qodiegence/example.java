package com.example.qodiegence;

public class example {
    String name,phone,email,address,location,year,opentime,closetime;

    public example(String name, String phone, String email, String address, String location, String year, String opentime, String closetime) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.location = location;
        this.year = year;
        this.opentime = opentime;
        this.closetime = closetime;
    }

    public String getOpentime() {
        return opentime;
    }

    public String getClosetime() {
        return closetime;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getLocation() {
        return location;
    }

    public String getYear() {
        return year;
    }
}
