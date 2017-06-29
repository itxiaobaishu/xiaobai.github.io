package com.shop.bean;

public class Shop {

    private int id;

    private String name;

    private String people;

    private String intro;

    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Shop() {
    }

    public Shop(int id, String name, String people, String intro, String time) {
        this.id = id;
        this.name = name;
        this.people = people;
        this.intro = intro;
        this.time = time;
    }

}
