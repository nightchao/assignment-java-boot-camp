package com.shopping.product.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
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