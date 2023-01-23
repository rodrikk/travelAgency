package com.exercise.travelAgency;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor @NoArgsConstructor
public class reservationDTO {
    @Getter @Setter
    private Integer travelPkgId, reservationId;
    @Getter @Setter
    private String airLineName, hotelName, destination, hotelService,
            firstName, lastName;
    @Getter @Setter
    private float pricePerPerson, paidDeposit;
    @Getter @Setter
    private Date returnDate, departDate, reservationDate;
    @Getter @Setter
    private LocalDateTime returnFlight;
    @Getter @Setter
    private Status status;

    @Override
    public String toString() {
        return "reservation{" +
                "id=" + reservationId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", reservationDate=" + reservationDate +
                ", paidDeposit=" + paidDeposit +
                '}' + "\n" +
                "Travel Package{" +
                "id=" + travelPkgId +
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
