package com.employee;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database {

    public static List<Employee> database = new ArrayList<>();

    public @Nullable Employee getEmployeeByLogin(String login) {
        return database
                .stream()
                .filter(employee -> employee.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    public void addEmployee(String login, String password, String name) {
        if (getEmployeeByLogin(login) == null) {
            database.add(new Employee(name, login, password));
        }
    }


    public void updateEmployeeByLogin(String login, String newLogin, String newPassword, String newName) {
        Employee employee = getEmployeeByLogin(login);

        if (employee != null && getEmployeeByLogin(newLogin) == null) {
            employee
                    .setName(newName)
                    .setLogin(newLogin)
                    .setPassword(newPassword);
        }
    }

    public void deleteEmployeeByLogin(String login) {
        database.removeIf(employee -> employee.getLogin().equals(login));
    }

    public void show() {
        System.out.println(database.toString());
    }

}
