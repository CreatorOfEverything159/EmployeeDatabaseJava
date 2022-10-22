package com.employee.Entity;

public class Workplace {

    private String type;

    private int number;

    public Workplace(String type, int number) {
        this.type = type;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public Workplace setType(String type) {
        this.type = type;

        return this;
    }

    public int getNumber() {
        return number;
    }

    public Workplace setNumber(int number) {
        this.number = number;

        return this;
    }

}
