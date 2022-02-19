package com.example.shopping.product.controller;

import com.example.shopping.product.db.Product;
import com.example.shopping.product.model.ListSearchItem;
import com.example.shopping.product.model.SearchReponse;
import com.example.shopping.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SearchReponse searchProduct(@RequestParam(required = false, defaultValue = "") String search) {
        List<Product> listProduct = productService.getListProduct(search);
        if (listProduct.isEmpty()) {
            return new SearchReponse(0, new ArrayList<>(1));
        }

        List<ListSearchItem> listSearch = new ArrayList<>(1);
        ListSearchItem listSearchItem;
        for (Product product : listProduct) {
            listSearchItem = new ListSearchItem();
            listSearchItem.setProductId(product.getProductId());
            listSearchItem.setName(product.getName());
            listSearchItem.setPrice(product.getPrice());
            listSearchItem.setDiscount(product.getDiscount());
            listSearchItem.setRating(product.getRating());
            listSearchItem.setRatingVote(product.getRatingVote());
            listSearchItem.setProvince(product.getProvince());
            listSearchItem.setEms(product.isEms());
            listSearch.add(listSearchItem);
        }

        int total = listSearch.size();
        return new SearchReponse(total, listSearch);
    }
}
