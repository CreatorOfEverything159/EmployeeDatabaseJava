package com.employee;

import com.employee.Exception.AlreadyExistsException;
import com.employee.Exception.NotFoundException;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();

        for (int i = 0; i < 5; i++) {
            try {
                database.addEmployee("login" + i, "password" + i, "name" + i);
            } catch (AlreadyExistsException e) {
                System.err.println(e.getMessage());
            }
        }

        try {
            database.addEmployee("login1", "1", "1");
        } catch (AlreadyExistsException e) {
            System.err.println(e.getMessage());
        }

        database.showEmployees();

        try {
            database.updateEmployeeByLogin("login3", "admin", "admin", "admin");
        } catch (AlreadyExistsException | NotFoundException e) {
            System.err.println(e.getMessage());
        }

        database.showEmployees();

        System.out.println(database.getEmployeeByLogin("admin"));
        System.out.println(database.getEmployeeByLogin("admin1"));

        database.deleteEmployeeByLogin("admin");
        database.showEmployees();

        try {
            database.addWorkplace("meeting room", 1);
        } catch (AlreadyExistsException e) {
            System.err.println(e.getMessage());
        }

        try {
            database.reserve("login0", 1, new Date(21, Calendar.NOVEMBER, 20, 10, 0), new Date(21, Calendar.NOVEMBER, 20, 20, 0));
        } catch (AlreadyExistsException e) {
            System.err.println(e.getMessage());
        }

        try {
            database.reserve("login1", 1, new Date(21, Calendar.NOVEMBER, 20, 9, 0), new Date(21, Calendar.NOVEMBER, 20, 19, 0));
        } catch (AlreadyExistsException e) {
            System.err.println(e.getMessage());
        }

        try {
            database.reserve("login2", 1, new Date(21, Calendar.NOVEMBER, 20, 11, 0), new Date(21, Calendar.NOVEMBER, 20, 21, 0));
        } catch (AlreadyExistsException e) {
            System.err.println(e.getMessage());
        }

        try {
            database.reserve("login4", 1, new Date(21, Calendar.NOVEMBER, 20, 9, 0), new Date(21, Calendar.NOVEMBER, 20, 21, 0));
        } catch (AlreadyExistsException e) {
            System.err.println(e.getMessage());
        }

        try {
            database.reserve("login1", 1, new Date(21, Calendar.NOVEMBER, 20, 11, 0), new Date(21, Calendar.NOVEMBER, 20, 19, 0));
        } catch (AlreadyExistsException e) {
            System.err.println(e.getMessage());
        }

        database.showReservations();

    }
}