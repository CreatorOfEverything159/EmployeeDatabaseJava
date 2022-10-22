package com.employee;

import com.employee.Entity.Booking;
import com.employee.Entity.Employee;
import com.employee.Entity.Workplace;
import com.employee.Exception.AlreadyExistsException;
import com.employee.Exception.NotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Database {

    public static List<Employee> employees = new ArrayList<>();
    public static List<Workplace> workplaces = new ArrayList<>();
    public static List<Booking> bookings = new ArrayList<>();

    public Optional<Employee> getEmployeeByLogin(String login) {
        return employees.stream()
                .filter(e -> e.getLogin().equals(login))
                .findFirst();
    }

    public void addEmployee(String login, String password, String name) throws AlreadyExistsException {
        if (getEmployeeByLogin(login).isPresent()) {
            throw new AlreadyExistsException("Пользователь с логином " + login + " уже существует!");
        }

        employees.add(new Employee(name, login, password));
    }

    public void updateEmployeeByLogin(String login, String newLogin, String newPassword, String newName)
            throws AlreadyExistsException, NotFoundException {
        Optional<Employee> employee = getEmployeeByLogin(login);

        if (employee.isEmpty()) {
            throw new NotFoundException("Пользователя с логином " + login + " не существует!");
        }

        if (getEmployeeByLogin(newLogin).isPresent()) {
            throw new AlreadyExistsException("Пользователь с логином " + newLogin + " уже существует!");
        }

        employee.get()
                .setName(newName)
                .setLogin(newLogin)
                .setPassword(newPassword);
    }

    public void deleteEmployeeByLogin(String login) {
        employees.removeIf(e -> e.getLogin().equals(login));
    }

    public Optional<Workplace> getWorkplaceByNumber(int number) {
        return workplaces.stream()
                .filter(w -> w.getNumber() == number)
                .findFirst();
    }

    public void addWorkplace(String type, int number) throws AlreadyExistsException {
        if (getWorkplaceByNumber(number).isPresent()) {
            throw new AlreadyExistsException("Место с номером " + number + " уже существует!");
        }

        workplaces.add(new Workplace(type, number));
    }

    public void updateWorkplaceByNumber(int number, String newType, int newNumber)
            throws AlreadyExistsException, NotFoundException {
        Optional<Workplace> workplace = getWorkplaceByNumber(number);

        if (workplace.isEmpty()) {
            throw new NotFoundException("Место с номером " + number + " не существует!");
        }

        if (getWorkplaceByNumber(newNumber).isPresent()) {
            throw new AlreadyExistsException("Место с номером " + newNumber + " уже существует!");
        }

        workplace.get()
                .setNumber(newNumber)
                .setType(newType);
    }

    public void deleteWorkplaceByNumber(int number) {
        workplaces.removeIf(w -> w.getNumber() == number);
    }

    public List<Booking> getBookingsByEmployee(Employee employee) {
        return bookings.stream()
                .filter(b -> b.getHolder().equals(employee))
                .collect(Collectors.toList());
    }

    private boolean isTimeIntersect(Date start1, Date end1, Date start2, Date end2) {
        return (start1.before(start2) && end1.after(start2)) || (start1.after(start2) && start1.before(end2));
    }

    private List<Booking> getBookingsByWorkplace(Workplace workplace) {
        return bookings.stream()
                .filter(b -> b.getWorkplace().getNumber() == workplace.getNumber())
                .collect(Collectors.toList());
    }

    public void reserve(String holderLogin, int workplaceNumber, Date start, Date end) throws AlreadyExistsException {
        Optional<Employee> holder = getEmployeeByLogin(holderLogin);
        if (holder.isEmpty()) {
            throw new AlreadyExistsException("Пользователя с логином " + holderLogin + " не существует!");
        }

        Optional<Workplace> workplace = getWorkplaceByNumber(workplaceNumber);
        if (workplace.isEmpty()) {
            throw new AlreadyExistsException("Места с номером " + workplaceNumber + " не существует!");
        }

        List<Booking> bookingsByWorkplace = this.getBookingsByWorkplace(workplace.get());
        Optional<Booking> bookingWithIntersectTime = bookingsByWorkplace.stream()
                .filter(b -> this.isTimeIntersect(start, end, b.getStartsAt(), b.getEndsAt()))
                .findFirst();

        if (bookingWithIntersectTime.isEmpty()) {
            bookings.add(new Booking(holder.get(), start, end, workplace.get()));
        } else {
            throw new AlreadyExistsException("Место " + workplace.get().getNumber() + " уже забронировано на это время!");
        }
    }

    public void showEmployees() {
        System.out.println("qwe".getClass());
        System.out.println(employees.toString());
    }

    public void showWorkplaces() {
        System.out.println(workplaces.toString());
    }

    public void showReservations() {
        System.out.println(bookings.toString());
    }

}
