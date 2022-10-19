package com.employee;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();

        for (int i = 0; i < 5; i++) {
            database.addEmployee("login" + i, "password" + i, "name" + i);
        }

        database.show();

        database.updateEmployeeByLogin("login3", "admin", "admin", "admin");
        database.show();

        System.out.println(database.getEmployeeByLogin("admin"));
        System.out.println(database.getEmployeeByLogin("admin1"));

        database.deleteEmployeeByLogin("admin");
        database.show();
    }
}