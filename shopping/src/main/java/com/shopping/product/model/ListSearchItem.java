package com.shopping.product.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopping.product.db.Product;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListSearchItem extends Product {

}
