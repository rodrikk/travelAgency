package com.exercise.travelAgency;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;

@Entity
public class reservations {
    private @Id @GeneratedValue int id;
    private String firstName, lastName;
    private Date reservationDate;
    private float paidDeposit;

    private travelPkg bookedPkg;

    public reservations() {}

    public reservations(String firstName, String lastName, Date reservationDate, float paidDeposit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservationDate = reservationDate;
        this.paidDeposit = paidDeposit;
    }

    public reservations(String firstName, String lastName, Date reservationDate, float paidDeposit, travelPkg bookedPkg) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservationDate = reservationDate;
        this.paidDeposit = paidDeposit;
        this.bookedPkg = bookedPkg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public float getPaidDeposit() {
        return paidDeposit;
    }

    public void setPaidDeposit(float paidDeposit) {
        this.paidDeposit = paidDeposit;
    }

    public travelPkg getBookedPkg() {
        return bookedPkg;
    }

    public void setBookedPkg(travelPkg bookedPkg) {
        this.bookedPkg = bookedPkg;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof reservations))
            return false;
        else if(obj==this)
            return true;
        else {
            reservations aux = (reservations) obj;
            return this.id==aux.getId()&&this.firstName.equals(aux.getFirstName())&&
                    this.lastName.equals(aux.getLastName())&&this.reservationDate.equals(aux.getReservationDate());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.reservationDate);
    }

    @Override
    public String toString() {
        return "reservations{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", reservationDate=" + reservationDate +
                ", paidDeposit=" + paidDeposit +
                '}';
    }
}
