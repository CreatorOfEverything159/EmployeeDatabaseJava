package com.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {

    public static List<Employee> database = new ArrayList<>();

    public Optional<Employee> getEmployeeByLogin(String login) {
        return database.stream()
                .filter(e -> e.getLogin().equals(login))
                .findFirst();
    }

    public void addEmployee(String login, String password, String name) {
        try {
            database.add(new Employee(name, login, password));
        } catch (Exception e) {
            System.out.println("Пользователь с таким именем уже существует!");
        } finally {
            System.out.println("Пользователь успешно добавлен!");
        }
    }

    public void updateEmployeeByLogin(String login, String newLogin, String newPassword, String newName) {
        Optional<Employee> employee = getEmployeeByLogin(login);

        if (employee.isPresent() && getEmployeeByLogin(newLogin).isEmpty()) {
            employee.get()
                    .setName(newName)
                    .setLogin(newLogin)
                    .setPassword(newPassword);
        }
    }

    public void deleteEmployeeByLogin(String login) {
        database.removeIf(e -> e.getLogin().equals(login));
    }

    public void show() {
        System.out.println(database.toString());
    }

}
