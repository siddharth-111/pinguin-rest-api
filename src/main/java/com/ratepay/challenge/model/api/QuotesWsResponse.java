package com.ratepay.challenge.model.api;

import com.ratepay.challenge.model.enums.QuotesEventType;

public class QuotesWsResponse {
    private QuotesDataResponse data;
    private QuotesEventType type;

    public QuotesDataResponse getData() {
        return data;
    }

    public void setData(QuotesDataResponse data) {
        this.data = data;
    }

    public QuotesEventType getType() {
        return type;
    }

    public void setType(QuotesEventType type) {
        this.type = type;
    }
}