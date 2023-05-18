package com.capstoneproject.ms9ticketservicev1.tickets;

public class TicketRequestModel {


    private long id;
    private String customerName;
    private long matchId;
    private float ticketPrice;
    private boolean isActive;

    public TicketRequestModel() {
    }

    public TicketRequestModel(long id, String customerName, long matchId, float ticketPrice) {
        this.id = id;
        this.customerName = customerName;
        this.matchId = matchId;
        this.ticketPrice = ticketPrice;
    }

    public TicketRequestModel(String customerName, long matchId, float ticketPrice) {
        this.customerName = customerName;
        this.matchId = matchId;
        this.ticketPrice = ticketPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
