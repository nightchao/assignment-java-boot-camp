package com.shopping.product.controller;

import com.shopping.checkout.service.CheckoutService;
import com.shopping.product.db.Basket;
import com.shopping.checkout.db.OrderList;
import com.shopping.product.db.Product;
import com.user.db.ScmUser;
import com.exception.CheckoutProductNotFoundException;
import com.exception.ProductNotFoundException;
import com.exception.UserNotFoundException;
import com.shopping.product.model.*;
import com.shopping.product.service.ProductService;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private UserService userService;

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
    public DetailResponse getProductDetail(@PathVariable Integer productId) {
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
        response.setDateExpiredPromotion(convertDateToStr(product.getDateExpiredPromotionDb()));
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

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        return simpleDateFormat.format(date);
    }

    @PostMapping("/basket")
    public AddBasketResponse addProductToBasket(@Valid @RequestBody() AddBasketRequest input) {
        ScmUser user = userService.getUser(input.getUserId());
        if (user == null) {
            throw new UserNotFoundException(input.getUserId());
        }

        Product product = productService.getProduct(input.getProductId());
        if (product == null) {
            throw new ProductNotFoundException(input.getProductId());
        }

        Basket basket = new Basket(user.getUserId(), product.getProductId());
        basket.setQuantity(input.getQuantity());
        basket.setImage(input.getImage());
        basket.setSize(input.getSize());
        productService.saveBasket(basket);

        return new AddBasketResponse("Update Success");
    }

    @GetMapping("/basket/{userId}")
    public GetBasketResponse showProductInBasket(@PathVariable Integer userId) {
        List<ListBasketItem> listBasket = getListBasketItem(userId);
        int total = listBasket.size();
        return new GetBasketResponse(total, listBasket);
    }

    private List<ListBasketItem> getListBasketItem(int userId) {
        List<Basket> listProduct = productService.getProductInBasket(userId);

        List<ListBasketItem> listBasket = new ArrayList<>();
        ListBasketItem listBasketItem;
        for (Basket basket : listProduct) {
            listBasketItem = new ListBasketItem();
            setDataToListBasketItem(listBasketItem, basket.getProductId());
            listBasketItem.setImage(basket.getImage());
            listBasketItem.setSize(basket.getSize());
            listBasket.add(listBasketItem);
        }
        return listBasket;
    }

    private void setDataToListBasketItem(ListBasketItem listBasketItem, int productId) {
        Product product = productService.getProduct(productId);
        if (product == null) {
            return;
        }

        listBasketItem.setProductId(productId);
        listBasketItem.setQuantity(product.getQuantity());
        listBasketItem.setName(product.getName());
        listBasketItem.setPrice(product.getPrice());
        listBasketItem.setDiscount(product.getDiscount());
        listBasketItem.setBrand(product.getBrand());
        listBasketItem.setVat(product.getVat());
    }

    @PostMapping("/basket/checkout")
    public CheckOutResponse checkOutBasket(@Valid @RequestBody() CheckOutRequest input) {
        List<ListBasketItem> listBasket = getListBasketItem(input.getUserId());
        if (listBasket.isEmpty()) {
            throw new CheckoutProductNotFoundException(input.getUserId());
        }

        List<OrderList> listAllOrder = new ArrayList<>();
        OrderList orderList;
        UUID uuid = UUID.randomUUID();
        String orderId = uuid.toString();
        for (ListBasketItem item : listBasket) {
            orderList = new OrderList(orderId, input.getUserId(), item.getProductId(), item.getQuantity(), item.getPrice(), item.getVat());
            listAllOrder.add(orderList);
        }

        checkoutService.saveOrderList(listAllOrder);
        return new CheckOutResponse("Update Success", orderId);
    }
}
