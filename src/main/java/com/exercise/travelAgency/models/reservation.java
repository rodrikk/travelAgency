package com.exercise.travelAgency.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQuery(name = "reservation.findByClientName", query = "FROM reservation WHERE firstName = ?1 AND lastName = ?2")
//Entity representing a reservation made by a client of a given Travel Package
public class reservation {
    @EqualsAndHashCode.Include
    private @Id @GeneratedValue Integer id;
    @EqualsAndHashCode.Include
    private String firstName, lastName;
    @EqualsAndHashCode.Include
    private Date reservationDate;
    private float paidDeposit;

    private Status status;

    @ManyToOne(cascade = CascadeType.REMOVE) @JoinColumn(name="pack_id", referencedColumnName = "id")
    private travelPkg bookedPkg;


    public reservation(String firstName, String lastName, Date reservationDate, float paidDeposit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservationDate = reservationDate;
        this.paidDeposit = paidDeposit;
        this.status = Status.IN_PROGRESS;
    }

    public reservation(String firstName, String lastName, Date reservationDate, float paidDeposit, travelPkg bookedPkg) {
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

    public void setStatus(Status stat) {
        this.status = stat;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "reservation{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", reservationDate=" + reservationDate +
                ", paidDeposit=" + paidDeposit +
                '}';
    }
}
