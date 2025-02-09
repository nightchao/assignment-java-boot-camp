package com.shopping.product.controller;

import com.shopping.checkout.db.OrderBuy;
import com.shopping.checkout.service.CheckoutService;
import com.shopping.product.entity.Basket;
import com.shopping.product.entity.Product;
import com.shopping.product.model.*;
import com.shopping.product.service.ProductService;
import com.user.entity.ScmUser;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        System.out.println("Check getProductDetail");
        Product product = productService.getProduct(productId);

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
    @ResponseStatus(HttpStatus.CREATED)
    public AddBasketResponse addProductToBasket(@Valid @RequestBody() AddBasketRequest input) {
        ScmUser user = userService.getUser(input.getUserId());
        Product product = productService.getProduct(input.getProductId());

        Basket basket = new Basket(user.getUserId(), product.getProductId());
        basket.setQuantity(input.getQuantity());
        basket.setImage(input.getImage());
        basket.setSize(input.getSize());
        basket.setEms(product.isEms());
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
        listBasketItem.setProductId(productId);
        listBasketItem.setQuantity(product.getQuantity());
        listBasketItem.setName(product.getName());
        listBasketItem.setPrice(product.getPrice());
        listBasketItem.setDiscount(product.getDiscount());
        listBasketItem.setBrand(product.getBrand());
        listBasketItem.setVat(product.getVat());
        listBasketItem.setEms(product.isEms());
    }

    @PostMapping("/basket/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public CheckOutResponse checkOutBasket(@Valid @RequestBody() CheckOutRequest input) {
        List<ListBasketItem> listBasket = getListBasketItem(input.getUserId());

        List<OrderBuy> listAllOrder = new ArrayList<>();
        OrderBuy orderBuy;
        UUID uuid = UUID.randomUUID();
        String orderId = uuid.toString();
        for (ListBasketItem item : listBasket) {
            orderBuy = new OrderBuy();
            orderBuy.setOrderId(orderId);
            orderBuy.setUserId(input.getUserId());
            orderBuy.setProductId(item.getProductId());
            orderBuy.setQuantity(item.getQuantity());
            orderBuy.setPrice(productService.priceFinal(item.getPrice(), item.getDiscount(), item.getVat()));
            orderBuy.setName(item.getName());
            orderBuy.setEms(item.isEms());
            listAllOrder.add(orderBuy);
        }

        checkoutService.saveOrderBuy(listAllOrder);
        return new CheckOutResponse("Update Success", orderId);
    }
}
