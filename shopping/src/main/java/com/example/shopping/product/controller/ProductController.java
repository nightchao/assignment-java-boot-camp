package com.example.shopping.product.controller;

import com.example.shopping.product.db.Product;
import com.example.shopping.product.model.ListSearchItem;
import com.example.shopping.product.model.SearchReponse;
import com.example.shopping.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public SearchReponse searchReponse(@RequestParam(required = false, defaultValue = "") String search) {
        List<Product> list = productService.getListProduct(search);
        if (list.isEmpty()) {
            return new SearchReponse(0, new ArrayList<>(1));
        }

        List<ListSearchItem> listSearch = new ArrayList<>(1);
        ListSearchItem model;
        for (Product product : list) {
            model = new ListSearchItem();
            model.setProductId(product.getProductId());
            model.setName(product.getName());
            model.setPrice(product.getPrice());
            model.setDiscount(product.getDiscount());
            model.setRating(product.getRating());
            model.setRatingVote(product.getRatingVote());
            model.setProvince(product.getProvince());
            model.setEms(product.isEms());
            listSearch.add(model);
        }

        int total = listSearch.size();
        return new SearchReponse(total, listSearch);
    }
}
