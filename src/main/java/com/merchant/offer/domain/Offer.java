package com.merchant.offer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@JsonSerialize

public class Offer {

    public enum OfferStatus {
        ACTIVE, INACTIVE, CANCELLED, EXPIRED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @Column
    private String description;

    @Column
    private String currency;

    @Column
    private int price;

    @Column
    private LocalDate startTime;

    @Column
    private LocalDate endTime;

    @Column
    @JsonIgnore
    private boolean cancelled;

    private OfferStatus status;

    public Offer() {

    }

    public Offer(Product product, String description, String currency, int price, LocalDate startTime, LocalDate endTime) {
        this.product = product;
        this.description = description;
        this.currency = currency;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cancelled = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getStartTime() { return startTime; }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public OfferStatus getStatus() {
        if(this.cancelled==true) {
            return OfferStatus.CANCELLED;
        }

        LocalDate now = LocalDate.now();

        if(this.endTime.isBefore(now)) {
            return OfferStatus.EXPIRED;
        }

        if(this.startTime.isBefore(now) && this.endTime.isAfter(now)) {
            return OfferStatus.ACTIVE;
        }

        return OfferStatus.INACTIVE;
    }
}
