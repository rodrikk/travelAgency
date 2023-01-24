package com.exercise.travelAgency.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity @NoArgsConstructor
public class travelPkg {
    private @Id @GeneratedValue Integer id;
    private String airLineName, hotelName, destination, hotelService;
    private float pricePerPerson;
    private Date returnDate, departDate;
    private LocalDateTime returnFlight;

    public travelPkg(String airLineName, String hotelName, String destination, String hotelService, float pricePerPerson, Date returnDate, Date departDate, LocalDateTime returnFlight) {
        this.airLineName = airLineName;
        this.hotelName = hotelName;
        this.destination = destination;
        this.hotelService = hotelService;
        this.pricePerPerson = pricePerPerson;
        this.returnDate = returnDate;
        this.departDate = departDate;
        this.returnFlight = returnFlight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirLineName() {
        return airLineName;
    }

    public void setAirLineName(String airLineName) {
        this.airLineName = airLineName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getHotelService() {
        return hotelService;
    }

    public void setHotelService(String hotelService) {
        this.hotelService = hotelService;
    }

    public float getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(float pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public LocalDateTime getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(LocalDateTime returnFlight) {
        this.returnFlight = returnFlight;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof travelPkg))
            return false;
        else if(obj==this)
            return true;
        else {
            travelPkg aux = (travelPkg) obj;
            return aux.getId()==this.id&&this.airLineName.equals(aux.getAirLineName())&&
                    this.destination.equals(aux.getDestination())&&this.departDate.equals(aux.getDepartDate());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.airLineName, this.departDate, this.destination);
    }

    @Override
    public String toString() {
        return "Travel Package{" +
                "id=" + id +
                ", airLineName='" + airLineName + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", destination='" + destination + '\'' +
                ", hotelService='" + hotelService + '\'' +
                ", pricePerPerson=" + pricePerPerson +
                ", returnDate=" + returnDate +
                ", departDate=" + departDate +
                ", returnFlight=" + returnFlight +
                '}';
    }
}
