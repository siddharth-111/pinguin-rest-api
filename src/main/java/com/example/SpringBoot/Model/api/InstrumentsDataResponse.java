package com.example.SpringBoot.Model.api;

public class InstrumentsDataResponse {
    private String description;
    private String isin;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }
}
