# Flow การสั่งซื้อสินค้า Online จากร้าน SALADAR ในกรณี Login เข้าระบบมาแล้ว

กำหนด userId ใน flow นี้คือ 65001

## 1. ค้นหาสินค้า โดยกำหนดให้ค้นคำว่า Chalawan จากนั้นระบบจะแสดงสินค้าที่ค้นหาเจอ

| Method | Url                      | Description                                  | Sample Valid Request Body                                                                                       | Sample Valid Response Body                                                                                       |
|--------|--------------------------|----------------------------------------------|-----------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| GET    | /product?search=         | show list product and search by product name | -                                                                                                               | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Response-for-search-product)             |

## 2. เลือกสินค้า

กำหนดให้เลือกสินค้า productId = 1001

| Method | Url                      | Description                                  | Sample Valid Request Body                                                                                       | Sample Valid Response Body                                                                                       |
|--------|--------------------------|----------------------------------------------|-----------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| GET    | /product/{productId}     | show product detail                          | -                                                                                                               | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Response-for-product-detail)             |

## 3. กดสินค้าใส่ตะกร้า

| Method | Url                      | Description                                  | Sample Valid Request Body                                                                                       | Sample Valid Response Body                                                                                       |
|--------|--------------------------|----------------------------------------------|-----------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| POST   | /product/basket          | add product to basket                        | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Request-for-add-product-to-basket)      | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Response-for-add-product-to-basket)      |

## 4. แสดงสินค้าในตะกร้า

กำหนดให้มีสินค้าอยู่ในตะกร้า 1 รายการ

| Method | Url                      | Description                                  | Sample Valid Request Body                                                                                       | Sample Valid Response Body                                                                                       |
|--------|--------------------------|----------------------------------------------|-----------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| GET    | /product/basket/{userId} | show product in basket                       | -                                                                                                               | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Response-for-show-product-in-basket)     |

## 5. กดชำระค่าสินค้า

| Method | Url                      | Description                                  | Sample Valid Request Body                                                                                       | Sample Valid Response Body                                                                                       |
|--------|--------------------------|----------------------------------------------|-----------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| POST   | /product/basket/checkout | checkout product in basket                   | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Request-for-checkout-product-in-basket) | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Response-for-checkout-product-in-basket) |

## 6. แสดงที่อยู่เพื่อจัดส่งสินค้า, ข้อมูลการส่งสินค้า และรายการสั่งซื้อสินค้า

กำหนดให้ที่อยู่แสดงตามค่า default ของ user ในระบบ กำหนดให้ไม่เลือกรับใบกำกับภาษี

| Method | Url                       | Description       | Sample Valid Request Body | Sample Valid Response Body                                                                             |
|--------|---------------------------|-------------------|---------------------------|--------------------------------------------------------------------------------------------------------|
| GET    | /user/{userId}/address    | show address user | -                         | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Request-for-show-address-user) |
| GET    | /order/shipping/{orderId} | show shipping     | -                         | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Response-for-show-shipping)    |
| GET    | /order/{orderId}          | show list order   | -                         | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Response-for-show-list-order)  |

## 7. แสดงช่องทางการชำระเงิน

กำหนดให้เลือกช่องทางการชำระเงินโดยโอนเงินผ่านธนาคาร

| Method | Url                   | Description                                  | Sample Valid Request Body                                                                                       | Sample Valid Response Body                                                                                       |
|--------|-----------------------|----------------------------------------------|-----------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| GET    | /order/payment/method | show payment method                          | -                                                                                                               | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Response-for-show-payment-method)        |

## 8. สั่งสื้อสินค้า

กำหนดให้ไม่ใช้คูปองส่วนลด

| Method | Url              | Description  | Sample Valid Request Body                                                                         | Sample Valid Response Body                                                                         |
|--------|------------------|--------------|---------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| PUT    | /order/{orderId} | update order | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Request-for-update-order) | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Response-for-update-order) |

## 9. แสดงใบแจ้งการชำระเงิน

| Method | Url                      | Description  | Sample Valid Request Body | Sample Valid Response Body                                                                         |
|--------|--------------------------|--------------|---------------------------|----------------------------------------------------------------------------------------------------|
| GET    | /order/summary/{orderId} | show summary | -                         | [JSON](https://github.com/nightchao/assignment-java-boot-camp/wiki/JSON-Response-for-show-summary) |
