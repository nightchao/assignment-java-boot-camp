package com.example.shopping.product.controller;

import com.example.shopping.product.db.Product;
import com.example.shopping.product.exception.ProductNotFoundException;
import com.example.shopping.product.model.DetailResponse;
import com.example.shopping.product.model.ListSearchItem;
import com.example.shopping.product.model.SearchResponse;
import com.example.shopping.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public SearchResponse searchProduct(@RequestParam(required = false, defaultValue = "") String search) {
        List<Product> listProduct = productService.getListProduct(search);
        if (listProduct.isEmpty()) {
            return new SearchResponse(0, new ArrayList<>(1));
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
        return new SearchResponse(total, listSearch);
    }

    @GetMapping("/{productId}")
    public DetailResponse getProductDetail(@PathVariable int productId) {
        Product product = productService.getProduct(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }

        DetailResponse response = new DetailResponse(listSizeMockup(), listImgMockup());
        response.setProductId(product.getProductId());
        response.setQuantity(product.getQuantity());
        response.setCategoryId(product.getCategoryId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setDiscount(product.getDiscount());
        response.setBrand(product.getBrand());
        response.setBrandUrl(product.getBrandUrl());
        response.setNote(product.getNote());
        response.setNoteUrl(product.getNoteUrl());
        response.setRating(product.getRating());
        response.setRatingVote(product.getRatingVote());
        response.setWarranty(product.getWarranty());
        response.setDateExpiredPromotion(convertDateToStr(product.getDateExpiredPromotion()));
        return response;
    }

    // Mock up list size data
    private List<Integer> listSizeMockup() {
        List<Integer> list = new ArrayList<>(1);
        list.add(38);
        list.add(39);
        list.add(40);
        list.add(41);
        list.add(42);
        list.add(43);
        return list;
    }

    // Mock up list image data
    private List<String> listImgMockup() {
        List<String> list = new ArrayList<>(1);
        list.add("https://image/1.jpg");
        list.add("https://image/2.jpg");
        list.add("https://image/3.jpg");
        list.add("https://image/4.jpg");
        list.add("https://image/5.jpg");
        return list;
    }

    private String convertDateToStr(Date date) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");
        return dateFor.format(date);
    }
}
