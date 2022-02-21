package com.shopping.product.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBasketResponse {

    private int total;
    private List<ListBasketItem> listBasket;

    public GetBasketResponse() {

    }

    public GetBasketResponse(int total, List<ListBasketItem> listBasket) {
        this.total = total;
        this.listBasket = listBasket;
    }

    public int getTotal() {
        return total;
    }

    public List<ListBasketItem> getListBasket() {
        return listBasket;
    }
}