package com.validty.myproject;


import com.opencsv.bean.CsvBindByPosition;

public class Customer {
    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String firstName;

    @CsvBindByPosition(position = 2)
    private String lastName;

    @CsvBindByPosition(position = 3)
    private String company;

    @CsvBindByPosition(position = 4)
    private String email;

    @CsvBindByPosition(position = 5)
    private String address1;

    @CsvBindByPosition(position = 6)
    private String address2;

    @CsvBindByPosition(position = 7)
    private String zip;

    @CsvBindByPosition(position = 8)
    private String city;

    @CsvBindByPosition(position = 9)
    private String stateLong;

    @CsvBindByPosition(position = 10)
    private String state;

    @CsvBindByPosition(position = 11)
    private String phone;

    private boolean isDuplicate = false;


    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getStateLong() {
        return stateLong;
    }

    public String getState() {
        return state;
    }

    public String getPhone() {
        return phone;
    }

    public boolean getIsDuplicate(){
        return isDuplicate;
    }

    public void setIsDuplicate(boolean value){
        this.isDuplicate = value;
    }

    @Override
    public String toString(){
        return this.getId()+"   "+this.getFirstName()+"   "+this.getLastName()+"   "+this.getCompany()+"   "+this.getEmail()+"   "+this.getAddress1()+"   "+this.getAddress2()+"   "+this.getZip()+"   "+this.getCity()+"   "+this.getStateLong()+"   "+this.getState()+"   "+this.getPhone();
    }
}
