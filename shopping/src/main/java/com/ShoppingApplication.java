package com;

import com.shopping.checkout.db.Payment;
import com.shopping.checkout.repo.PaymentRepository;
import com.shopping.product.entity.Product;
import com.shopping.product.repo.ProductRepository;
import com.user.db.Address;
import com.user.db.ScmUser;
import com.user.repo.AddressRepository;
import com.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ShoppingApplication {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
    }

    @PostConstruct
    public void initData() {
        addUserInit();
        addProductInit();
        addPaymentMethodInit();
    }

    private void addUserInit() {
        ScmUser user = new ScmUser();
        user.setUserId(65001);
        user.setFullName("ไนท์ ป่ามะม่วง");
        user.setEmail("nightza007@abc.com");
        this.userRepository.save(user);

        Address address = new Address(512945, user.getUserId());
        address.setAddress("14/1 หมู่ที่ 10 ตำบลป่ามะม่วง");
        address.setDistrict("เมือง");
        address.setProvince("ตาก");
        address.setTelephone("0891234567");
        address.setDefault(1);
        addressRepository.save(address);
    }

    private void addProductInit() {
        List<Product> listDb = new ArrayList<>(1);

        Product product01 = new Product(1001, 100, 7878, 10000, 10, 30, false);
        product01.setName("Chalawan ลูกพ่อ ผีบอก 001");
        product01.setDescription("ใส่สบาย วางที่ไหน ก็ไม่มีใครขโมย");
        product01.setBrand("Chalawan Shoes");
        product01.setBrandUrl("https://www.chalawan.com");
        product01.setNote("เพิ่มเติม สุภาพบุรุษจาก Chalawan Shoes");
        product01.setNoteUrl("https://www.chalawan.com/shoes/male");
        product01.setDateExpiredPromotion(dateExpiredPromotion());
        product01.setRating(4.5);
        product01.setRatingVote(999);
        product01.setProvince("Tak");
        listDb.add(product01);

        Product product02 = new Product(1002, 20, 7879, 4000, 20, 7, false);
        product02.setName("Chalawan ลูกแม่ ผีบอก 002");
        product02.setDescription("ใส่สวยๆ ไปที่ไหน คนก็ชม");
        product02.setBrand("Chalawan Shoes");
        product02.setBrandUrl("https://www.chalawan.com");
        product02.setNote("เพิ่มเติม สุภาพสตรีจาก Chalawan Shoes");
        product02.setNoteUrl("https://www.chalawan.com/shoes/female");
        product02.setDateExpiredPromotion(dateExpiredPromotion());
        product02.setRating(4.0);
        product02.setRatingVote(888);
        product02.setProvince("Tak");
        listDb.add(product02);

        Product product03 = new Product(1003, 50, 7880, 7000, 5, 14, false);
        product03.setName("Chalawan ลูกใคร ผีบอก 003");
        product03.setDescription("ใส่แล้วคล่องตัว เหมาะกับใส่ในบ้าน");
        product03.setBrand("Chalawan Shoes");
        product03.setBrandUrl("https://www.chalawan.com");
        product03.setNote("เพิ่มเติม ทุกเพศทุกวัย Chalawan Shoes");
        product03.setNoteUrl("https://www.chalawan.com/shoes/general");
        product03.setDateExpiredPromotion(dateExpiredPromotion());
        product03.setRating(5.0);
        product03.setRatingVote(777);
        product03.setProvince("Tak");
        listDb.add(product03);

        Product product04 = new Product(1004, 300, 7880, 1000, 30, 30, true);
        product04.setName("Kraithong สยบเข้ 001");
        product04.setDescription("ใส่แล้วมีพลัง มั่นใจในทุกเวลา");
        product04.setBrand("Kraithong Shoes");
        product04.setBrandUrl("https://www.kraithong.com");
        product04.setNote("เพิ่มเติม ทุกเพศทุกวัย Kraithong Shoes");
        product04.setNoteUrl("https://www.kraithong.com/shoes/general");
        product04.setDateExpiredPromotion(dateExpiredPromotion());
        product04.setRating(5.0);
        product04.setRatingVote(555);
        product04.setProvince("Tak");
        listDb.add(product04);

        this.productRepository.saveAll(listDb);
    }

    private Date dateExpiredPromotion() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    private void addPaymentMethodInit() {
        Payment payment01 = new Payment(111, "Debit or Credit card");
        Payment payment02 = new Payment(222, "Book bank");
        Payment payment03 = new Payment(333, "Cash on delivery");
        List<Payment> listDb = new ArrayList<>(1);
        listDb.add(payment01);
        listDb.add(payment02);
        listDb.add(payment03);
        paymentRepository.saveAll(listDb);
    }
}
