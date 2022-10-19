package com.employee;

public class Employee {

    private String name;

    private String login;

    private String password;

    public Employee(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;

        return this;
    }

    public String getLogin() {
        return login;
    }

    public Employee setLogin(String login) {
        this.login = login;

        return this;
    }

    public String getPassword() {
        return password;
    }

    public Employee setPassword(String password) {
        this.password = password;

        return this;
    }

    public String toString() {
        return this.login + ":" + this.name + ":" + this.password;
    }
}
