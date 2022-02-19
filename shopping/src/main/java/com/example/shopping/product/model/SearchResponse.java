package com.example.shopping.product.model;

import java.util.List;

public class SearchResponse {

    private int total;
    private List<ListSearchItem> listSearch;

    public SearchResponse() {
    }

    public SearchResponse(int total, List<ListSearchItem> listSearch) {
        this.total = total;
        this.listSearch = listSearch;
    }

    public List<ListSearchItem> getListSearch() {
        return listSearch;
    }

    public int getTotal() {
        return total;
    }
}