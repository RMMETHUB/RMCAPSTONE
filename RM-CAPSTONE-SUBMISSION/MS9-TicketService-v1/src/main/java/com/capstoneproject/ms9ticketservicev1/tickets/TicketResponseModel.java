package com.capstoneproject.ms9ticketservicev1.tickets;


import com.capstoneproject.ms9ticketservicev1.feign_client.MatchResponseForTicketRequestModel;

public class TicketResponseModel {

    private long ticketId;
    private String customerName;
    private MatchResponseForTicketRequestModel match;
    private float ticketPrice;
    private boolean isActive;

    public TicketResponseModel() {}

    public TicketResponseModel(long ticketId, String customerName, MatchResponseForTicketRequestModel match, float ticketPrice, boolean isActive) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.match = match;
        this.ticketPrice = ticketPrice;
        this.isActive = isActive;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public MatchResponseForTicketRequestModel getMatch() {
        return match;
    }

    public void setMatch(MatchResponseForTicketRequestModel match) {
        this.match = match;
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
