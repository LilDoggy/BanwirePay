package com.myvoga.banwirepay.models;

public class HistoryModel {

    private String status;
    private String ref;
    private String amount;

    public HistoryModel(String status, String ref, String amount) {
        this.status = status;
        this.ref = ref;
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
