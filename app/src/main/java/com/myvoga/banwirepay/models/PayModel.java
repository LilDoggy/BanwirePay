package com.myvoga.banwirepay.models;

public class PayModel {

    private String id;
    private String description;
    private String amount;
    private String ref;
    private String date;
    private boolean isPaid;

    public PayModel(String id, String description, String amount, String ref, String date, boolean isPaid) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.ref = ref;
        this.date = date;
        this.isPaid = isPaid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
