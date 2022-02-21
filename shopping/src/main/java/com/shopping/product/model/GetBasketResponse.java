package com.shopping.product.model;

import java.util.List;

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