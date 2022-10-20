package com.employee;

import com.employee.Entity.Employee;
import com.employee.Exception.AlreadyExistsException;
import com.employee.Exception.NotFoundException;

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

    public void addEmployee(String login, String password, String name) throws AlreadyExistsException {
        if (getEmployeeByLogin(login).isPresent()) {
            throw new AlreadyExistsException("Пользователь с логином " + login + " уже существует!");
        }

        database.add(new Employee(name, login, password));
    }

    public void updateEmployeeByLogin(String login, String newLogin, String newPassword, String newName) throws AlreadyExistsException, NotFoundException {
        Optional<Employee> employee = getEmployeeByLogin(login);

        if (employee.isEmpty()) {
            throw new NotFoundException("Пользователя с логином " + login + " не существует!");
        }

        if (getEmployeeByLogin(newLogin).isPresent()) {
            throw new AlreadyExistsException("Пользователь с логином " + login + " уже существует!");
        }

        employee.get()
                .setName(newName)
                .setLogin(newLogin)
                .setPassword(newPassword);
    }

    public void deleteEmployeeByLogin(String login) {
        database.removeIf(e -> e.getLogin().equals(login));
    }

    public void show() {
        System.out.println(database.toString());
    }

}
