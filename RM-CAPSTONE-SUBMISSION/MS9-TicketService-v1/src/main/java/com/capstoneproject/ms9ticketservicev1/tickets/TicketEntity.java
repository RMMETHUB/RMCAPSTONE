package com.capstoneproject.ms9ticketservicev1.tickets;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "ticket",schema = "ticket_schema")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "match_id")
    private long matchEntityId;
    @Column(name = "ticket_price")
    private float ticketPrice;

    @Column(name = "isActive")
    private boolean isActive;

    public TicketEntity(String customerName, long matchEntity, float ticketPrice, boolean isActive) {
        this.customerName = customerName;
        this.matchEntityId = matchEntity;
        this.ticketPrice = ticketPrice;
        this.isActive = isActive;
    }
}
