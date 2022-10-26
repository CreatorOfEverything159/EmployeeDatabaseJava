package com.employee.Entity;

import java.util.Date;

public class Booking {

    private Employee holder;

    private Date startsAt;

    private Date endsAt;

    private Workplace workplace;

    public Booking(Employee holder, Date startsAt, Date endsAt, Workplace workPlace) {
        this.holder = holder;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.workplace = workPlace;
    }

    public Employee getHolder() {
        return holder;
    }

    public void setHolder(Employee holder) {
        this.holder = holder;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

}
